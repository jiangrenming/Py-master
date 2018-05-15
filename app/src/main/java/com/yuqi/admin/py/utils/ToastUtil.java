package com.yuqi.admin.py.utils;

import android.content.Context;
import android.graphics.Color;
import android.os.Handler;
import android.util.Log;
import android.view.Gravity;
import android.widget.TextView;
import android.widget.Toast;
import com.yuqi.admin.py.R;
import java.util.HashMap;
import java.util.Map;


public class ToastUtil {
    public static Toast mToast;
    public static Handler hand = new Handler();
    public static Runnable r = new Runnable() {
        @Override
        public void run() {
            mToast.cancel();
        }
    };

    static Map<String, Long> log_msg = new HashMap<String, Long>();

    public static void show(Context ctx, String s) {
        long time = System.currentTimeMillis();
        if (log_msg.get(s) != null && time - log_msg.get(s) < 1000) {
            return;
        }
        log_msg.put(s, time);
        mToast = new Toast(ctx);
        TextView text = new TextView(ctx);
        text.setBackgroundResource(R.drawable.toast_background);
        text.setTextColor(ctx.getResources().getColor(R.color.yellow1));
//        text.setTextColor(Color.argb(255, 0, 255, 0));

        text.setPadding(30, 30, 30, 30);
        text.setTextSize(12);
        text.setGravity(Gravity.CENTER);
        text.setLineSpacing(2,1);//行间距
        text.setText(s);
        mToast.setView(text);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER_VERTICAL|Gravity.CENTER_HORIZONTAL , 0, 0);  //设置显示位置
        mToast.show();
        hand.postDelayed(r, 2000);
    }

    public static void show(Context ctx, String s, int time) {
        long mTime = System.currentTimeMillis();
        if (log_msg.get(s) != null && mTime - log_msg.get(s) < 1000) {
            return;
        }
        log_msg.put(s, mTime);
        mToast = new Toast(ctx);
        TextView text = new TextView(ctx);
        text.setBackgroundResource(R.drawable.toast_background);
        text.setTextColor(ctx.getResources().getColor(R.color.yellow1));
        text.setPadding(30, 30, 30, 30);
        text.setTextSize(12);
        text.setText(s);
        mToast.setView(text);
        mToast.setDuration(Toast.LENGTH_LONG);
        Log.e("toasttime", mToast.getDuration() + "");
        mToast.show();
        hand.postDelayed(r, time);
    }


    public static void show1(Context ctx, String s) {
        long time = System.currentTimeMillis();
        if (log_msg.get(s) != null && time - log_msg.get(s) < 1000) {
            return;
        }
        log_msg.put(s, time);
        mToast = new Toast(ctx);
        TextView text = new TextView(ctx);
        text.setBackgroundResource(R.drawable.toast_background1);
        text.setTextColor(ctx.getResources().getColor(R.color.text_color));
        text.setPadding(30, 30, 30, 30);
        text.setTextSize(14);
        text.setText(s);
        mToast.setView(text);
        mToast.setDuration(Toast.LENGTH_LONG);
        mToast.setGravity(Gravity.CENTER, 0, 0);
        mToast.show();
        hand.postDelayed(r, 2000);
    }


}
