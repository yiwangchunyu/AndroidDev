package wang.yiwangchunyu.androidfinal.fragment;

import android.content.Context;
import android.os.Bundle;
import android.preference.PreferenceActivity;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.ButterKnife;
import cz.msebera.android.httpclient.Header;
import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.adapter.StatusAdapterDemo;
import wang.yiwangchunyu.androidfinal.bean.StatusDemo;
import wang.yiwangchunyu.androidfinal.bean.StatusTimeLineResponseDemo;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.Logger;
import wang.yiwangchunyu.androidfinal.utils.TitleBuilder;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;

/**
 *
 * Home
 */

public class WeiboFragment extends BaseFragment {
    private static final String TAG = "WeiboFragment";
    private SwipeToLoadLayout swipeToLoadLayout;
    private ListView lvHome;
    private View view;
    private StatusAdapterDemo adapter;
    private List<StatusDemo> statuses = new ArrayList<>();
    private int curPage = 1;
    private GestureDetector gestureDetector;
    private View view_search;
    private Context mContent;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mContent = getContext();
        initView();
        initData(1);
        ButterKnife.bind(this, view);
        return view;
    }

    private void initView() {
        view_search = View.inflate(getContext(), R.layout.include_searchview, null);
        view = View.inflate(activity, R.layout.frag_weibo, null);
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        lvHome = (ListView) view.findViewById(R.id.swipe_target);
        new TitleBuilder(view)
                .setLeftImage(R.drawable.title_camre)
                .setRightImage(R.drawable.title_sacn)
                .setTitleText("首页")
                .setRightOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "扫一扫", Toast.LENGTH_LONG);
                    }
                })
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ToastUtils.showToast(activity, "相机", Toast.LENGTH_SHORT);
                    }
                });


        lvHome.addHeaderView(view_search);
        adapter = new StatusAdapterDemo(activity, statuses);
        lvHome.setAdapter(adapter);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                initData(1);
                swipeToLoadLayout.setRefreshing(false);

            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                initData(curPage + 1);
                swipeToLoadLayout.setLoadingMore(false);
            }
        });

        //双击事件处理
        RadioButton radioButton = (RadioButton) getActivity().findViewById(R.id.rb_home);
        radioButton.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                return gestureDetector.onTouchEvent(event);
            }
        });
        gestureDetector = new GestureDetector(getContext(), new GestureDetector.SimpleOnGestureListener() {
            @Override
            public boolean onDoubleTap(MotionEvent e) {
                ToastUtils.showToast(getActivity(), "双击事件", Toast.LENGTH_LONG);
                lvHome.setSelection(0);
                swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
                    @Override
                    public void onRefresh() {
                        initData(1);
                        swipeToLoadLayout.setRefreshing(false);

                    }
                });
                return super.onDoubleTap(e);
            }
        });


    }

    private void initData(final int page) {
        RequestParams params = new RequestParams();
        AsyncHttpUtils.post("/dynamic/getAll", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
                ToastUtils.showToast(mContent, "fail: " + responseString, Toast.LENGTH_SHORT);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "success: " + responseString);
                JSONObject jsonRes = null;
                try {
                    jsonRes = new JSONObject(responseString);
                    if (jsonRes.getInt("code") == 0) {
                        String data = jsonRes.getJSONObject("data").toString();
                        StatusTimeLineResponseDemo timeLineResponse = new Gson().fromJson(data, StatusTimeLineResponseDemo.class);
                        lvHome.setAdapter(new StatusAdapterDemo(activity, timeLineResponse.getStatuses()));

                        if (page == 1) {
                            statuses.clear();
                        }
                        curPage = page;
                        lvHome.setAdapter(new StatusAdapterDemo(activity, timeLineResponse.getStatuses()));
                        addStatus(new Gson().fromJson(data, StatusTimeLineResponseDemo.class));

                    } else {
                        ToastUtils.showToast(mContent, jsonRes.getString("msg"), Toast.LENGTH_SHORT);
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    private void addStatus(StatusTimeLineResponseDemo resBean) {
        for (StatusDemo status : resBean.getStatuses()) {
            if (!statuses.contains(status)) {
                statuses.add(status);
            }
        }
        adapter.notifyDataSetChanged();

    }


}
