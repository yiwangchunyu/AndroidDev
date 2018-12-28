package wang.yiwangchunyu.androidfinal.adapter;

import android.content.Context;
import android.content.Intent;
import android.text.Html;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.ArrayList;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wang.yiwangchunyu.androidfinal.ImageBrowserActivity;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.bean.StatusLostFoundItem;
import wang.yiwangchunyu.androidfinal.utils.StringUtils;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;

public class StatusAdapterLostFound extends BaseAdapter {
    private Context context;
    private ArrayList<StatusLostFoundItem> datas;
    public StatusAdapterLostFound(Context context, ArrayList<StatusLostFoundItem> datas) {
        this.context=context;
        this.datas=datas;
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

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final StatusAdapterLostFound.ViewHolder holder;
        if (convertView == null) {
            holder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_status, null);
            holder.ll_card_content = (LinearLayout) convertView.findViewById(R.id.ll_card_content);
            holder.iv_head = (ImageView) convertView.findViewById(R.id.iv_head);
            holder.rl_content = (RelativeLayout) convertView.findViewById(R.id.rl_content);
            holder.tv_head_name = (TextView) convertView.findViewById(R.id.tv_head_name);
            holder.tv_head_desc = (TextView) convertView.findViewById(R.id.tv_head_desc);

            holder.tv_content = (TextView) convertView.findViewById(R.id.tv_content);
            holder.include_status_image = (FrameLayout) convertView.findViewById(R.id.include_status_image);
            holder.gv_images = (GridView) holder.include_status_image.findViewById(R.id.gv_images);
            holder.iv_image = (ImageView) holder.include_status_image.findViewById(R.id.iv_image);

            holder.include_retweeted_status = (LinearLayout) convertView.findViewById(R.id.include_retweeted_status);
            holder.tv_retweeted_content = (TextView) holder.include_retweeted_status.findViewById(R.id.tv_retweeted_content);
            holder.include_retweeted_status_image = (FrameLayout) holder.include_retweeted_status.findViewById(R.id.include_status_image);
            holder.gv_retweeted_images = (GridView) holder.include_retweeted_status_image.findViewById(R.id.gv_images);
            holder.iv_retweeted_image = (ImageView) holder.include_retweeted_status_image.findViewById(R.id.iv_image);

            holder.ll_share_bottom = (LinearLayout) convertView.findViewById(R.id.ll_share_bottom);
            holder.iv_share_bottom = (ImageView) convertView.findViewById(R.id.iv_weibo_bottom_retweet);
            holder.tv_share_bottom = (TextView) convertView.findViewById(R.id.tv_weibo_bottom_retweet);
            holder.ll_comment_bottom = (LinearLayout) convertView.findViewById(R.id.ll_comment_bottom);
            holder.iv_comment_bottom = (ImageView) convertView.findViewById(R.id.iv_weibo_bottom_comment);
            holder.tv_comment_bottom = (TextView) convertView.findViewById(R.id.tv_weibo_bottom_comment);
            holder.ll_like_bottom = (LinearLayout) convertView.findViewById(R.id.ll_like_bottom);
            holder.iv_like_bottom = (ImageView) convertView.findViewById(R.id.iv_weibo_bottom_like);
            holder.tv_like_bottom = (TextView) convertView.findViewById(R.id.tv_weibo_bottom_like);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        //用户
        final StatusLostFoundItem statusLostFoundItem = (StatusLostFoundItem) getItem(position);
        Glide.with(context).load(statusLostFoundItem.getAvatarUrl()).bitmapTransform(new CropCircleTransformation(context)).placeholder(R.drawable.head_pistion).into(holder.iv_head);
        holder.tv_head_name.setText(statusLostFoundItem.getNickName());
        holder.tv_head_desc.setText(statusLostFoundItem.getSubmission_time() + "  |  来自 " + statusLostFoundItem.getType());
        //正文
        holder.tv_content.setText(StringUtils.getWeiboContent(context, holder.tv_content, statusLostFoundItem.getMsg()));
        setImages(statusLostFoundItem, holder.include_status_image, holder.gv_images, holder.iv_image);

        //转发内容
        holder.include_retweeted_status.setVisibility(View.GONE);

        //转发评论点赞
        holder.tv_share_bottom.setText("转发");
        holder.ll_share_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(context, "转发", Toast.LENGTH_LONG);
            }
        });
        holder.tv_like_bottom.setText("赞");
        holder.ll_like_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(context, "点赞", Toast.LENGTH_LONG);
            }
        });
        holder.tv_comment_bottom.setText("评论");
        holder.ll_comment_bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(context, "评论", Toast.LENGTH_LONG);
            }
        });
        return convertView;
    }

    //图片设置处理
    private void setImages(final StatusLostFoundItem statusLostFoundItem, FrameLayout imgContainer, GridView gv_images, ImageView iv_image) {
        ArrayList<String> pic_urls = statusLostFoundItem.getImage_url();

        if (pic_urls != null && pic_urls.size() >= 1) {
            imgContainer.setVisibility(View.VISIBLE);
            gv_images.setVisibility(View.VISIBLE);
            iv_image.setVisibility(View.GONE);
            StatusGridImgsAdapter gvAdapter = new StatusGridImgsAdapter(context, pic_urls);
            gv_images.setAdapter(gvAdapter);
            gv_images.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Intent intent = new Intent(context, ImageBrowserActivity.class);
                    intent.putExtra("status", statusLostFoundItem);
                    intent.putExtra("position", position);
                    context.startActivity(intent);
                }
            });

        } else {
            imgContainer.setVisibility(View.GONE);
        }
    }

    static class ViewHolder {
        LinearLayout ll_card_content;
        ImageView iv_head;
        RelativeLayout rl_content;
        TextView tv_head_name;
        TextView tv_head_desc;

        TextView tv_content;

        FrameLayout include_status_image;
        GridView gv_images;
        ImageView iv_image;

        LinearLayout include_retweeted_status;
        TextView tv_retweeted_content;
        FrameLayout include_retweeted_status_image;
        GridView gv_retweeted_images;
        ImageView iv_retweeted_image;

        LinearLayout ll_share_bottom;
        ImageView iv_share_bottom;
        TextView tv_share_bottom;
        LinearLayout ll_comment_bottom;
        ImageView iv_comment_bottom;
        TextView tv_comment_bottom;
        LinearLayout ll_like_bottom;
        ImageView iv_like_bottom;
        TextView tv_like_bottom;
    }
}
