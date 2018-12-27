package wang.yiwangchunyu.androidfinal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.widget.GlideImageLoader;


/**
 *
 */

public class SearchFragment extends BaseFragment {
    List<String> images = Arrays.asList(
            "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg"
            , "http://img4.imgtn.bdimg.com/it/u=4019396207,3823344333&fm=26&gp=0.jpg"
            , "http://cdn3.nflximg.net/images/3093/2043093.jpg"
            , "https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=447637348,2082257628&fm=26&gp=0.jpg");

    private View view;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }
}
