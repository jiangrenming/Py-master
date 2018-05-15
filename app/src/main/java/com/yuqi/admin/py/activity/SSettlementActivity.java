package com.yuqi.admin.py.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

/**
 * Created by Administrator on 2017/12/15.
 * 余额结算
 */
public class SSettlementActivity extends BaseActivity {
    private TextView jine;
    int a;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_spdd_jiesuan);
        init();
        //新页面接收数据
        Bundle bundle = this.getIntent().getExtras();
        //接收name值
        String name = bundle.getString("heji2");
        jine.setText(name);
    }

    private void init() {
        jine = (TextView) findViewById(R.id.jine);
    }

    @Override
    public String title_text() {
        return "结算";
    }

    @Override
    public void onClick(View v) {

    }
}
