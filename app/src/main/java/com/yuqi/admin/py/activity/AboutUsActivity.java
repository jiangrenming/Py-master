package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

/**
 * Created by Administrator on 2017/12/27.
 * 联系我们
 */
public class AboutUsActivity extends BaseActivity{
    private LinearLayout jiaruwomen;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about_us);
        init();
    }

    private void init() {
        jiaruwomen = (LinearLayout) findViewById(R.id.jiaruwomen);
    }

    @Override
    public String title_text() {
        return "联系我们";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.jiaruwomen:
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_CALL);
                intent.setData(Uri.parse("tel:4009651681")); startActivity(intent);
                break;
        }
    }
}
