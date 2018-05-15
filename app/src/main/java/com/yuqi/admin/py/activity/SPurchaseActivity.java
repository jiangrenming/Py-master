package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.view.MyListView;


/**
 * Created by Administrator on 2017/11/23.
 *  我要采购
 */
public class SPurchaseActivity extends BaseActivity{

//    private PurchaseAdapter purchaseAdapter;
    private MyListView lv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wycg_woyaocaigou);
    }
    @Override
    public String title_text() {
        return "我要采购";
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.purchase_edit:
                intent = new Intent(SPurchaseActivity.this,ReleaseInformationActivity.class);
                startActivity(intent);
                finish();
                break;
        }
    }

}
