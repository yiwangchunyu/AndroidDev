package com.example.springrain.ch8project1;

import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv;
    private Button bt;
    private Handler handler;
    private Runnable runable;
    private Thread t;
    private int second = 0;
    private boolean stop = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler = new Handler() {
            @Override
            public void handleMessage(Message msg) {
                if (msg.what == 1) {
                    int now = msg.getData().getInt("now");
                    tv.setText("计时： " + now + " 秒");
                }

            }
        };

        runable = new Runnable() {
            public void run() {
                while (!stop) {
                    // 创建消息
                    Message msg = new Message();
                    msg.what = 1;
                    Bundle bundle = new Bundle();
                    second += 1;
                    bundle.putInt("now", second);
                    msg.setData(bundle);
//                    每秒钟发送一次message
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    handler.sendMessage(msg);
                }
            }
        };
        tv = (TextView) findViewById(R.id.tv);
        bt = (Button) findViewById(R.id.bt);
        bt.setOnClickListener(this);

//        handler.post(runable);
        t = new Thread(runable);
        t.start();
    }

    @Override
    public void onClick(View view) {
//        handler.removeCallbacks(runable);
        stop = true;
        t.interrupt();
    }
}
