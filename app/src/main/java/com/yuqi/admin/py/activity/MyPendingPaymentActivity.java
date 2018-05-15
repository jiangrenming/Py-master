package com.yuqi.admin.py.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.SpAdapter;
import com.yuqi.admin.py.bean.MyPendingPaymentBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 *      待付款
 */
public class MyPendingPaymentActivity extends BaseActivity{

    private LinearLayout ll_wait;
    Intent intent;
    ListView llv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_wait);
        initView();
        // 获取待付款
        MyPendingPaymentHTTP();
    }

    private void MyPendingPaymentHTTP() {

        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id",CommonData.user_id+"");
        params1.addQueryStringParameter("orderStatus","待付款");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "getOrderByStatus.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化

                        MyPendingPaymentBean pendingPayment = gson.fromJson(result, MyPendingPaymentBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = pendingPayment.getState();
                        switch (state) {
                            case "200":
                                MyPendingPaymentView(pendingPayment);
                                break;
                            case "210":
                                break;
                            case "220":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(MyPendingPaymentActivity.this, CommonData.REQUEST_EXCEOTON);
                    }
                });
    }

    private void MyPendingPaymentView(MyPendingPaymentBean PendingPayment) {
        SpAdapter asAdapter  = new SpAdapter(MyPendingPaymentActivity.this ,PendingPayment);
        llv.setAdapter(asAdapter);
        asAdapter.notifyDataSetInvalidated();
    }

    private void initView() {
        llv = (ListView)findViewById(R.id.llv);
    }

    @Override
    public String title_text() {
        return "待付款";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
        }
    }





}
