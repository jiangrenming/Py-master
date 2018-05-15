package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.view.CustomVideoView;
import com.yuqi.admin.py.view.RoundCornerButton;

import java.util.Timer;
import java.util.TimerTask;


public class SplashActivity extends BaseActivity implements View.OnClickListener {
    public static final String TAG = "SplashActivity";
    private CustomVideoView jzVideoPlayer;
    private RoundCornerButton splash_tv;

    private int recLen = 20;//跳过倒计时提示5秒
    Timer timer = new Timer();
    private Handler handler;
    private Runnable runnable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        jzVideoPlayer = (CustomVideoView) findViewById(R.id.splash_videoplayer);
        splash_tv = (RoundCornerButton) findViewById(R.id.splash_tv);
        splash_tv.setOnClickListener(this);
        jzVideoPlayer.setVideoPath("http://www.pengyoujuhui.com:8021/video/ydy.mp4");
        jzVideoPlayer.start();
        jzVideoPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mp.start();
                mp.setLooping(true);
            }
        });
        jzVideoPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                jzVideoPlayer.setVideoPath("http://www.pengyoujuhui.com:8021/video/ydy.mp4");
                jzVideoPlayer.start();
            }
        });

//        timer.schedule(task, 1000, 1000);//等待时间一秒，停顿时间一秒
//        /**
//         * 正常情况下不点击跳过
//         */
//        handler = new Handler();
//        handler.postDelayed(runnable = new Runnable() {
//            @Override
//            public void run() {
//
//                //从闪屏界面跳转到首界面
//                Intent intent = new Intent(SplashActivity.this, StartActivity.class);
//                startActivity(intent);
//                finish();
//            }
//        }, 20000);//延迟5S后发送handler信息

    }
//    TimerTask task = new TimerTask() {
//        @Override
//        public void run() {
//            runOnUiThread(new Runnable() { // UI thread
//                @Override
//                public void run() {
//                    recLen--;
//                    splash_tv.setText("跳过 " + recLen);
//                    if (recLen < 0) {
//                        timer.cancel();
//                        splash_tv.setVisibility(View.GONE);//倒计时到0隐藏字体
//                    }
//                }
//            });
//        }
//    };
    @Override
    public String title_text() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId())
        {
            case R.id.splash_tv:
                Intent intent = new Intent(SplashActivity.this, StartActivity.class);
                startActivity(intent);
                finish();
            break;
        }

    }

}
