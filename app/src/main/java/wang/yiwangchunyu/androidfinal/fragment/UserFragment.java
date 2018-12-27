package wang.yiwangchunyu.androidfinal.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;
import wang.yiwangchunyu.androidfinal.BaseApplication;
import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.SettingActivity;
import wang.yiwangchunyu.androidfinal.adapter.UserItemAdapter;
import wang.yiwangchunyu.androidfinal.bean.User;
import wang.yiwangchunyu.androidfinal.bean.UserItem;
import wang.yiwangchunyu.androidfinal.utils.TitleBuilder;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;
import wang.yiwangchunyu.androidfinal.widget.WrapHeightListView;

/**
 *
 */

public class UserFragment extends BaseFragment {

    private LinearLayout ll_userinfo;

    private ImageView iv_avatar;
    private TextView tv_subhead;
    private TextView tv_caption;

    private TextView tv_status_count;
    private TextView tv_follow_count;
    private TextView tv_fans_count;

    private WrapHeightListView lv_user_items;

    private User user;

    Unbinder unbinder;
    private View view;
    private List<UserItem> userItems;
    private UserItemAdapter adapter;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_user, null);
        initView();
        setItem();

        unbinder = ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onHiddenChanged(boolean hidden) {
//        super.onHiddenChanged(hidden);
//        Oauth2AccessToken mAccessToken = AccessTokenKeeper.readAccessToken(activity);
//        String token = mAccessToken.getToken();
//        String uid = mAccessToken.getUid();
//        RequestParams params = new RequestParams();
//        params.put("access_token", token);
//        params.put("uid", uid);
//        if (!hidden) {
//            AsyncHttpUtils.get("users/show.json", params, new TextHttpResponseHandler() {
//                @Override
//                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
//                    Log.d("usershow", responseString);
//                }
//
//                @Override
//                public void onSuccess(int statusCode, Header[] headers, String responseString) {
//                    ToastUtils.showToast(activity, "onSuccess", Toast.LENGTH_SHORT);
//                    Log.d("usershow", responseString);
//                    BaseApplication application = (BaseApplication) activity.getApplication();
//                    application.currentUser = new Gson().fromJson(responseString, User.class);
//                    setUserInfo();
//                }
//            });
//
//
//        }
    }

    private void initView() {
        new TitleBuilder(view)
                .setTitleText("我")
                .setLeftText("添加好友")
                .setRightText("设置")
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        intent2Activity(SettingActivity.class);

                    }
                })
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(getActivity(),"添加好友",Toast.LENGTH_LONG);

                    }
                });
        tv_status_count = (TextView) view.findViewById(R.id.tv_status_count);
        tv_follow_count = (TextView) view.findViewById(R.id.tv_follow_count);
        tv_fans_count = (TextView) view.findViewById(R.id.tv_fans_count);

        iv_avatar = (ImageView) view.findViewById(R.id.iv_avatar);
        tv_subhead = (TextView) view.findViewById(R.id.tv_subhead);
        tv_caption = (TextView) view.findViewById(R.id.tv_caption);

        lv_user_items = (WrapHeightListView) view.findViewById(R.id.lv_user_items);
        userItems = new ArrayList<>();
        adapter = new UserItemAdapter(activity, userItems);
        lv_user_items.setAdapter(adapter);
    }

    private void setItem() {
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_1, "新的朋友", ""));
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_2, "微博等级", "Lv13"));
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_3, "编辑资料", ""));
        userItems.add(new UserItem(true, R.drawable.push_icon_app_small_4, "我的相册", "(18)"));
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_5, "我的点评", ""));
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_4, "我的赞", "(32)"));
        userItems.add(new UserItem(true, R.drawable.push_icon_app_small_3, "微博支付", ""));
        userItems.add(new UserItem(false, R.drawable.push_icon_app_small_2, "微博运动", "步数、卡路里、跑步轨迹"));
        userItems.add(new UserItem(true, R.drawable.push_icon_app_small_1, "更多", "收藏、名片"));
        adapter.notifyDataSetChanged();
    }

    private void setUserInfo() {
//        user = ((BaseApplication) activity.getApplication()).currentUser;
//
//        if (user == null) {
//            return;
//        }
//
//        // set data
//        tv_subhead.setText(user.getName());
//        tv_caption.setText("简介:" + user.getDescription());
//        Glide.with(activity).load(user.getAvatar_hd()).bitmapTransform(new CropCircleTransformation(activity)).into(iv_avatar);
//        tv_status_count.setText("" + user.getStatuses_count());
//        tv_follow_count.setText("" + user.getFriends_count());
//        tv_fans_count.setText("" + user.getFollowers_count());
//
//        iv_avatar.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String screen_name = user.getScreen_name();
//                Intent intent = new Intent(activity, NewUserInfoActivity.class);
//                intent.putExtra("screen_name", screen_name);
//                startActivity(intent);
//            }
//        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
