package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/11/24.
 * 发布成功
 */
public class PublishSuccessfullyActivity extends BaseActivity {
    private TextView countDown,v1,v2,v3;
    private int recLen = 3;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wycg_fabuchenggong);
        init();
    }

    private void init() {
        v1 =(TextView) findViewById(R.id.tv1);
        v2 =(TextView) findViewById(R.id.tv2);
        v3 =(TextView) findViewById(R.id.tv3);
        countDown =(TextView) findViewById(R.id.t4);
        timer.schedule(task, 1000, 1000);       // timeTask
    }

    TimerTask task = new TimerTask() {
        @Override
        public void run() {

            runOnUiThread(new Runnable() {      // UI thread
                @Override
                public void run() {
                    recLen--;
                    countDown.setText(""+recLen+"s");
                    if(recLen ==3){
                        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) v1.getLayoutParams();
                        lp1.width = 36;
                        lp1.height=36;//lp.height=LayoutParams.WRAP_CONTENT;
                        v1.setLayoutParams(lp1);
                        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) v2.getLayoutParams();
                        lp2.width = 24;
                        lp2.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v2.setLayoutParams(lp2);
                        LinearLayout.LayoutParams lp3 = (LinearLayout.LayoutParams) v3.getLayoutParams();
                        lp3.width = 24;
                        lp3.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v3.setLayoutParams(lp3);
                    }else if (recLen == 2){
                        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) v1.getLayoutParams();
                        lp1.width = 24;
                        lp1.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v1.setLayoutParams(lp1);
                        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) v2.getLayoutParams();
                        lp2.width = 36;
                        lp2.height=36;//lp.height=LayoutParams.WRAP_CONTENT;
                        v2.setLayoutParams(lp2);
                        LinearLayout.LayoutParams lp3 = (LinearLayout.LayoutParams) v3.getLayoutParams();
                        lp3.width = 24;
                        lp3.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v3.setLayoutParams(lp3);
                    }else if (recLen == 1){
                        LinearLayout.LayoutParams lp1 = (LinearLayout.LayoutParams) v1.getLayoutParams();
                        lp1.width = 24;
                        lp1.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v1.setLayoutParams(lp1);
                        LinearLayout.LayoutParams lp2 = (LinearLayout.LayoutParams) v2.getLayoutParams();
                        lp2.width = 24;
                        lp2.height=24;//lp.height=LayoutParams.WRAP_CONTENT;
                        v2.setLayoutParams(lp2);
                        LinearLayout.LayoutParams lp3 = (LinearLayout.LayoutParams) v3.getLayoutParams();
                        lp3.width = 36;
                        lp3.height=36;//lp.height=LayoutParams.WRAP_CONTENT;
                        v3.setLayoutParams(lp3);
                    }

                    if(recLen < 1){
                        Intent intent = new Intent();
                        intent.setClass(PublishSuccessfullyActivity.this,ShouyActivity.class);
                        startActivity(intent);
                        timer.cancel();
                        finish();
                    }
                }
            });
        }
    };


    @Override
    public String title_text() {
        return "发布成功";
    }

    @Override
    public void onClick(View v) {
    }
}
