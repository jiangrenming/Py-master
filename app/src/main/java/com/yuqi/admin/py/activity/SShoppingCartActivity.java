package com.yuqi.admin.py.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.ObserverManager;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.MyPendingPaymentBean;
import com.yuqi.admin.py.bean.ShoppingCarBean;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.interfaces.Deletesth;
import com.yuqi.admin.py.interfaces.IselectInter;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.ShoppCarViewBItem;
import com.yuqi.admin.py.adapter.ShangPinAdapter;
import com.yuqi.admin.py.view.ShoppCarViewSItem;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/15.
 *  购物车
 */
public class SShoppingCartActivity extends BaseActivity implements IselectInter{
    private ShangPinAdapter spAdapter;
    private TextView Jrgwc,jianshu2,heji2,tijiaodd;
    private LinearLayout container,none;

    private getShoppingtrolleyBean CarBeanData;
    private LinearLayout mGallery;
    private LayoutInflater mInflater;
    private Context mContext;


    private List<getShoppingtrolleyBean.ObjectBean> beanList = new ArrayList<>();
    private ShoppCarViewBItem mItem;
    private Deletesth deletesth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_gwc_gouwuche);
        mContext = this;
        init();
        getShoppingtrolleyHTTP();
    }

    private void getShoppingtrolleyHTTP() {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", CommonData.user_id+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "getShoppingtrolley.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SShoppingCartActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("购物车=", responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        while(result.indexOf("\":[")!=-1){
                            int i = result.indexOf("\":[");
                            result = result.replace(result.substring(i-3,i+3),"");
                        }
                        result = result.replace("]","");
                        result = result.replace("\"object\":{","\"object\":[");
                        result = result.replace("},\"state\"","],\"state\"");
                        CarBeanData = gson.fromJson(result, getShoppingtrolleyBean.class);//存商品数据
                        String state = CarBeanData.getState();
                        switch (state) {
                            case "200":
                                initView();
                                break;
                            case "210":
                                none.setVisibility(View.VISIBLE);
                                break;
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SShoppingCartActivity.this,"网络异常");
                    }
                });

    }

    private void init() {
        none = (LinearLayout) findViewById(R.id.ll_none);
        container = (LinearLayout) findViewById(R.id.layout_container);
        jianshu2 = (TextView)findViewById(R.id.jianshu2);
        heji2 = (TextView)findViewById(R.id.heji2);
        tijiaodd = (TextView)findViewById(R.id.tijiaodd);
    }


    private void initView() {
        List<getShoppingtrolleyBean.ObjectBean> beans = new ArrayList<>();
        getShoppingtrolleyBean.ObjectBean objectBean = new getShoppingtrolleyBean.ObjectBean();
        objectBean.setTypeName("default");
        CarBeanData.getObject().add(objectBean);

        if(CarBeanData != null) {
            String type = "";
            for(getShoppingtrolleyBean.ObjectBean bean : CarBeanData.getObject()) {
                if(!bean.getTypeName().equals(type)){
                    if(beans.size() != 0) {
                        mItem = new ShoppCarViewBItem(mContext, beans, this);
                        container.addView(mItem);
                    }
                    beans = new ArrayList<>();
                    type = bean.getTypeName();
                    beans.add(bean);
                    Log.e("1f的数据枫林狂的",bean.getTypeName());
                    }else{
                        beans.add(bean);
                    }
            }
        }



//        ObserverManager manager = ObserverManager.getInstance();
        /**
         * 如果没有购物车的数据就显示无数据的页面
         * 如果有购物车的数据，就去获取购物车的数据并显示到页面上去
         * 这里分类首页六种
         */

//        if(manager.getEntity().getGoodsLis().size()!=0){
//            none.setVisibility(View.GONE);
//
//        }else{
//                for(ShoppingCarBean.ShoppingcarData data : manager.getEntity().getGoodsLis()){
//                //在container里面添加通过数据新建的view
//
//                }
//        }
//        spAdapter = new ShangPinZhAdapter(SShoppingCartActivity.this);
//        gwc_gv.setAdapter(spAdapter);
    }

    @Override
    public String title_text() {
        return "购物车";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_jrsc:
                Intent intent = new Intent();
                intent.setClass(SShoppingCartActivity.this,ShouyActivity.class);
                startActivity(intent);
                finish();
                break;
            case R.id.tijiaodd:
                if (beanList.size()>0){
                    Intent intent1 = new Intent();
                    intent1.setClass(SShoppingCartActivity.this,SConfirmationSingleActivity.class);
                    intent1.putExtra("dingDan", (Serializable) beanList);
                    startActivity(intent1);
//                    finish();
                }else {
                    ToastUtil.show(SShoppingCartActivity.this,"请选好您的商品");
                    return;
                }


                break;

        }
    }


    @Override
    public void push(boolean select, ShoppCarViewSItem item) {
        if(select){
            beanList.add(item.data);
        }else{
            beanList.remove(item.data);
        }
        changeViews();
    }


    private void changeViews(){
        /**
         * 计算beanList里面的date数据来进行相加或是相减
         * 这里要写计算方法，还有界面的改变
         *
         * 如果是全选，就直接把
         */
        double tatol = 0;
        int numt = 0;
        for(getShoppingtrolleyBean.ObjectBean bean : beanList){
            tatol += (double)bean.getCommodityNumber()*bean.getCommodityPrice();
            numt += bean.getCommodityNumber();
        }
        jianshu2.setText(numt+"");
        heji2.setText("￥"+tatol);
    }

    private void allPick(boolean pick){
        if(pick){
            beanList.clear();
            for(ShoppCarViewSItem item : mItem.viewSItems){
                beanList.add(item.data);
            }
        }else{
            beanList.clear();
        }
        changeViews();
    }
}
