package com.example.springrain.ch5project3;

import android.app.Activity;
import android.app.TabActivity;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TabHost;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {
    private List<Item> itemList = new ArrayList<Item>();
    private List<Item> itemList2 = new ArrayList<Item>();
    private List<Item> itemList3 = new ArrayList<Item>();
    private List<Item> itemList4 = new ArrayList<Item>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        TabHost tab = (TabHost) findViewById(R.id.tabhost);

        //初始化TabHost容器
        tab.setup();
        //在TabHost创建标签，然后设置：标题／图标／标签页布局
        tab.addTab(tab.newTabSpec("tab1").setIndicator("首页" , null).setContent(R.id.tab1));
        tab.addTab(tab.newTabSpec("tab2").setIndicator("直播" , null).setContent(R.id.tab2));
        tab.addTab(tab.newTabSpec("tab3").setIndicator("排行" , null).setContent(R.id.tab3));
        tab.addTab(tab.newTabSpec("tab4").setIndicator("搜索" , null).setContent(R.id.tab4));
        tab.addTab(tab.newTabSpec("tab5").setIndicator("更多" , null).setContent(R.id.tab5));

        init1();
        init2();
        init3();
        init4();
        init5();
    }

    private void init1() {
        Item item1 = new Item("雪豹", R.drawable.pic1);
        itemList.add(item1);
        Item item2 = new Item("士兵突击", R.drawable.pic1);
        itemList.add(item2);
        Item item3 = new Item("雪豹", R.drawable.pic1);
        itemList.add(item3);
        Item item4 = new Item("嘻嘻嘻", R.drawable.pic1);
        itemList.add(item4);
        Item item5 = new Item("助教哥哥", R.drawable.pic1);
        itemList.add(item5);
        Item item6 = new Item("给个好评呗~", R.drawable.pic1);
        itemList.add(item6);

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList);
        ListView listView = (ListView) findViewById(R.id.listView1);
        listView.setAdapter(adapter);
    }
    private void init2() {
        Item item1 = new Item("啊哈哈哈", R.drawable.pic2);
        itemList2.add(item1);
        Item item2 = new Item("嘻嘻嘻嘻", R.drawable.pic2);
        itemList2.add(item2);
        Item item3 = new Item("霍霍霍霍", R.drawable.pic2);
        itemList2.add(item3);
        Item item4 = new Item("你看这个碗", R.drawable.pic2);
        itemList2.add(item4);
        Item item5 = new Item("又大又圆", R.drawable.pic2);
        itemList2.add(item5);
        Item item6 = new Item("freestyle", R.drawable.pic2);
        itemList2.add(item6);

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList2);
        ListView listView = (ListView) findViewById(R.id.listView2);
        listView.setAdapter(adapter);
    }
    private void init3() {
        Item item1 = new Item("啊哈哈哈", R.drawable.png1);
        itemList3.add(item1);
        Item item2 = new Item("嘻嘻嘻嘻", R.drawable.png1);
        itemList3.add(item2);
        Item item3 = new Item("霍霍霍霍", R.drawable.png1);
        itemList3.add(item3);
        Item item4 = new Item("你看这个碗", R.drawable.png1);
        itemList3.add(item4);
        Item item5 = new Item("又大又圆", R.drawable.png1);
        itemList3.add(item5);
        Item item6 = new Item("freestyle", R.drawable.png1);
        itemList3.add(item6);

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList3);
        ListView listView = (ListView) findViewById(R.id.listView3);
        listView.setAdapter(adapter);
    }
    private void init4() {
        Item item1 = new Item("周杰伦", R.drawable.pic2);
        itemList4.add(item1);
        Item item2 = new Item("林俊杰", R.drawable.pic2);
        itemList4.add(item2);
        Item item3 = new Item("陈奕迅", R.drawable.pic2);
        itemList4.add(item3);
        Item item4 = new Item("李荣浩", R.drawable.pic2);
        itemList4.add(item4);
        Item item5 = new Item("助教哥哥", R.drawable.pic2);
        itemList4.add(item5);
        Item item6 = new Item("我", R.drawable.pic2);
        itemList4.add(item6);

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList4);
        ListView listView = (ListView) findViewById(R.id.listView4);
        listView.setAdapter(adapter);
    }
    private void init5() {

        ItemAdapter adapter = new ItemAdapter(MainActivity.this, R.layout.item, itemList);
        ListView listView = (ListView) findViewById(R.id.listView5);
        listView.setAdapter(adapter);
    }
}
