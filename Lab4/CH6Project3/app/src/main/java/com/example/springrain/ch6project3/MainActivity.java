package com.example.springrain.ch6project3;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private AlertDialog alertDialog;
    private Button bt;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bt = (Button) findViewById(R.id.bt);
        initDialog();
        bt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDialog();
                Toast t = Toast.makeText(getApplicationContext(), "You have a new message!", Toast.LENGTH_SHORT);
                t.setGravity(Gravity.CENTER, 0, 0);//设置对齐方式
                t.setMargin(0, 20);
                t.show();
            }
        });
    }

    /*
    初始化AlertDialog
     */
    public void initDialog() {
        //创建AlertDialog的构造器的对象
        AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
        //设置构造器标题
        //builder.setTitle("提示");
        //构造器对应的图标
        //builder.setIcon(R.mipmap.ic_launcher);
        //构造器内容,为对话框设置文本项(之后还有列表项的例子)
        builder.setMessage("You have a new message!");
        //为构造器设置确定按钮,第一个参数为按钮显示的文本信息，第二个参数为点击后的监听事件，用匿名内部类实现
        builder.setPositiveButton("Sure", null);
        //为构造器设置取消按钮,若点击按钮后不需要做任何操作则直接为第二个参数赋值null
        builder.setNegativeButton("Cancel", null);
        //为构造器设置一个比较中性的按钮，比如忽略、稍后提醒等
        //builder.setNeutralButton("ignore",null);
        //利用构造器创建AlertDialog的对象,实现实例化
        alertDialog = builder.create();
    }

    public void showDialog() {
        //当AlertDialog存在实例对象并且没有在展示时
        if (alertDialog != null && !alertDialog.isShowing())
            alertDialog.show();
    }

}
