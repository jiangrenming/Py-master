package com.yuqi.admin.py.utils;

import android.content.Context;
import android.content.SharedPreferences;

import com.yuqi.admin.py.MyApplication;

/**
 * Created by jiangrenming on 2017/12/12.
 * 银行卡ps存储
 */

public class SharePreferenceUtils {

    private static final String SHARE_PREFS_NAME = "com.yuqi.admin.py";

    private static SharedPreferences getPreferences() {
        return MyApplication.mContext.getSharedPreferences(SHARE_PREFS_NAME, Context.MODE_PRIVATE);
    }

    public static void putBoolean(String key, boolean value) {
        getPreferences().edit().putBoolean(key, value).commit();
    }

    public static boolean getBoolean(String key, boolean defaultValue) {
        return getPreferences().getBoolean(key, defaultValue);
    }

    public static void putString(String key, String value) {
        getPreferences().edit().putString(key, value).commit();
    }

    public static String getString(String key, String defaultValue) {
        return getPreferences().getString(key, defaultValue);
    }

    public static void putInt(String key, int value) {
        getPreferences().edit().putInt(key, value).commit();
    }

    public static int getInt(String key, int defaultValue) {
        return getPreferences().getInt(key, defaultValue);
    }
    public  static void clearData(String key){
        SharedPreferences.Editor edit = getPreferences().edit();
        edit.remove(key);
        edit.commit();
    }
}
