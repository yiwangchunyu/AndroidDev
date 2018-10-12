package wang.yiwangchunyu.android;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {
    private TextView show;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        show = (TextView) findViewById(R.id.show);
        Intent it = getIntent();
        String name = it.getStringExtra("name");
        show.setText(name);
    }
}
