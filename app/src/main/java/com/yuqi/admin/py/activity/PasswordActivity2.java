package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

/**
 * Created by Administrator on 2017/11/27.
 * 修改密码2
 */
public class PasswordActivity2 extends BaseActivity {
    private TextView submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password2);
        init();
    }

    private void init() {
        submit = (TextView) findViewById(R.id.submit);
        submit.setVisibility(View.INVISIBLE);
        submit.setText("完成");
    }

    @Override
    public String title_text() {
        return "找回密码";
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()){
            case R.id.wjmm_fanhui:
                Intent intent = new Intent();
                intent.setClass(PasswordActivity2.this,ShouyActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }
}
