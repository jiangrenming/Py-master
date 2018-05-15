package com.yuqi.admin.py;

import android.Manifest;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.support.v4.app.ActivityCompat;


import com.umeng.commonsdk.UMConfigure;
import com.umeng.socialize.Config;
import com.umeng.socialize.PlatformConfig;
import com.umeng.socialize.UMShareAPI;

import cn.jpush.android.api.JPushInterface;


/**
 * Created by Admin on 2017/3/1.
 */

public class MyApplication extends Application {

    public  static Context mContext;

    @Override
    public void onCreate() {
        super.onCreate();
        //设置LOG开关，默认为false
        UMConfigure.setLogEnabled(true);
        //初始化组件化基础库, 统计SDK/推送SDK/分享SDK都必须调用此初始化接口
        UMConfigure.init(this, "5aa380a2f29d98737c00044d", null, UMConfigure.DEVICE_TYPE_PHONE, null);
//        //开启ShareSDK debug模式，方便定位错误，具体错误检查方式可以查看http://dev.umeng.com/social/android/quick-integration的报错必看，正式发布，请关闭该模式
        Config.DEBUG = true;
        UMShareAPI.get(this);
        mContext = this;
        JPushInterface.setDebugMode(false); 	// 设置开启日志,发布时请关闭日志
        JPushInterface.init(this);     		// 初始化 JPush

    }
    {
        PlatformConfig.setWeixin("wxd220618f0c9781b5", "034a305345f6e0bf99f96af7a728b454");
    }

    public static Context getmContext() {
        return mContext;
    }
}
