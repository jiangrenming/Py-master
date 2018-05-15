package com.yuqi.admin.py;

import android.app.Activity;
import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;

import com.yuqi.admin.py.interfaces.Viewable;
import com.yuqi.admin.py.utils.ClickUtils;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2017/11/23.
 */
    public abstract class BaseActivity extends FragmentActivity  {

    public abstract String title_text();
    public abstract void onClick(View v);
    public TextView title;
    public boolean showBack(){
        return true;
    }
    public void setHeadTitle(String title){
        if (title!=null)this.title.setText(title);
    }
    public void onLayoutClick(View v){
        if (ClickUtils.isContinuClick())return;
        switch (v.getId()){
            case R.id.back:
                onBackPressed();
                break;
            default:
                onClick(v);
                break;
        }
    }
    @Override
    protected void onStart() {
        super.onStart();
        if(!showBack())findViewById(R.id.back).setVisibility(View.INVISIBLE);
        title = (TextView)findViewById(R.id.title);
        if (!StringUtil.isEmpty(title_text())&&title!=null){
            title.setText(title_text());
        }
    }


//    protected int getLayoutId() {
//        return 0;
//    }
}
