package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.tencent.mm.opensdk.modelpay.PayReq;
import com.tencent.mm.opensdk.openapi.IWXAPI;
import com.tencent.mm.opensdk.openapi.WXAPIFactory;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.alipay.PayResult;
import com.yuqi.admin.py.bean.AlipayBean;
import com.yuqi.admin.py.bean.WeChatHaymentBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import java.util.Map;

/**
 * Created by Administrator on 2017/11/23.
 *      联系我们
 */
public class WomActivity extends BaseActivity implements View.OnClickListener{
    private ImageView bt_women,back;
    private TextView bt_women1;
    private ImageView wm_weixin1,wm_weixin11,wm_zhifubao1,wm_zhifubao11;
    private EditText wm_gongsiquancheng,
            wm_zhenshixingming,wm_shoujihao,wm_gsdz,wm_yufukuan;

    private ImageView wlzjjs2,pyjh_ycz2,ch_ry_ps_zz2;
    private String xuanze = "网络基础建设";
    String  peisong ="支付宝";
    Intent intent;
    private static final int SDK_PAY_FLAG = 1;
    int demandPrice;;

    AlipayBean alipayBean;
    WeChatHaymentBean weChatHaymentBean;
    WeChatHaymentBean.ResultMapBean credential;
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        return super.onKeyDown(keyCode, event);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layou_women);
        initView();
    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0,0);
    }


    private void initView() {
        bt_women = (ImageView) this.findViewById(R.id.bt_women);
        bt_women1 = (TextView) this.findViewById(R.id.bt_women1);
        back = (ImageView) this.findViewById(R.id.back);

        wm_gongsiquancheng = (EditText) this.findViewById(R.id.wm_gongsiquancheng);
        wm_zhenshixingming = (EditText) this.findViewById(R.id.wm_zhenshixingming);
        wm_shoujihao = (EditText) this.findViewById(R.id.wm_shoujihao);
        wm_gsdz = (EditText) this.findViewById(R.id.wm_gsdz);

        wlzjjs2 = (ImageView) findViewById(R.id.wlzjjs2);
        pyjh_ycz2 =(ImageView)findViewById(R.id.pyjh_ycz2);
        ch_ry_ps_zz2 =(ImageView)findViewById(R.id.ch_ry_ps_zz2);
        wm_yufukuan= (EditText)findViewById(R.id.wm_yufukuan);

        wm_weixin1 = (ImageView)findViewById(R.id.wm_weixin1);
        wm_weixin11 = (ImageView)findViewById(R.id.wm_weixin11);
        wm_zhifubao1 = (ImageView)findViewById(R.id.wm_zhifubao1);
        wm_zhifubao11 = (ImageView)findViewById(R.id.wm_zhifubao11);

        inits();
    }
    private void inits() {
        back.setVisibility(View.INVISIBLE);
        bt_women.setImageResource(R.mipmap.b4);

        bt_women1.setTextColor(Color.parseColor("#f4a100"));
    }

    @Override
    public String title_text() {
        return "联系我们";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.bt_shouye11:
                intent  = new Intent(WomActivity.this,ShouyActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;

            case R.id.bt_yuqi11:
                intent = new Intent(WomActivity.this,YuqActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;

            case R.id.bt_anli11:
                intent = new Intent(WomActivity.this,AnliActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;

            case R.id.bt_women11:
//                intent = new Intent(WomActivity.this,WomActivity.class);
//                startActivity(intent);
                break;

            case R.id.wm_tijiao:
                String mResult = isContentEmpty();
                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    if ("".equals(wm_yufukuan.getText().toString())){
                        ToastUtil.show(WomActivity.this,"输入金额不正确");
                    }else {
                        demandPrice = Integer.parseInt(wm_yufukuan.getText().toString());
                        if (demandPrice % 50 == 0 && demandPrice >=50 ) {
                            if(peisong.equals("支付宝")){
                                AlipayToPayHttp();
                            }else if (peisong.equals("微信")){
                                WeChatHaymentHttp();
                            }
                        }else {
                            ToastUtil.show(WomActivity.this,"输入金额不正确");
                        }
                    }
                }

                break;

            case R.id.wlzjjs:
                wlzjjs2.setImageResource(R.mipmap.xuanzhong);
                pyjh_ycz2.setImageResource(R.mipmap.meixuanzhong);
                ch_ry_ps_zz2.setImageResource(R.mipmap.meixuanzhong);
                xuanze ="网络基础建设";
                break;

            case R.id.pyjh_ycz:
                wlzjjs2.setImageResource(R.mipmap.meixuanzhong);
                pyjh_ycz2.setImageResource(R.mipmap.xuanzhong);
                ch_ry_ps_zz2.setImageResource(R.mipmap.meixuanzhong);
                xuanze ="彭友聚汇-预充值";
                break;

            case R.id.ch_ry_ps_zz:
                wlzjjs2.setImageResource(R.mipmap.meixuanzhong);
                pyjh_ycz2.setImageResource(R.mipmap.meixuanzhong);
                ch_ry_ps_zz2.setImageResource(R.mipmap.xuanzhong);
                xuanze ="策划-人员-拍摄-制作";
                break;

            case R.id.wm_weixin:
                wm_weixin1.setImageResource(R.mipmap.wx2);
                wm_weixin11.setImageResource(R.mipmap.xuanzhong);
                wm_zhifubao1.setImageResource(R.mipmap.zfb1);
                wm_zhifubao11.setImageResource(R.mipmap.meixuanzhong);
                peisong ="微信";
                break;
            case R.id.wm_zhifubao:
                wm_weixin1.setImageResource(R.mipmap.wx1);
                wm_weixin11.setImageResource(R.mipmap.meixuanzhong);
                wm_zhifubao1.setImageResource(R.mipmap.zfb2);
                wm_zhifubao11.setImageResource(R.mipmap.xuanzhong);
                peisong ="支付宝";
                break;

        }
    }

    //微信支付
    private void WeChatHaymentHttp() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("constantName",xuanze);//名字
        params.addQueryStringParameter("constantMoney",  wm_yufukuan.getText().toString());
        params.addQueryStringParameter("companyName", wm_gongsiquancheng.getText().toString());
        params.addQueryStringParameter("userName", wm_zhenshixingming.getText().toString());
        params.addQueryStringParameter("phoneNumber", wm_shoujihao.getText().toString());
        params.addQueryStringParameter("constantAddress", wm_gsdz.getText().toString());
        params.addQueryStringParameter("payWay",peisong);
        params.addQueryStringParameter("user_id", CommonData.user_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);

        Log.e(CommonData.REQUEST_EXCEOTON, "constantName="+ xuanze+",constantMoney=" +wm_yufukuan.getText().toString());

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.weixinURL+"createConstant.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(WomActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result + "/");
                        String  result =  responseInfo.result;
                        //微信支付
                        if (!TextUtils.isEmpty(result)){
                            Gson gson = new Gson();//初始化
                            weChatHaymentBean = gson.fromJson(result, WeChatHaymentBean.class);
                            credential  = weChatHaymentBean.getResultMap();

                            final IWXAPI iwxapi = WXAPIFactory.createWXAPI(WomActivity.this,CommonData.APP_ID); //初始化微信api
                            iwxapi.registerApp(CommonData.APP_ID); //注册appid  appid可以在开发平台获取
                            String partnerId = credential.getPartnerid();
                            String prepayId = credential.getPrepayid();
                            String nonceStr =  credential.getNoncestr();
                            String timeStamp = credential.getTimestamp();
                            String sign = credential.getSign();

                            PayReq request = new PayReq(); //调起微信APP的对象
                            //下面是设置必要的参数，也就是前面说的参数,这几个参数从何而来请看上面说明
                            request.appId = CommonData.APP_ID;
                            request.partnerId = partnerId;
                            request.prepayId = prepayId;
                            request.packageValue = "Sign=WXPay";
                            request.nonceStr = nonceStr;
                            request.timeStamp = timeStamp;
                            request.sign = sign;
                            iwxapi.sendReq(request);//发送调起微信的请求
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_EXCEOTON, "失败="+error.toString() + "/"+ msg);
                    }
                });
    }
    //支付宝支付
    private void AlipayToPayHttp() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("constantName",xuanze);//名字
        params.addQueryStringParameter("constantMoney",  wm_yufukuan.getText().toString());
        params.addQueryStringParameter("companyName", wm_gongsiquancheng.getText().toString());
        params.addQueryStringParameter("userName", wm_zhenshixingming.getText().toString());
        params.addQueryStringParameter("phoneNumber", wm_shoujihao.getText().toString());
        params.addQueryStringParameter("constantAddress", wm_gsdz.getText().toString());
        params.addQueryStringParameter("payWay",peisong);
        params.addQueryStringParameter("user_id", CommonData.user_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);

        Log.e(CommonData.REQUEST_EXCEOTON, "constantName="+ xuanze+",constantMoney=" +wm_yufukuan.getText().toString());

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.alipayURL+"createConstant.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(WomActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result + "/");
                        String  result =  responseInfo.result;
                        //支付宝支付
                        if (!TextUtils.isEmpty(result)){
                            Gson gson = new Gson();//初始化
                            alipayBean = gson.fromJson(result, AlipayBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：
                            String credential  = alipayBean.getResult();
                            Log.e("orderInfo=", credential);
                            alipay(credential);
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_EXCEOTON, "失败="+error.toString() + "/"+ msg);
                    }
                });

    }
    private void alipay(final String orderInfo) {
        Runnable payRunnable  = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(WomActivity.this);
                Map<String, String> result = alipay.payV2(orderInfo, true);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);

            }
        };
        // 必须异步调用
        Thread payThread  = new Thread(payRunnable );
        payThread .start();
    }
    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    @SuppressWarnings("unchecked")
                    PayResult payResult = new PayResult((Map<String, String>) msg.obj);
                    /**
                     对于支付结果，请商户依赖服务端的异步通知结果。同步通知结果，仅作为支付结束的通知。
                     */
                    String resultInfo = payResult.getResult();// 同步返回需要验证的信息
                    String resultStatus = payResult.getResultStatus();
                    // 判断resultStatus 为9000则代表支付成功
                    if (TextUtils.equals(resultStatus, "9000")) {
                        // 该笔订单是否真实支付成功，需要依赖服务端的异步通知。
                        Toast.makeText(WomActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(WomActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };

    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wm_yufukuan))){return "请填写预存金额";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wm_gongsiquancheng))){return "请填写公司名称";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wm_zhenshixingming))){return "请填写真实名字";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wm_shoujihao))){return "请填写电话";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wm_gsdz))){return "请填写地址";}
        return "";
    }

    /**处理两次点击手机返回键退出*/
    long backtime = 0;
    @Override
    public void onBackPressed() {
        long clicktime = System.currentTimeMillis();
        if (backtime == 0 || clicktime - backtime > 1500) {
            backtime = clicktime;
            ToastUtil.show(this, "再次点击退出程序");
        } else {
            Intent backHome = new Intent(Intent.ACTION_MAIN);
            backHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            backHome.addCategory(Intent.CATEGORY_HOME);
            startActivity(backHome);
        }
    }
}