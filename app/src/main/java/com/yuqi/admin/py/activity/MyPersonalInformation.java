package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

/**
 * Created by Administrator on 2018/1/4.
 *      用户信息
 */
public class MyPersonalInformation extends BaseActivity {
    private TextView pi_zhanghao,pi_zhuceshijian,pi_gongsimingcheng;

    private String phoneNumber,CompanyName,creationTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_personal);
        Intent intent = getIntent();
        phoneNumber =intent.getStringExtra("phoneNumber");
        CompanyName =intent.getStringExtra("CompanyName");
        creationTime =intent.getStringExtra("creationTime");
        initView();
    }

    private void initView() {
        pi_zhanghao = (TextView) findViewById(R.id.pi_zhanghao);
        pi_zhuceshijian = (TextView) findViewById(R.id.pi_zhuceshijian);
        pi_gongsimingcheng = (TextView) findViewById(R.id.pi_gongsimingcheng);

        pi_zhanghao.setText(phoneNumber);
        pi_zhuceshijian.setText(creationTime);
        pi_gongsimingcheng.setText(CompanyName);
    }

    @Override
    public String title_text() {
        return "用户信息";
    }

    @Override
    public void onClick(View v) {
    }
}
