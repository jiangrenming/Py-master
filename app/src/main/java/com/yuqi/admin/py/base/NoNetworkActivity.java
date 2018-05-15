package com.yuqi.admin.py.base;

import android.view.View;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.utils.Tools;


/**
 * Created by jill on 2016/11/14.
 */

public class NoNetworkActivity extends BaseActivity {

    @Override
    public String title_text() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }

//    @OnClick({R.id.tv_reload})
//    public void onClick(View view) {
//        switch (view.getId()) {
//            case R.id.tv_reload:
//                boolean networkAvailable = Tools.isNetworkAvailable(this);
//                if(networkAvailable){
//                    if(GlobalInfo.viewable == null)
//                        return;
//
//                    finish();
//                    GlobalInfo.viewable.refresh();
//                }else{
////                    showToast("请检查您的网络");
//                }
//
//                break;
//        }
//    }

//    @Override
//    protected int getLayoutId() {
//        return R.layout.activity_no_network;
//    }

    @Override
    protected void onResume() {
        super.onResume();

        boolean networkAvailable = Tools.isNetworkAvailable(this);
//        if(networkAvailable){
//            if(GlobalInfo.viewable == null)
//                return;
//
//            finish();
//            GlobalInfo.viewable.refresh();
//        }
    }
}
