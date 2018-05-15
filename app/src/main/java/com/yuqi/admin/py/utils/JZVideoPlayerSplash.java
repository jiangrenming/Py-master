package com.yuqi.admin.py.utils;


import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import com.yuqi.admin.py.bean.APPHomePageBean;

import cn.jzvd.JZUserAction;
import cn.jzvd.JZUserActionStandard;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2018/3/19/019.
 */

public class JZVideoPlayerSplash extends JZVideoPlayerStandard{


    public JZVideoPlayerSplash(Context context) {
        super(context);
    }

    public JZVideoPlayerSplash(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void init(Context context) {
        super.init(context);
    }

    @Override
    public void setUp(String url, int screen, Object... objects) {
        super.setUp(url, screen, objects);
        if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
            startButton.setClickable(false);
            titleTextView.setVisibility(View.GONE);
            batteryLevel.setVisibility(View.GONE);
            videoCurrentTime.setVisibility(View.GONE);
            replayTextView.setVisibility(View.GONE);
            clarity.setVisibility(View.GONE);
            backButton.setVisibility(View.GONE);
            batteryTimeLayout.setVisibility(View.GONE);
            mRetryLayout.setVisibility(View.GONE);
            tinyBackImageView.setVisibility(View.GONE);
            setAllControlsVisiblity(View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE);
            textureViewContainer.setClickable(false);
            bottomContainer.setVisibility(View.GONE);
            progressBar.setVisibility(View.GONE);
        }
        if(currentState == CURRENT_STATE_PREPARING){
            loadingProgressBar.setVisibility(View.GONE);
        }

    }
    @Override
    public void onClick(View v) {
        super.onClick(v);
        int i = v.getId();
        if (i == cn.jzvd.R.id.fullscreen) {
            if (currentScreen == SCREEN_WINDOW_FULLSCREEN) {
                startButton.setClickable(false);
                titleTextView.setVisibility(View.GONE);
                batteryLevel.setVisibility(View.GONE);
                videoCurrentTime.setVisibility(View.GONE);
                replayTextView.setVisibility(View.GONE);
                clarity.setVisibility(View.GONE);
                backButton.setVisibility(View.GONE);
                batteryTimeLayout.setVisibility(View.GONE);
                mRetryLayout.setVisibility(View.GONE);
                tinyBackImageView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);
                setAllControlsVisiblity(View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE, View.GONE);
            }

        }
    }



    @Override
    public void onClickUiToggle() {
        super.onClickUiToggle();
        bottomContainer.setVisibility(View.GONE);

    }

    @Override
    public void onCompletion() {
        super.onCompletion();

    }

    @Override
    public int getLayoutId() {
        return cn.jzvd.R.layout.jz_layout_standard;
    }

    @Override
    public boolean onTouch(View v, MotionEvent event) {

        return super.onTouch(v, event);
    }

    @Override
    public void startVideo() {
        super.startVideo();
    }

    @Override
    public void onStateNormal() {
        super.onStateNormal();
        startButton.setVisibility(View.GONE);
        changeUiToPreparing();
    }

    @Override
    public void onStatePreparing() {
        super.onStatePreparing();
    }

    @Override
    public void onStatePlaying() {
        super.onStatePlaying();
    }

    @Override
    public void onStatePause() {
        super.onStatePause();

    }

    @Override
    public void onStateError() {
        super.onStateError();
    }

    @Override
    public void onStateAutoComplete() {
        super.onStateAutoComplete();
            startVideo();
    }

    @Override
    public void onInfo(int what, int extra) {
        super.onInfo(what, extra);
    }

    @Override
    public void onError(int what, int extra) {
        super.onError(what, extra);
    }

    @Override
    public void startWindowFullscreen() {
        super.startWindowFullscreen();
    }

    @Override
    public void startWindowTiny() {
        super.startWindowTiny();
    }
}
