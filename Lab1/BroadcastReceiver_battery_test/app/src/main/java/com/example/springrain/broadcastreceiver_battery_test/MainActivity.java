package com.example.springrain.broadcastreceiver_battery_test;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    BatteryBroadcastReceiver myReceiver;

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(myReceiver);

    }

    @Override
    protected void onResume() {
        super.onResume();
        myReceiver = new BatteryBroadcastReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReceiver, itFilter);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
