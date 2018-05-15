package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2017/11/23.
 *      平台说明
 */
public class AnliActivity extends BaseActivity implements View.OnClickListener{
    private ImageView al_anli,back;
    private TextView al_anli1;
    Intent intent;


    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layou_anli);
        init();
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0,0);
    }


    private void init() {
        al_anli = (ImageView) this.findViewById(R.id.bt_anli);
        back = (ImageView) this.findViewById(R.id.back);
        al_anli1 = (TextView) this.findViewById(R.id.bt_anli1);
        inits();
    }

    private void inits() {
        back.setVisibility(View.INVISIBLE);
        al_anli.setImageResource(R.mipmap.b3);
        al_anli1.setTextColor(Color.parseColor("#f4a100"));
    }

    @Override
    public String title_text() {
        return "平台说明";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.bt_shouye11:
                intent  = new Intent(AnliActivity.this,ShouyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;

            case R.id.bt_yuqi11:
                intent = new Intent(AnliActivity.this,YuqActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;

            case R.id.bt_anli11:
//                intent = new Intent(AnliActivity.this,YuqActivity.class);
//                startActivity(intent);
                break;

            case R.id.bt_women11:
                intent = new Intent(AnliActivity.this,WomActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
        }
    }

    /**处理两次点击手机返回键退出*/
    long backtime = 0;
    @Override
    public void onBackPressed() {
        long clicktime = System.currentTimeMillis();
        if (backtime == 0 || clicktime - backtime > 1500) {
            backtime = clicktime;
            ToastUtil.show(this, "再次点击退出程序");
        } else {
            Intent backHome = new Intent(Intent.ACTION_MAIN);
            backHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            backHome.addCategory(Intent.CATEGORY_HOME);
            startActivity(backHome);
        }
    }
}
