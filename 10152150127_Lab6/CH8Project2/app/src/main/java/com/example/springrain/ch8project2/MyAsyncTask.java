package com.example.springrain.ch8project2;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

/**
 * Created by SpringRain on 2018/12/17.
 */

public class MyAsyncTask extends AsyncTask<Integer, Integer, String> {
    private TextView txt;
    private ProgressBar pgbar, pgbar2;
    private Button bt1, bt2;

    public MyAsyncTask(TextView txt, Button bt1, Button bt2, ProgressBar pgbar, ProgressBar pgbar2) {
        super();
        this.txt = txt;
        this.pgbar = pgbar;
        this.pgbar2 = pgbar2;
        this.bt1 = bt1;
        this.bt2 = bt2;
    }


    //该方法不运行在UI线程中,主要用于异步操作,通过调用publishProgress()方法
    //触发onProgressUpdate对UI进行操作
    @Override
    protected String doInBackground(Integer... params) {
        int i;
        Log.d("doInBackground", "|" + params[0].intValue());
        for (i = params[0].intValue(); i < 100; i += 1) {
            if (isCancelled()) {
                break;
            }
            Log.d("doInBackground for", "|" + i);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            publishProgress(i + 1);
        }
        return "" + (i);
    }

    //该方法运行在UI线程中,可对UI控件进行设置
    @Override
    protected void onPreExecute() {

    }


    //在doBackground方法中,每次调用publishProgress方法都会触发该方法
    //运行在UI线程中,可对UI控件进行操作


    @Override
    protected void onProgressUpdate(Integer... values) {
        int value = values[0];
        txt.setText("" + value);
        pgbar.setProgress(value);
    }

    @Override
    protected void onPostExecute(String s) {
        super.onPostExecute(s);
        txt.setText(s);
        bt1.setEnabled(false);
        bt2.setEnabled(false);
        pgbar2.setVisibility(View.INVISIBLE);
    }
}