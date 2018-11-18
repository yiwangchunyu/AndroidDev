package com.example.springrain.activity_2_intent_test;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener{
    private Button play,stop;
    private TextView res_mp3;
    private Intent itMp3Player;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        play = (Button) findViewById(R.id.play);
        stop = (Button) findViewById(R.id.stop);
        res_mp3 = (TextView) findViewById(R.id.res_mp3);
        play.setOnClickListener(this);
        stop.setOnClickListener(this);
        itMp3Player = new Intent(MainActivity.this, MusicService.class);
    }


    @Override
    public void onClick(View view) {
        if(view.getId()==R.id.play){
            startService(itMp3Player);
            res_mp3.setText("音乐正在后台播放");
        }
        if(view.getId()==R.id.stop){
            stopService(itMp3Player);
            res_mp3.setText("音乐停止播放");
        }
    }
}