package com.example.springrain.ch5project1;

import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button fontButton, sizeButton;
    private TextView tv;
    private int fontLoop;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        fontLoop=1;
        fontButton = (Button) findViewById(R.id.font);
        sizeButton = (Button) findViewById(R.id.size);
        tv = (TextView) findViewById(R.id.tv);
        fontButton.setOnClickListener(this);
        sizeButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.font:
                switch (fontLoop){
                    case 1:
                        tv.setTypeface(Typeface.SERIF);
                        fontLoop++;
                        break;
                    case 2:
                        tv.setTypeface(Typeface.SANS_SERIF);
                        fontLoop++;
                        break;
                    case 3:
                        tv.setTypeface(Typeface.MONOSPACE);
                        fontLoop=1;
                        break;
                }
                break;
            case R.id.size:
                tv.setTextSize(tv.getTextSize()>50?10:tv.getTextSize()+1);
                break;
            default:
                break;
        }
    }
}
