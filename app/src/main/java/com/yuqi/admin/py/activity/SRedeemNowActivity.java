package com.yuqi.admin.py.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.yuqi.admin.py.bean.APPqueryIntegralcommodityBean;
import com.yuqi.admin.py.bean.APPqueryIntegralcommodityByIDBean;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.ResizableImageView;

/**
 * Created by Administrator on 2017/12/19.
 * 立即兑换
 */
public class SRedeemNowActivity extends BaseActivity {
    private TextView ljdh_leixing,ljd_jifen;
    private EditText ljdh_shulian;
    private ResizableImageView ljdh_tupian;

    APPqueryIntegralcommodityByIDBean APPqueryIntegralcommodityByID;
    Intent intent = new Intent();
    String CommodityName;
    String id;
    Bean bean;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_jfsc_lijiduihuan);
        Intent getIntent = getIntent();
        id = getIntent.getStringExtra("id");
        CommodityName = getIntent.getStringExtra("CommodityName");
        init();
        APPqueryIntegralcommodityByIDHTTP(id+"");
    }

    private void init() {
        ljdh_leixing = (TextView)findViewById(R.id.ljdh_leixing);
        ljd_jifen = (TextView)findViewById(R.id.ljd_jifen);
        ljdh_shulian = (EditText) findViewById(R.id.ljdh_shulian);
        ljdh_tupian = (ResizableImageView)findViewById(R.id.ljdh_tupian);

        ljdh_leixing.setText(CommodityName);
    }

    @Override
    public String title_text() {
        return ljdh_leixing.getText().toString();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dh_lijiduihuan:
                AlertDialog dialog =  new  AlertDialog.Builder(SRedeemNowActivity.this)
                        .setTitle("确认操作" )
                        .setMessage("你正在兑换彭友聚汇"+CommodityName )
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                APPexchangeIntegralcommodityHTTP();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();

                            }
                        })
                        .create();
                dialog.show();

                break;
            case R.id.ljdh_jian:
                int jian = Integer.parseInt(ljdh_shulian.getText().toString());
                if (jian > 1 ){
                    jian = Integer.parseInt(ljdh_shulian.getText().toString()) - 1;
                    ljdh_shulian.setText(jian+"");
                }else if (ljdh_shulian.getText().toString().length()==0){
                    ljdh_shulian.setText(1+"");
                }
                break;
            case R.id.ljdh_jia:
                int jia = Integer.parseInt(ljdh_shulian.getText().toString()) + 1;
                if (jia == 9999)return;
                ljdh_shulian.setText(jia+"");
                break;

        }
    }
    /**积分商城立即兑换*/
    private void APPexchangeIntegralcommodityHTTP() {
        RequestParams params = new RequestParams();


        params.addQueryStringParameter("user_id", CommonData.user_id +"");
        params.addQueryStringParameter("integralCommodity_id", id+"");
        params.addQueryStringParameter("integralNumber", ljdh_shulian.getText().toString());

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("请求数据=", "积分商城立即兑换="+CommonData.user_id+"integralCommodity_id"+id+"integralNumber"+ljdh_shulian.getText().toString());

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"APPexchangeIntegralcommodity.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SRedeemNowActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("积分商城立即兑换=", responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        bean = gson.fromJson(result, Bean.class);
                        String state = APPqueryIntegralcommodityByID.getState();
                        switch (state){
                            case "200":
                                intent.setClass(SRedeemNowActivity.this,SExchangeApplicationActivity.class);
                                startActivity(intent);
                                finish();
                                break;
                            case "210":
                                break;
                        }

                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
//                        ToastUtil.show(SRedeemNowActivity.this,"网络异常");
                    }
                });
    }

    /**积分商城信息详情*/
    private void APPqueryIntegralcommodityByIDHTTP(String id) {
        RequestParams params = new RequestParams();

        params.addQueryStringParameter("user_id", CommonData.user_id +"");
        params.addQueryStringParameter("integralCommodity_id", id+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("请求数据=", "积分商城信息详情="+params);

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"APPqueryIntegralcommodityByID.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SRedeemNowActivity.this);
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
                        APPqueryIntegralcommodityByID = gson.fromJson(result, APPqueryIntegralcommodityByIDBean.class);
                        String state = APPqueryIntegralcommodityByID.getState();
                        switch (state){
                            case "200":
                                ljd_jifen.setText(APPqueryIntegralcommodityByID.getObject().getIntegralcommodities().getIntegralPrice()+"");

                                BitmapUtils bitmapUtils = new BitmapUtils(SRedeemNowActivity.this);
                                // 加载网络图片
                                bitmapUtils.display(ljdh_tupian,APPqueryIntegralcommodityByID.getObject().getIntegralcommoditypictures().get(0).getPicturePath());

                                break;
                            case "210":
                                break;
                        }

                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SRedeemNowActivity.this,"网络异常");
                    }
                });
    }
}
