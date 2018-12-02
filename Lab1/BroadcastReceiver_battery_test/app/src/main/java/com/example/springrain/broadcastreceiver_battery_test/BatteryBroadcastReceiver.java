package com.example.springrain.broadcastreceiver_battery_test;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by SpringRain on 2018/10/15.
 */

public class BatteryBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        String msg;
        int level = intent.getIntExtra("level",-1);
        int scale = intent.getIntExtra("scale",-1);
        if(level<20){
            msg="电量过低，请及时充电";
        }
        else if(level==100){
            msg="电量已恢复，请放心使用";
        }
        else{
            msg="电量足够，请放心使用";
        }
        String show_msg="当前电量为："+level+"% 。"+msg;
        String TAG = "MyBroadCastReceiver";
        Log.i(TAG, show_msg);
        Toast.makeText(context,show_msg, Toast.LENGTH_LONG).show();
    }
}
