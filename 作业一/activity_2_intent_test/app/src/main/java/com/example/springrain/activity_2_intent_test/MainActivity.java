package com.example.springrain.activity_2_intent_test;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity{
    private EditText name,pwd;
    private Button loginButton;
    private TextView res;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = (EditText) findViewById(R.id.name);
        pwd =  (EditText) findViewById(R.id.pwd);
        loginButton = (Button) findViewById(R.id.login);
        res = (TextView) findViewById(R.id.res);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(name.getText().toString().equals("lth")&&(pwd.getText().toString().equals("123"))){
                    Intent it = new Intent(MainActivity.this, SecondActivity.class);
                    it.putExtra("name", name.getText().toString());
                    startActivity(it);
                }else{
                    res.setText("用户名或密码错误！");
                }
            }
        });
    }
}
