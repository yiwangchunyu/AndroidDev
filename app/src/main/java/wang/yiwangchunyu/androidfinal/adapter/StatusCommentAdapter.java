package wang.yiwangchunyu.androidfinal.adapter;

import android.content.Context;
import android.text.SpannableString;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;

import java.util.List;

import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.bean.Comment;
import wang.yiwangchunyu.androidfinal.bean.User;
import wang.yiwangchunyu.androidfinal.utils.DateUtils;
import wang.yiwangchunyu.androidfinal.utils.StringUtils;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;

/**
 *
 */

public class StatusCommentAdapter extends BaseAdapter {
    private Context context;
    private List<Comment> comments;


    public StatusCommentAdapter(Context context, List<Comment> comments) {
        this.context = context;
        this.comments = comments;
    }

    @Override
    public int getCount() {
        return comments.size();
    }

    @Override
    public Object getItem(int position) {
        return comments.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View view, ViewGroup parent) {
        StatusCommentAdapter.ViewHolder holder;
        if (view == null) {
            holder = new StatusCommentAdapter.ViewHolder();
            view = View.inflate(context, R.layout.item_comment, null);
            holder.ll_comments = (LinearLayout) view.findViewById(R.id.ll_comments);
            holder.iv_head = (ImageView) view.findViewById(R.id.iv_head);
            holder.tv_head_name = (TextView) view.findViewById(R.id.tv_head_name);
            holder.tv_head_desc = (TextView) view.findViewById(R.id.tv_head_desc);
            holder.tv_comment = (TextView) view.findViewById(R.id.tv_comment);
            view.setTag(holder);
        } else {
            holder = (ViewHolder) view.getTag();
        }
        Comment comment = (Comment) getItem(position);
        final User user = comment.getUser();
        Glide.with(context).load(user.getAvatar_hd()).bitmapTransform(new CropCircleTransformation(context)).into(holder.iv_head);
        holder.tv_head_name.setText(user.getName());
        holder.tv_head_desc.setText(DateUtils.getShortTime(comment.getCreated_at()));
        SpannableString weiboContent = StringUtils.getWeiboContent(context, holder.tv_comment, comment.getText());
        holder.tv_comment.setText(weiboContent);
        holder.ll_comments.setOnClickListener(new View.OnClickListener
                () {
            @Override
            public void onClick(View v) {
                ToastUtils.showToast(context, "回复评论", Toast.LENGTH_SHORT);
            }
        });

        return view;
    }

    static class ViewHolder {
        LinearLayout ll_comments;
        ImageView iv_head;
        TextView tv_head_name;
        TextView tv_head_desc;
        TextView tv_comment;
    }
}
