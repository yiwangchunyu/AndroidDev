package wang.yiwangchunyu.androidfinal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.aspsine.swipetoloadlayout.OnLoadMoreListener;
import com.aspsine.swipetoloadlayout.OnRefreshListener;
import com.aspsine.swipetoloadlayout.SwipeToLoadLayout;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;
import com.youth.banner.Banner;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import cz.msebera.android.httpclient.Header;
import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.adapter.StatusAdapterDemo;
import wang.yiwangchunyu.androidfinal.adapter.StatusAdapterLostFound;
import wang.yiwangchunyu.androidfinal.bean.StatusLostFoundItem;
import wang.yiwangchunyu.androidfinal.bean.StatusTimeLineResponseDemo;
import wang.yiwangchunyu.androidfinal.utils.AsyncHttpUtils;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;
import wang.yiwangchunyu.androidfinal.widget.GlideImageLoader;


/**
 *
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {
    private static final String TAG = "SearchFragment";
    List<String> images = Arrays.asList(
            "http://5b0988e595225.cdn.sohucs.com/images/20180624/fdeac61b6061410487c71122855b194d.jpeg"
            , "http://file.xdf.cn/uploads/180529/1092_180529173911e9XVe69S2w4Lgg91.jpg"
            , "http://bbswater-fd.zol-img.com.cn/t_s1200x5000/g5/M00/00/00/ChMkJ1aLM_uIZ2LgABMUH-oLejcAAG_OgE7eYAAExQ3858.jpg"
            , "http://www.people.com.cn/mediafile/pic/20171026/25/18375107741788349577.jpg");

    private View view;
    TextView tvLostFound, tvECNUFresh, tvTody, tvSecondShop;
    private ListView lvHome;
    SwipeToLoadLayout swipeToLoadLayout;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        tvLostFound = (TextView) view.findViewById(R.id.tv_lostfound);
        tvLostFound.setOnClickListener(this);
        tvECNUFresh = (TextView) view.findViewById(R.id.tv_ECNUFresh);
        tvECNUFresh.setOnClickListener(this);
        tvTody = (TextView) view.findViewById(R.id.tv_tody);
        tvTody.setOnClickListener(this);
        tvSecondShop = (TextView) view.findViewById(R.id.tv_shop);
        tvSecondShop.setOnClickListener(this);
        lvHome = (ListView) view.findViewById(R.id.swipe_target);

        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        swipeToLoadLayout = (SwipeToLoadLayout) view.findViewById(R.id.swipeToLoadLayout);
        swipeToLoadLayout.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                swipeToLoadLayout.setRefreshing(false);

            }
        });

        swipeToLoadLayout.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                swipeToLoadLayout.setLoadingMore(false);
            }
        });
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_lostfound:
                showLostFound();
                break;
            default:
                lvHome.setAdapter(null);
                Toast.makeText(activity, "clean", Toast.LENGTH_SHORT).show();
                break;
        }
    }

    private void showLostFound() {
        RequestParams params = new RequestParams();
        new AsyncHttpClient().get("https://lostandfound.yiwangchunyu.wang/index/index_publish_info.php", params, new TextHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d(TAG, "fail: " + responseString);
                ToastUtils.showToast(getContext(), "fail: " + responseString, Toast.LENGTH_SHORT);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, String responseString) {
                Log.d(TAG, "getLostFound success: " + responseString);
                ToastUtils.showToast(getContext(), "success", Toast.LENGTH_SHORT);
                ArrayList<StatusLostFoundItem> lostFoundItems = new ArrayList<StatusLostFoundItem>();
                JSONArray jsonRes = null;
                try {
                    jsonRes = new JSONArray(responseString);
                    for(int i=0;i<jsonRes.length();i++){
                        if(i>20){
                            break;
                        }
                        lostFoundItems.add(new Gson().fromJson(jsonRes.getJSONObject(i).toString(), StatusLostFoundItem.class));
                    }
                    lvHome.setAdapter(new StatusAdapterLostFound(activity, lostFoundItems));
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
