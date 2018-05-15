package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.SSearchAdapter;
import com.yuqi.admin.py.adapter.ShangPinzhAdapter;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.bean.APPqueryCommodityBean;
import com.yuqi.admin.py.bean.APPqueryHistorySeekByIDBean;
import com.yuqi.admin.py.bean.APPqueryHistorySeekByIDShopBean;
import com.yuqi.admin.py.bean.MyPendingPaymentBean;
import com.yuqi.admin.py.bean.PersonalCenterBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ImageUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.AutoSplitTextView;
import com.yuqi.admin.py.view.FixGridLayout;
import com.yuqi.admin.py.view.MultiGridView;
import com.yuqi.admin.py.view.ResizableImageView;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/12/17.
 * 搜索
 */
public class SSearchActivity extends BaseActivity{
    LinearLayout ll_showpictrue_container,resou ;
    FixGridLayout ll_showpictrue_container1;
    String sousu;
    private EditText et_sousu;
    private ShangPinzhAdapter spZhAdapter;
    private MultiGridView resouList;
    private int recLen = 3;
    Timer timer = new Timer();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_ss_sousu);
        initView();
        //历史搜索
        APPqueryHistorySeekByID();

    }

    private void initView() {
        resou = (LinearLayout) findViewById(R.id.resou);
        et_sousu = (EditText)findViewById(R.id.et_sousu);
        ll_showpictrue_container = (LinearLayout) findViewById(R.id.ll_showpictrue_container);
        ll_showpictrue_container1 = (FixGridLayout) findViewById(R.id.ll_showpictrue_container1);
        resouList = (MultiGridView)findViewById(R.id.resouList);
    }

    private void APPqueryHistorySeekByID() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("appUser_id", CommonData.user_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "APPqueryHistorySeekByID.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SSearchActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("SPersonal历史记录=", responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        APPqueryHistorySeekByIDBean queryHistorySeekByID = gson.fromJson(result, APPqueryHistorySeekByIDBean.class);
                        String state = queryHistorySeekByID.getState();
                        switch (state) {
                            case "200":
                                lisi(queryHistorySeekByID);
                                break;
                            case "210":
                                resou(queryHistorySeekByID);
                                break;
                        }
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                    }
                });

    }

    private void resou(APPqueryHistorySeekByIDBean queryHistorySeekByID) {
        List<String> bean =  queryHistorySeekByID.getObject().getHotSearch();
        for(int i =0 ; i < queryHistorySeekByID.getObject().getHotSearch().size();i++) {
            final TextView textresou = new TextView(SSearchActivity.this);
            textresou.setText(bean.get(i));
            textresou.setPadding(30, 30, 30, 30);
            textresou.setTextSize(12);
            textresou.setGravity(Gravity.CENTER);
            textresou.setBackgroundResource(R.drawable.text_background1);
            textresou.setTextColor(SSearchActivity.this.getResources().getColor(R.color.gray2));
            ll_showpictrue_container.addView(textresou);
            TextView text1 = new TextView(SSearchActivity.this);
            text1.setWidth(20);
            ll_showpictrue_container.addView(text1);

            textresou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sousu=  textresou.getText().toString();
                    et_sousu.setText(sousu);
                    if(sousu.equals(""))return;
                    //搜索
                    APPqueryCommodityByName(sousu);
                }
            });
        }
    }


    private void lisi(APPqueryHistorySeekByIDBean queryHistorySeekByID) {
        List<String> bean =  queryHistorySeekByID.getObject().getHotSearch();
        List<String> bean1 =  queryHistorySeekByID.getObject().getSeekValues();
        for(int i =0 ; i < queryHistorySeekByID.getObject().getHotSearch().size();i++) {
            final TextView textresou = new TextView(SSearchActivity.this);
            textresou.setText(bean.get(i));
            textresou.setPadding(30, 30, 30, 30);
            textresou.setTextSize(12);
            textresou.setGravity(Gravity.CENTER);
            textresou.setBackgroundResource(R.drawable.text_background1);
            textresou.setTextColor(SSearchActivity.this.getResources().getColor(R.color.gray2));
            ll_showpictrue_container.addView(textresou);
            TextView text1 = new TextView(SSearchActivity.this);
            text1.setWidth(20);
            ll_showpictrue_container.addView(text1);

            textresou.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sousu=  textresou.getText().toString();
                    et_sousu.setText(sousu);
                    if(sousu.equals(""))return;
                    //搜索
                    APPqueryCommodityByName(sousu);
                }
            });
        }

        for(int i = 0 ; i < queryHistorySeekByID.getObject().getSeekValues().size();i++) {
            final AutoSplitTextView textlishi = new AutoSplitTextView(SSearchActivity.this);
            textlishi.setText(bean1.get(i));
            textlishi.setPadding(30, 30, 30, 30);
            textlishi.setTextSize(12);
            textlishi.setGravity(Gravity.CENTER);
            textlishi.setBackgroundResource(R.drawable.text_background1);
            textlishi.setTextColor(SSearchActivity.this.getResources().getColor(R.color.gray2));
            ll_showpictrue_container1.addView(textlishi);
            TextView text1 = new TextView(SSearchActivity.this);
            text1.setWidth(20);
            ll_showpictrue_container1.addView(text1);
            textlishi.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    sousu=  textlishi.getText().toString();
                    et_sousu.setText(sousu);
                    //搜索
                    if(sousu.equals(""))return;
                    APPqueryCommodityByName(sousu);
                }
            });
        }

    }
    //搜索
    private void APPqueryCommodityByName(String sousu) {
        Log.e("sousu----",sousu);
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("appUser_id", CommonData.user_id+"");
        params.addQueryStringParameter("Commodity_name", sousu);
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "APPqueryCommodityByName.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SSearchActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("SPersonal=", responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        APPqueryHistorySeekByIDShopBean queryHistorySeekByIDShop = gson.fromJson(result, APPqueryHistorySeekByIDShopBean.class);
                        String state = queryHistorySeekByIDShop.getState();
                        switch (state) {
                            case "200":
                                resou.setVisibility(View.GONE);
                                resouList.setVisibility(View.VISIBLE);
                                //历史搜索
                                SSearchAdapter searchAdapter = new SSearchAdapter(SSearchActivity.this ,queryHistorySeekByIDShop);
                                resouList.setAdapter(searchAdapter);
                                break;
                            case "210":
                                ToastUtil.show(SSearchActivity.this,"抱歉！没搜到商品,如有需要请联系彭友");
//                                //历史搜索
//                                queryHistorySeekByIDShop.getObject().clear();
//                                APPqueryHistorySeekByID();
                                break;
                        }
                    }


                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                    }
                });
    }



    @Override
    public String title_text() {
        return "搜索";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.sous:
                sousu  = et_sousu.getText().toString();
                //搜索
                if(sousu.equals(""))return;
                APPqueryCommodityByName(sousu);
                break;
        }
    }
}
