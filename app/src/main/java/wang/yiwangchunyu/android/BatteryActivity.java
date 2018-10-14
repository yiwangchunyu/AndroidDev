package wang.yiwangchunyu.android;

import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class BatteryActivity extends AppCompatActivity {
    MyBroadCastReceiver myReceiver;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_battery);

        myReceiver = new MyBroadCastReceiver();
        IntentFilter itFilter = new IntentFilter();
        itFilter.addAction(Intent.ACTION_BATTERY_CHANGED);
        registerReceiver(myReceiver, itFilter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }
}
