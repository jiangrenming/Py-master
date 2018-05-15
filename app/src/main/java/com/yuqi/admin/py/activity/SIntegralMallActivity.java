package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.JIFenAdapter;
import com.yuqi.admin.py.bean.APPqueryIntegralcommodityBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2017/12/18.
 * 积分商品
 */
public class SIntegralMallActivity extends BaseActivity {
    private TextView jfsc_xm,jfsc_dh;

    Intent intent = new Intent();

    private GridView GridJfsc;
    private JIFenAdapter jiFenAdapter;
    APPqueryIntegralcommodityBean  APPqueryIntegralcommodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_jfsc_jifenshangcheng);
        init();
        APPqueryIntegralcommodityHTTP();
    }

    private void init() {
        GridJfsc = (GridView)findViewById(R.id.GridJfsc);
        jfsc_xm = (TextView)findViewById(R.id.jfsc_xm);
        jfsc_dh = (TextView)findViewById(R.id.jfsc_dh);
    }

    //综合展示商品
    private void homePage( APPqueryIntegralcommodityBean APPqueryIntegralcommodity) {
        jiFenAdapter = new JIFenAdapter(SIntegralMallActivity.this ,APPqueryIntegralcommodity);
        GridJfsc.setAdapter(jiFenAdapter);
    }

    @Override
    public String title_text() {
        return "积分商城";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
//            case R.id.duihuan_wzdj:
//                intent.setClass(SIntegralMallActivity.this, SRedeemNowActivity.class);
//                startActivity(intent);
//                finish();
//                break;
        }
    }

    /**积分商城信息*/
    private void APPqueryIntegralcommodityHTTP() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("user_id",CommonData.user_id+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("请求数据=", "积分商城="+params);

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"APPqueryIntegralcommodity.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SIntegralMallActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("积分商城=", responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        APPqueryIntegralcommodity = gson.fromJson(result, APPqueryIntegralcommodityBean.class);
                        String state = APPqueryIntegralcommodity.getState();
                        switch (state){
                            case "200":
                                //积分商品展示
                                homePage(APPqueryIntegralcommodity);
                                jfsc_xm.setText(APPqueryIntegralcommodity.getCompanyName());
                                jfsc_dh.setText("可用积分:"+APPqueryIntegralcommodity.getCompanyIntegral()+"");
                                break;
                            case "210":
                                break;
                        }

                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SIntegralMallActivity.this,"网络异常");
                    }
                });
    }
}
