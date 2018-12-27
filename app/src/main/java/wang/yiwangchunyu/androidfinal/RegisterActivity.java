package wang.yiwangchunyu.androidfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;


public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    private static final String TAG = "RegisterActivity";
    Button bt_register;
    EditText etNickname, pwd ,pwdCheck;
    RadioButton rb_girl,rb_boy;
    ImageView ivNameClear, ivPwdClear, ivPwdCheckClear;
    public int gender=1;
    private Context mcontext;
    Intent it;
    String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        mcontext = this;

        it = getIntent();
        phone = it.getStringExtra("phone");

        bt_register = (Button) findViewById(R.id.btn_signin);
        bt_register.setOnClickListener(this);
        etNickname = (EditText) findViewById(R.id.et_userName);
        pwd = (EditText) findViewById(R.id.et_password);
        pwdCheck = (EditText) findViewById(R.id.et_passwordCheck);
        rb_boy = (RadioButton) findViewById(R.id.rb_boy);
        rb_boy.setOnClickListener(this);
        rb_girl = (RadioButton) findViewById(R.id.rb_girl);
        rb_girl.setOnClickListener(this);
        ivNameClear = (ImageView) findViewById(R.id.iv_unameClear);
        ivNameClear.setOnClickListener(this);
        ivPwdClear = (ImageView) findViewById(R.id.iv_pwdClear);
        ivPwdClear.setOnClickListener(this);
        ivPwdCheckClear = (ImageView) findViewById(R.id.iv_pwdCheckClear);
        ivPwdCheckClear.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signin:
                if(this.check()){
                    this.register(phone, etNickname.getText().toString(), pwd.getText().toString(),gender);
                }

                break;

            case R.id.iv_unameClear:
                etNickname.setText("");
                break;

            case R.id.iv_pwdClear:
                pwd.setText("");
                break;

            case R.id.iv_pwdCheckClear:
                pwdCheck.setText("");
                break;

            case R.id.rb_boy:
                rb_boy.setChecked(true);
                rb_girl.setChecked(false);
                gender=1;
                break;

            case R.id.rb_girl:
                rb_girl.setChecked(true);
                rb_boy.setChecked(false);
                gender=0;
                break;

            default:
                break;
        }
    }

    private boolean check() {
        if(etNickname.getText().toString().replace(" ", "").length()<1){
            Toast.makeText(mcontext, "用户名不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(pwd.getText().toString().replace(" ", "").length()<1||pwdCheck.getText().toString().replace(" ", "").length()<1){
            Toast.makeText(mcontext, "密码不能为空！", Toast.LENGTH_SHORT).show();
            return false;
        }
        if(!pwd.getText().toString().equals(pwdCheck.getText().toString())){
            Toast.makeText(mcontext, "两次密码输入不相同！", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    private void register(final String phone, String nickname, String password, int gender) {
        RequestParams params = new RequestParams();
        params.put("phone", phone);
        params.put("nickname", nickname);
        params.put("password", password);
        params.put("gender", gender);
        AsyncHttpUtils.post("/user/add", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "success: " + responseString);
                JSONObject jsonRes = null;
                try {
                    jsonRes = new JSONObject(responseString);
                    if (jsonRes.getInt("code") == 0) {
                        Toast.makeText(mcontext, "注册成功！", Toast.LENGTH_SHORT).show();
                        JSONObject data = jsonRes.getJSONObject("data");
                        int user_id = data.getInt("id");
                        Bundle bd = new Bundle();
                        bd.putString("phone", phone);
                        bd.putInt("user_id", user_id);
                        it.putExtras(bd);
                        setResult(1,it);
                        finish();
                    } else {
                        ToastUtils.showToast(mcontext, "注册失败：" + jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                        Bundle bd = new Bundle();
                        bd.putString("phone", phone);
                        it.putExtras(bd);
                        setResult(1,it);
                        finish();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }

}
