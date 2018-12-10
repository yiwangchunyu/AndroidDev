package com.example.springrain.ch7project1;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;

/**
 * Created by SpringRain on 2018/11/26.
 */

public class ItemAdapter extends ArrayAdapter {
    private final int resourceId;
    public ItemAdapter(@NonNull Context context, int resource, List<Item> objects) {
        super(context, resource, objects);
        resourceId = resource;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        Item item = (Item) this.getItem(position); // 获取当前项的Item实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView name = (TextView) view.findViewById(R.id.name);//获取该布局内的文本视图
        TextView id = (TextView) view.findViewById(R.id.id);
        TextView price = (TextView) view.findViewById(R.id.price);
        name.setText(item.getName());//为文本视图设置文本内容
        id.setText(item.getId()+"");
        price.setText(item.getPrice()+"");
        return view;
    }
}
