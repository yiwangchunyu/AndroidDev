package com.example.springrain.ch7project3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText et;
    TextView show;
    Button saveButton;
    Button readButton;
    public static String filename = "tmp.txt";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind();

    }

    private void bind() {
        et = (EditText) findViewById(R.id.et);
        show = (TextView) findViewById(R.id.show);
        saveButton = (Button) findViewById(R.id.save);
        readButton = (Button) findViewById(R.id.read);
        saveButton.setOnClickListener(this);
        readButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        String detail="";
        FileHelper fHelper = new FileHelper(getApplicationContext());
        switch (view.getId()){
            case R.id.save:
                detail = et.getText().toString();
                try {
                    fHelper.save(filename, detail);
                    Toast.makeText(getApplicationContext(), "数据写入成功", Toast.LENGTH_SHORT).show();
                } catch (Exception e) {
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "数据写入失败", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.read:
                try {
                    detail = fHelper.read(filename);
                    show.setText(detail);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), detail, Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
    }
}
