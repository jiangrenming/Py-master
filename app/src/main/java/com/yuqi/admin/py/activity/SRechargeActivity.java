package com.yuqi.admin.py.activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.yuqi.admin.py.utils.ToastUtil;

import java.util.Map;

/**
 * 充值界面
 * Created by Administrator on 2017/12/27.
 */
public class SRechargeActivity extends BaseActivity {
    private static final int SDK_PAY_FLAG = 1;

    private ImageView cz_weixin1,cz_weixin11,cz_zhifubao1,cz_zhifubao11;
    private EditText cz_chongzhijine;
    String  peisong ="支付宝";
    int demandPrice;

    AlipayBean alipayBean;
    WeChatHaymentBean weChatHaymentBean;
    WeChatHaymentBean.ResultMapBean credential;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_chongzhi);
        initView();
    }

    private void initView() {
        cz_weixin1 = (ImageView)findViewById(R.id.cz_weixin1);
        cz_weixin11 = (ImageView)findViewById(R.id.cz_weixin11);
        cz_zhifubao1 = (ImageView)findViewById(R.id.cz_zhifubao1);
        cz_zhifubao11 = (ImageView)findViewById(R.id.cz_zhifubao11);
        cz_chongzhijine= (EditText)findViewById(R.id.cz_chongzhijine);
    }

    @Override
    public String title_text() {
        return "充值";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.cz_weixin:
                cz_weixin1.setImageResource(R.mipmap.wx2);
                cz_weixin11.setImageResource(R.mipmap.xuanzhong);
                cz_zhifubao1.setImageResource(R.mipmap.zfb1);
                cz_zhifubao11.setImageResource(R.mipmap.meixuanzhong);
                peisong ="微信";
                break;
            case R.id.cz_zhifubao:
                cz_weixin1.setImageResource(R.mipmap.wx1);
                cz_weixin11.setImageResource(R.mipmap.meixuanzhong);
                cz_zhifubao1.setImageResource(R.mipmap.zfb2);
                cz_zhifubao11.setImageResource(R.mipmap.xuanzhong);
                peisong ="支付宝";
                break;

            case R.id.cz_queding:
                if ("".equals(cz_chongzhijine.getText().toString())){
                    ToastUtil.show(SRechargeActivity.this,"输入金额不正确");
                }else {
                    demandPrice = Integer.parseInt(cz_chongzhijine.getText().toString());
                    if (demandPrice % 50 == 0 && demandPrice >=50 ) {
                        if(peisong.equals("支付宝")){
                            AlipayToPayHttp();
                        }else if (peisong.equals("微信")){
                            WeChatHaymentHttp();
                        }
                    }else {
                        ToastUtil.show(SRechargeActivity.this,"输入金额不正确");
                    }
                }
                break;
        }
    }
    //微信支付
    private void WeChatHaymentHttp() {
        final RequestParams params = new RequestParams();
        if ("null".equals(String.valueOf(demandPrice)))return;
        params.addQueryStringParameter("user_id", CommonData.user_id + "");
        params.addQueryStringParameter("demandWay", peisong);
        params.addQueryStringParameter("demandPrice", demandPrice+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("请求数据=", "请求数据="+params);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.weixinURL+"createRecharge.action",
                params,
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
                        Log.e(CommonData.REQUEST_SUCCESS+"2222", responseInfo.result + "/");
                        String  result =  responseInfo.result;
                        //微信支付
                        if (!TextUtils.isEmpty(result)){
                            Gson gson = new Gson();//初始化
                            weChatHaymentBean = gson.fromJson(result, WeChatHaymentBean.class);
                            credential  = weChatHaymentBean.getResultMap();

                            final IWXAPI iwxapi = WXAPIFactory.createWXAPI(SRechargeActivity.this,CommonData.APP_ID); //初始化微信api
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
        if ("null".equals(String.valueOf(demandPrice)))return;
        params.addQueryStringParameter("user_id", CommonData.user_id + "");
        params.addQueryStringParameter("demandWay", peisong);
        params.addQueryStringParameter("demandPrice", demandPrice+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.alipayURL + "createRecharge.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SRechargeActivity.this);
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
                        String result = responseInfo.result;
                        //支付宝支付
                        if (!TextUtils.isEmpty(result)){
                            Gson gson = new Gson();//初始化
                            alipayBean = gson.fromJson(result, AlipayBean.class);
                            String credential  = alipayBean.getResult();
                            Log.e("orderInfo=", credential);
                            alipay(credential);
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_EXCEOTON, "失败=" + error.toString() + "/" + msg);
                    }
                });
    }
    private void alipay(final String orderInfo) {
        Runnable payRunnable  = new Runnable() {
            @Override
            public void run() {
                PayTask alipay = new PayTask(SRechargeActivity.this);
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
                        Toast.makeText(SRechargeActivity.this, "支付成功", Toast.LENGTH_SHORT).show();
                    } else {
                        // 该笔订单真实的支付结果，需要依赖服务端的异步通知。
                        Toast.makeText(SRechargeActivity.this, "支付失败", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        }
    };
}
