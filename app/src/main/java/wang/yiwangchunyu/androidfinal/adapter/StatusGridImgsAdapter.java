package wang.yiwangchunyu.androidfinal.adapter;

import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.RelativeLayout.LayoutParams;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.bean.PicUrls;

/**
 *
 */

public class StatusGridImgsAdapter extends BaseAdapter {
    private Context context;
    private ArrayList<PicUrls> datas;

    public StatusGridImgsAdapter(Context context, ArrayList<PicUrls> datas) {
        this.context = context;
        this.datas = datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_grid_image, null);
            holder.iv_image = (ImageView) convertView.findViewById(R.id.iv_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        GridView gv = (GridView) parent;
        int horizontalSpacing = gv.getHorizontalSpacing();
        int numColumns = gv.getNumColumns();
        int itemWidth = (gv.getWidth() - (numColumns - 1) * horizontalSpacing - gv.getPaddingLeft() - gv.getPaddingRight()) / numColumns;

        LayoutParams params = new LayoutParams(itemWidth, itemWidth);
        holder.iv_image.setLayoutParams(params);

        PicUrls urls = datas.get(position);
        Glide.with(context).load(urls.getOriginal_pic()).placeholder(R.drawable.pic_postion).into(holder.iv_image);
        return convertView;
    }

    public static class ViewHolder {
        public ImageView iv_image;
    }

}

