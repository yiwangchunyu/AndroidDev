package com.example.springrain.ch5project3;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

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

        Item fruit = (Item) this.getItem(position); // 获取当前项的Item实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        ImageView fruitImage = (ImageView) view.findViewById(R.id.image);//获取该布局内的图片视图
        TextView fruitName = (TextView) view.findViewById(R.id.content);//获取该布局内的文本视图
        fruitImage.setImageResource(fruit.getImageId());//为图片视图设置图片资源
        fruitName.setText(fruit.getContent());//为文本视图设置文本内容
        return view;
    }
}
