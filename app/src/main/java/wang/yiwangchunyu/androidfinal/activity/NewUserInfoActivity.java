package wang.yiwangchunyu.androidfinal.activity;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import cz.msebera.android.httpclient.Header;
import jp.wasabeef.glide.transformations.CropCircleTransformation;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.bean.UserDemo;
import wang.yiwangchunyu.androidfinal.constants.UrlConstants;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.SharedHelper;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;

import static wang.yiwangchunyu.androidfinal.BaseApplication.getContext;

public class NewUserInfoActivity extends AppCompatActivity {
    public static final String TAG = "NewUserInfoActivity";
    //用户个人信息
    private ImageView iv_user_head;
    private TextView tv_user_name;
    private TextView tv_user_fans;
    private TextView tv_user_desc;
    // 用户相关信息
    private boolean isCurrentUser;
    private UserDemo user;
    private String screenName;
    private Context mContext;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setStatusBarColor();

        mContext = this;

        //沉浸式状态栏效果
        if (Build.VERSION.SDK_INT >= 21) {
            View decorView = getWindow().getDecorView();
            decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }
        setContentView(R.layout.activity_new_user_info);
        screenName = getIntent().getStringExtra("screen_name");
        Log.d(TAG, screenName);

        if (TextUtils.isEmpty(screenName)) {
            isCurrentUser = false;
            user = getContext().currentUser;
        }

        initUserInfo();
        initCL();
        loadData();

    }

    private void setStatusBarColor() {
        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS
                    | WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
            window.setNavigationBarColor(Color.TRANSPARENT);
        }

    }

    private void loadData() {
        if (isCurrentUser) {
            // 如果是当前授权用户,直接设置信息
            setUserInfo();
        } else {
            // 如果是查看他人,调用获取用户信息接口
            loadUserInfo();
        }
    }

    private void setUserInfo() {
        if (user == null) {
            return;
        }
        tv_user_name.setText(user.getNickname());
        Glide.with(this).load(UrlConstants.URL_MEDIA_PRE+user.getAvatar()).bitmapTransform(new CropCircleTransformation(this)).placeholder(R.drawable.head_pistion).into(iv_user_head);
        tv_user_fans.setText("关注  " + 0 + " | " + "粉丝  " + 0);
        tv_user_desc.setText("简介:" + "华师大学生党");
    }

    private void loadUserInfo() {
        RequestParams params = new RequestParams();
        params.put("id", new SharedHelper(mContext).read("userid"));

        AsyncHttpUtils.post("/user/getById", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
                ToastUtils.showToast(mContext, "fail: " + responseString, Toast.LENGTH_LONG);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "success:"+responseString);
                JSONObject jsonRes = null;
                try {
                    jsonRes = new JSONObject(responseString);
                    if (jsonRes.getInt("code") == 0) {
                        String data = jsonRes.getJSONObject("data").toString();
//                            BaseApplication application = (BaseApplication) activity.getApplication();
                        user = new Gson().fromJson(data, UserDemo.class);
                        setUserInfo();

                    } else {
                        ToastUtils.showToast(mContext, jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void initUserInfo() {
        iv_user_head = (ImageView) findViewById(R.id.iv_user_head);
        tv_user_name = (TextView) findViewById(R.id.tv_user_name);
        tv_user_fans = (TextView) findViewById(R.id.tv_user_fans);
        tv_user_desc = (TextView) findViewById(R.id.tv_user_desc);

    }

    private void initCL() {
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        CollapsingToolbarLayout collapsingToolbar = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        //设置CollapsingToolbarLayout的标题文字
        collapsingToolbar.setTitle(" ");
        //设置ViewPager
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        //设置tablayout，viewpager上的标题
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setSelectedTabIndicatorColor(getResources().getColor(R.color.orange));
    }

    private void setupViewPager(ViewPager viewPager) {
//        UserInfoAdapter adapter = new UserInfoAdapter(getSupportFragmentManager());
//        adapter.addFragment(new UserHomeFragment(), "主页");
//        adapter.addFragment(new UserHomeFragment(), "发布");
//        adapter.addFragment(new UserHomeFragment(), "相册");
//        viewPager.setAdapter(adapter);
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        getMenuInflater().inflate(R.menu.toolbar_actions, menu);
//        return super.onCreateOptionsMenu(menu);
//
//    }
}
