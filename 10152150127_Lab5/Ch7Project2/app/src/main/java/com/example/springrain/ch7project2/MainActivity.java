package com.example.springrain.ch7project2;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    Button saveButton;
    Button readButton;
    EditText usernameEd;
    EditText passwordEd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        saveButton=(Button)findViewById(R.id.save);
        readButton=(Button)findViewById(R.id.read);
        usernameEd=(EditText)findViewById(R.id.username);
        passwordEd=(EditText)findViewById(R.id.password);
        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }
    //定义一个保存数据的方法
    public void save(String username, String password) {
        SharedPreferences sp = this.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString("username", username);
        editor.putString("password", password);
        editor.commit();
        Toast.makeText(this, "信息已写入", Toast.LENGTH_SHORT).show();
    }

    //定义一个读取SP文件的方法
    public Map<String, String> read() {
        Map<String, String> data = new HashMap<String, String>();
        SharedPreferences sp = this.getSharedPreferences("mysp", Context.MODE_PRIVATE);
        data.put("username", sp.getString("username", ""));
        data.put("password", sp.getString("password", ""));
        return data;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.save:
                if(!check()){
                    Toast.makeText(this, "请输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{
                    save(usernameEd.getText().toString(),passwordEd.getText().toString());
                }
                break;
            case R.id.read:
                if(check()){
                    Toast.makeText(this, "请不要输入用户名和密码", Toast.LENGTH_SHORT).show();
                }else{
                    Map<String,String> map = read();
                    usernameEd.setText(map.get("username"));
                    passwordEd.setText(map.get("password"));
                }
                break;
            default:
                break;

        }
    }

    public boolean check(){
        if(usernameEd.getText().toString().length()<=0||passwordEd.getText().toString().length()<=0){
            return false;
        }else{
            return true;
        }
    }
}
