package wang.yiwangchunyu.androidfinal.activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import wang.yiwangchunyu.androidfinal.BaseActivity;
import wang.yiwangchunyu.androidfinal.R;
import wang.yiwangchunyu.androidfinal.SettingActivity;
import wang.yiwangchunyu.androidfinal.adapter.UserItemAdapter;
import wang.yiwangchunyu.androidfinal.adapter.UserProfileEditorItemAdapter;
import wang.yiwangchunyu.androidfinal.bean.UserItem;
import wang.yiwangchunyu.androidfinal.utils.TitleBuilder;
import wang.yiwangchunyu.androidfinal.utils.ToastUtils;
import wang.yiwangchunyu.androidfinal.widget.WrapHeightListView;

public class ProfileEditorActivity extends BaseActivity {

    View view;
    WrapHeightListView lv_user_items;
    private List<UserItem> userItems;
    UserProfileEditorItemAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_editor);
        view = ((ViewGroup)findViewById(android.R.id.content)).getChildAt(0);
        new TitleBuilder(view)
                .setTitleText("编辑资料")
                .setLeftImage(R.drawable.close)
                .setLeftOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                       finish();
                    }
                });

//        lv_user_items = (WrapHeightListView) view.findViewById(R.id.lv_user_items);
//        userItems = new ArrayList<>();
//        adapter = new UserProfileEditorItemAdapter(this, userItems);
//        lv_user_items.setAdapter(adapter);

    }
}
