package com.example.springrain.ch8project2;

import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView tv;
    Button startBtn, stopBtn;
    ProgressBar progressBar, progressBar2;
    MyAsyncTask task;
    private boolean first = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = (TextView) findViewById(R.id.tv);
        startBtn = (Button) findViewById(R.id.startBtn);
        startBtn.setOnClickListener(this);
        stopBtn = (Button) findViewById(R.id.stopBtn);
        stopBtn.setOnClickListener(this);
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar2 = (ProgressBar) findViewById(R.id.progressBar2);
        progressBar2.setVisibility(View.INVISIBLE);

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.startBtn:
                startBtn.setEnabled(false);
                stopBtn.setEnabled(true);
                progressBar2.setVisibility(View.VISIBLE);
                task = new MyAsyncTask(tv, startBtn, stopBtn, progressBar, progressBar2);
                if (first) {
                    first = false;
                    task.execute(0);
                } else {
                    Log.d("task", "|" + Integer.parseInt(tv.getText().toString()));
                    task.execute(Integer.parseInt(tv.getText().toString()));
                }
                break;
            case R.id.stopBtn:
                stopBtn.setEnabled(false);
                startBtn.setEnabled(true);
                progressBar2.setVisibility(View.INVISIBLE);
                task.cancel(true);
                break;
            default:
                break;
        }
    }
}
