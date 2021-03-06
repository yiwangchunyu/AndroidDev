package com.example.springrain.activity_2_intent_test;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.util.Log;

public class MusicService extends Service {
    private final String TAG = "MusicService";
    private MediaPlayer mPlayer = null;
    public MusicService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        Log.i(TAG, "onBind方法被调用!");
        return null;
    }

    //Service被创建时调用
    @Override
    public void onCreate() {
        Log.i(TAG, "onCreate方法被调用!");
        mPlayer = MediaPlayer.create(this,R.raw.daoko);
        super.onCreate();
    }
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "onStartCommand方法被调用!");
        //Toast.makeText(this, "mp3 play", Toast.LENGTH_SHORT).show();
        mPlayer.start();   //开始播放
        return super.onStartCommand(intent,flags,startId);
    }
    //Service被关闭之前回调
    @Override
    public void onDestroy() {
        Log.i(TAG, "onDestory方法被调用!");
        mPlayer.pause();     //停止播放
        super.onDestroy();
    }
}

