package wang.yiwangchunyu.androidfinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {

    Button bt_register;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        bt_register = (Button) findViewById(R.id.btn_signin);
        bt_register.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.btn_signin:
                Intent it = getIntent();
                String phone = it.getStringExtra("phone");
                Bundle bd = new Bundle();
                bd.putString("msg","注册成功："+phone);
                it.putExtras(bd);
                setResult(1,it);
                finish();
                break;
            default:
                break;
        }
    }

}
