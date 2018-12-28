package wang.yiwangchunyu.androidfinal;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import wang.yiwangchunyu.androidfinal.utils.TitleBuilder;

public class SettingActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);
        new TitleBuilder(this)
                .setTitleText("设置")
                .setLeftImage(R.drawable.close)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        finish();
                    }
                });
    }
}
