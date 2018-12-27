package wang.yiwangchunyu.androidfinal;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;
import cz.msebera.android.httpclient.Header;
import wang.yiwangchunyu.androidfinal.bean.User;
import wang.yiwangchunyu.androidfinal.constants.UrlConstants;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.HttpResponse;
import wang.yiwangchunyu.androidfinal.utils.SharedHelper;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    TextView tv_signup;
    Button bt_signin;
    Context mcontext;
    EditText etPhone,etPwd;
    ImageView ivNameClear, ivPwdClear;
    CheckBox cbRemenber, cbAutoLogin;
    SharedHelper sh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcontext=this;
        sh = new SharedHelper(mcontext);

        bt_signin = (Button) findViewById(R.id.btn_signin);
        bt_signin.setOnClickListener(this);
        tv_signup = (TextView) findViewById(R.id.tv_singup);
        tv_signup.setOnClickListener(this);
        etPhone = (EditText) findViewById(R.id.et_userPhone);
        etPwd = (EditText) findViewById(R.id.et_password);
        ivNameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ivNameClear.setOnClickListener(this);
        ivPwdClear = (ImageView) findViewById(R.id.iv_pwdClear);
        ivPwdClear.setOnClickListener(this);
        cbRemenber = (CheckBox) findViewById(R.id.rmb_pwd);
        cbRemenber.setOnClickListener(this);
        cbAutoLogin = (CheckBox) findViewById(R.id.auto_login);
        cbAutoLogin.setOnClickListener(this);
        init();
    }

    private void init() {
        if(sh.read("remenber").equals("true")){
            cbRemenber.setChecked(true);
            Log.d(TAG,"phone:"+sh.read("phone"));
            etPhone.setText(sh.read("phone"));
            etPwd.setText(sh.read("password"));
        }
        if(sh.read("autologin").equals("false")){

        }
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_singup:
                RegisterPage page = new RegisterPage();
                page.setTempCode(null);
                page.setRegisterCallback(new EventHandler() {
                    public void afterEvent(int event, int result, Object data) {
                        if (result == SMSSDK.RESULT_COMPLETE) {
                            // 处理成功的结果
                            HashMap<String,Object> phoneMap = (HashMap<String, Object>) data;
                            String country = (String) phoneMap.get("country"); // 国家代码，如“86”
                            String phone = (String) phoneMap.get("phone"); // 手机号码，如“13800138000”
                            // TODO 利用国家代码和手机号码进行后续的操作
                            userExist(phone);
                        } else{
                            // TODO 处理错误的结果
                            Toast.makeText(mcontext, "验证失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                page.show(mcontext);
                break;

            case R.id.btn_signin:
                if(check()){
                    login(etPhone.getText().toString(), etPwd.getText().toString());
                }
                break;

            case R.id.iv_unameClear:
                etPhone.setText("");
                break;

            case R.id.iv_pwdClear:
                etPwd.setText("");
                break;

            case R.id.rmb_pwd:
                if(cbRemenber.isChecked()){
                    sh.save("remenber", "true");
                }else{
                    sh.save("remenber", "false");
                }
                break;

            case R.id.auto_login:
                if(cbAutoLogin.isChecked()){
                    sh.save("autologin", "true");
                }else{
                    sh.save("autologin", "false");
                }
                break;
            default:
                break;
        }
    }

    private void login(String num, String pd) {
        RequestParams params = new RequestParams();
        params.put("phone", num);
        params.put("password", pd);
        AsyncHttpUtils.post("/user/validate", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
                ToastUtils.showToast(mcontext, "fail: " + responseString, Toast.LENGTH_SHORT);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "success: " + responseString);
                JSONObject jsonRes = null;
                try {
                    jsonRes = new JSONObject(responseString);
                    if (jsonRes.getInt("code") == 0) {
                        Toast.makeText(mcontext, "登陆成功！", Toast.LENGTH_SHORT).show();
                        JSONArray data = jsonRes.getJSONArray("data");
                        JSONObject user = data.getJSONObject(0);
                        SharedHelper sh = new SharedHelper(mcontext);
                        sh.saveUser(user.getString("nickname"), user.getString("password"), user.getInt("id")+"", user.getString("phone"));

                        Intent it = new Intent(LoginActivity.this, MainActivity.class);
                        startActivity(it);
                        finish();
                    } else {
                        ToastUtils.showToast(mcontext, jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

    private boolean check() {
        if(etPhone.getText().toString().length()<1 || etPwd.getText().toString().length()<1){
            Toast.makeText(mcontext, "用户名或密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }

    private void userExist(final String phone) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        AsyncHttpUtils.post("/user/get", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {

                HttpResponse hr = new Gson().fromJson(responseString, HttpResponse.class);
                Log.d(TAG, "success: " + responseString);
                JSONObject jsonRes = null;
                try {
                    jsonRes = new JSONObject(responseString);
                    if(jsonRes.getInt("code")==0){
                        JSONArray data = jsonRes.getJSONArray("data");
                        if(data.length()>0){
                            Toast.makeText(mcontext, "用户已存在！", Toast.LENGTH_SHORT).show();
                        }else{
                            toRegister(phone);
                        }
                    }else{
                        ToastUtils.showToast(mcontext, jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }

//                setUserInfo();
            }
        });
    }

    private void toRegister(String phone){
        Intent it = new Intent(LoginActivity.this, RegisterActivity.class);
        it.putExtra("phone",phone);
        startActivityForResult(it, 1);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==1&&resultCode==1){
            Bundle bd = data.getExtras();
            String phone = bd.getString("phone");
            etPhone.setText(phone);
        }
    }
}
