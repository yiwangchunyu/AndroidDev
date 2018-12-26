package wang.yiwangchunyu.androidfinal;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by SpringRain on 2018/12/25.
 */

public class SharedHelper {

        private Context mContext;

        public SharedHelper() {
        }

        public SharedHelper(Context mContext) {
            this.mContext = mContext;
        }


        //定义一个保存数据的方法
        public void save(String username, String passwd) {
            SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
            SharedPreferences.Editor editor = sp.edit();
            editor.putString("username", username);
            editor.putString("passwd", passwd);
            editor.commit();
        }

        //定义一个读取SP文件的方法
        public Map<String, String> read() {
            Map<String, String> data = new HashMap<String, String>();
            SharedPreferences sp = mContext.getSharedPreferences("mysp", Context.MODE_PRIVATE);
            data.put("username", sp.getString("username", ""));
            data.put("passwd", sp.getString("passwd", ""));
            return data;
        }
}
