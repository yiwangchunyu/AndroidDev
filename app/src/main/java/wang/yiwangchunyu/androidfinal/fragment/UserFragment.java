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
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wang.yiwangchunyu.androidfinal.BaseApplication;
import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.SettingActivity;
import wang.yiwangchunyu.androidfinal.activity.NewUserInfoActivity;
import wang.yiwangchunyu.androidfinal.adapter.UserItemAdapter;
import wang.yiwangchunyu.androidfinal.bean.UserDemo;
import wang.yiwangchunyu.androidfinal.bean.UserItem;
import wang.yiwangchunyu.androidfinal.constants.UrlConstants;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.SharedHelper;
import wang.yiwangchunyu.androidfinal.utils.TitleBuilder;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;
import wang.yiwangchunyu.androidfinal.widget.WrapHeightListView;

/**
 *
 */

public class UserFragment extends BaseFragment {

    private static final String TAG = "UserFragment";
    private LinearLayout ll_userinfo;

    private ImageView iv_avatar;
    private TextView tv_subhead;
    private TextView tv_caption;

    private TextView tv_status_count;
    private TextView tv_follow_count;
    private TextView tv_fans_count;

    private WrapHeightListView lv_user_items;

    private UserDemo user;

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
        super.onHiddenChanged(hidden);
        RequestParams params = new RequestParams();
        SharedHelper sh = new SharedHelper(getContext());
        String userid = sh.read("userid");
        params.put("id", sh.read("userid"));
        if (!hidden) {
            AsyncHttpUtils.post("/user/getById", params, new TextHttpResponseHandler() {
                @Override
                public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                    Log.d(TAG, "fail: " + responseString);
                    ToastUtils.showToast(getContext(), "fail: " + responseString, Toast.LENGTH_SHORT);
                }

                @Override
                public void onSuccess(int statusCode, Header[] headers, String responseString) {
                    Log.d(TAG, responseString);
                    JSONObject jsonRes = null;
                    try {
                        jsonRes = new JSONObject(responseString);
                        if (jsonRes.getInt("code") == 0) {
                            String data = jsonRes.getJSONObject("data").toString();
//                            BaseApplication application = (BaseApplication) activity.getApplication();
                            UserDemo currentUser = new Gson().fromJson(data, UserDemo.class);
                            setUserInfo(currentUser);

                        } else {
                            ToastUtils.showToast(getContext(), jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                        }
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                }
            });


        }
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
        userItems.add(new UserItem(false, R.drawable.mine_newfriend128, "新的朋友", ""));
        userItems.add(new UserItem(false, R.drawable.mine_editor_blue128, "编辑资料", ""));
        userItems.add(new UserItem(true, R.drawable.mine_album128, "我的相册", "(18)"));
        userItems.add(new UserItem(false, R.drawable.mine_favarite128, "我的收藏", "(23)"));
        userItems.add(new UserItem(false, R.drawable.mine_like128, "我的赞", "(32)"));
        userItems.add(new UserItem(true, R.drawable.table128, "我的课表", ""));
        userItems.add(new UserItem(false, R.drawable.mine_badminton128, "校园运动", "组团打卡、场馆预约"));
        userItems.add(new UserItem(false, R.drawable.mine_water128, "宿舍电费", ""));
        userItems.add(new UserItem(true, R.drawable.mine_more128, "更多", ""));
        adapter.notifyDataSetChanged();
    }

    private void setUserInfo(UserDemo currentUser) {
//        user = ((BaseApplication) activity.getApplication()).currentUser;
        user = currentUser;

        if (user == null) {
            return;
        }

        // set data
        tv_subhead.setText(user.getNickname());
        tv_caption.setText("简介:" + "低调码农本汪");
        Glide.with(activity).load(UrlConstants.URL_MEDIA_PRE + user.getAvatar()).bitmapTransform(new CropCircleTransformation(activity)).into(iv_avatar);
        tv_status_count.setText("0");
        tv_follow_count.setText("0");
        tv_fans_count.setText("0");

        iv_avatar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String screen_name = user.getNickname();
                Intent intent = new Intent(activity, NewUserInfoActivity.class);
                intent.putExtra("screen_name", screen_name);
                startActivity(intent);
            }
        });
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
