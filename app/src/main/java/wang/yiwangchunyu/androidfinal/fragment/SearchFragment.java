package wang.yiwangchunyu.androidfinal.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.youth.banner.Banner;

import java.util.Arrays;
import java.util.List;

import wang.yiwangchunyu.androidfinal.BaseFragment;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.widget.GlideImageLoader;


/**
 *
 */

public class SearchFragment extends BaseFragment implements View.OnClickListener {
    List<String> images = Arrays.asList(
            "http://5b0988e595225.cdn.sohucs.com/images/20180624/fdeac61b6061410487c71122855b194d.jpeg"
            , "http://file.xdf.cn/uploads/180529/1092_180529173911e9XVe69S2w4Lgg91.jpg"
            , "http://bbswater-fd.zol-img.com.cn/t_s1200x5000/g5/M00/00/00/ChMkJ1aLM_uIZ2LgABMUH-oLejcAAG_OgE7eYAAExQ3858.jpg"
            , "http://www.people.com.cn/mediafile/pic/20171026/25/18375107741788349577.jpg");

    private View view;
    TextView tvLostFound;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        view = View.inflate(activity, R.layout.frag_search, null);
        tvLostFound = (TextView) view.findViewById(R.id.tv_lostfound);
        tvLostFound.setOnClickListener(this);
        Banner banner = (Banner) view.findViewById(R.id.banner);
        //设置图片加载器
        banner.setImageLoader(new GlideImageLoader());
        //设置图片集合
        banner.setImages(images);
        //banner设置方法全部调用完毕时最后调用
        banner.start();
        return view;
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.tv_lostfound:
                showLostFound();
                break;
            default:
                break;
        }
    }

    private void showLostFound() {

    }
}
