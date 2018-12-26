package wang.yiwangchunyu.androidfinal;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.HashMap;

import cn.smssdk.EventHandler;
import cn.smssdk.SMSSDK;
import cn.smssdk.gui.RegisterPage;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    TextView tv_signup;
    Button bt_signin;
    Context mcontext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        mcontext=this;
        init();
        bt_signin = (Button) findViewById(R.id.btn_signin);
        bt_signin.setOnClickListener(this);
        tv_signup = (TextView) findViewById(R.id.tv_singup);
        tv_signup.setOnClickListener(this);
    }

    private void init() {

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
                            toRegister(phone);

                        } else{
                            // TODO 处理错误的结果
                            Toast.makeText(mcontext, "验证失败！", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
                page.show(mcontext);
                break;

            case R.id.btn_signin:
                Intent it = new Intent(LoginActivity.this, MainActivity.class);
                startActivity(it);
                break;
            default:
                break;
        }
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
            String msg = bd.getString("msg");
            Toast.makeText(this,msg,Toast.LENGTH_SHORT).show();
        }
    }
}
