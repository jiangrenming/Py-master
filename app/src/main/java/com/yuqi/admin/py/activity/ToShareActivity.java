package com.yuqi.admin.py.activity;


import android.Manifest;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.ShareBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

public class ToShareActivity extends BaseActivity {

    private int user_id;
    private ShareBean bean;
    private TextView tv_statement;
    private TextView tv_invitationCode;
    private TextView submit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_to_share);
        tv_statement = (TextView) findViewById(R.id.tv_statement);
        tv_invitationCode = (TextView) findViewById(R.id.tv_invitationCode);
        submit = (TextView) findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setText("奖励明细");
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id");
        intent.getExtras();
        shareStatement();
    }

    private void shareStatement() {
        RequestParams params = new RequestParams();
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("分享请求数据=", "数据="+params);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"MyInvitationCode.action"+"?user_id="+user_id,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(ToShareActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        String  result =  responseInfo.result;
                        Log.e("分享数据=", "数据="+result);
                        Gson gson = new Gson();//初始化
                        bean = gson.fromJson(result, ShareBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：
                        String state = bean.getStatus();
                        switch (state){
                            case "200":
                                tv_statement.setText(bean.getStatement());
                                tv_invitationCode.setText("您的活动分享邀请码： "+bean.getInvitationCode());
                                break;
                            case "210":
                                ToastUtil.show(ToShareActivity.this,"网络异常");
                                break;
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        Log.e("分享请求", "失败="+error.toString() + "/"+ msg);
                    }
                });


    }

    @Override
    public String title_text() {
        return "我要分享";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.submit://奖励明细
                Intent intent = new Intent();
                intent.setClass(ToShareActivity.this, RewardActivity.class);
                intent.putExtra("user_id",user_id);
                startActivity(intent);
            break;
            case R.id.tv_share://我要分享
                if(Build.VERSION.SDK_INT>=23){
                    String[] mPermissionList = new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE,Manifest.permission.CALL_PHONE,Manifest.permission.READ_LOGS,Manifest.permission.READ_PHONE_STATE, Manifest.permission.READ_EXTERNAL_STORAGE,Manifest.permission.SET_DEBUG_APP,Manifest.permission.SYSTEM_ALERT_WINDOW,Manifest.permission.GET_ACCOUNTS,Manifest.permission.WRITE_APN_SETTINGS};
                    ActivityCompat.requestPermissions(this,mPermissionList,123);
                }
                String imageurl = "http://www.pengyoujuhui.com:8021/pyjh/test.html?yqm="+bean.getInvitationCode();
//                                        String imageurl ="http://www.pengyoujuhui.com:8021/pyjh/test.html";
//                                        UMImage image = new UMImage(.this, imageurl);//网络图片
//                                        new ShareAction(ToShareActivity.this).withText("分享注册彭友聚汇得红包").withExtra(image)
//                                                .setDisplayList(SHARE_MEDIA.WEIXIN,
//                                                SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE)
//                                                .setCallback(umShareListener).open();
                                        UMImage image = new UMImage(ToShareActivity.this, R.mipmap.pengyou);//本地文件
                                        UMWeb  web = new UMWeb(imageurl);

                                        web.setTitle("下载注册得奖励（邀请码）"+bean.getInvitationCode());//标题
                                        web.setThumb(image);  //缩略图
                                        web.setDescription("注册时填写邀请码："+bean.getInvitationCode()+
                                                "，双方均可随机获得随机奖励，奖励余额最高88.8");
                                        new ShareAction(ToShareActivity.this)
                                                .withMedia(web)
                                                .setDisplayList(SHARE_MEDIA.WEIXIN,
                                                SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE)
                                                .setCallback(umShareListener)
                                                .open();
//                                        UMWeb web = new UMWeb("http://sj.qq.com/myapp/");
//                                    web.setTitle("来自分享面板标题");
//                                    web.setDescription("来自分享面板内容");
//                                    web.setThumb(new UMImage(ToShareActivity.this, R.mipmap.pengyou));
//                                    new ShareAction(ToShareActivity.this).withMedia(web)
//                                    .setDisplayList(SHARE_MEDIA.WEIXIN,
//                                    SHARE_MEDIA.WEIXIN_CIRCLE,SHARE_MEDIA.WEIXIN_FAVORITE)
//                                    .setCallback(umShareListener)
//                                            .share();
//                                        ToastUtil.show(ToShareActivity.this,"分享成功");


        }


    }

    private UMShareListener umShareListener = new UMShareListener() {
        /**
         * @descrption 分享开始的回调
         * @param platform 平台类型
         */
        @Override
        public void onStart(SHARE_MEDIA platform) {

        }

        /**
         * @descrption 分享成功的回调
         * @param platform 平台类型
         */
        @Override
        public void onResult(SHARE_MEDIA platform) {
            Toast.makeText(ToShareActivity.this,"成功了",Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享失败的回调
         * @param platform 平台类型
         * @param t 错误原因
         */
        @Override
        public void onError(SHARE_MEDIA platform, Throwable t) {
            Toast.makeText(ToShareActivity.this,"失败"+t.getMessage(),Toast.LENGTH_LONG).show();
        }

        /**
         * @descrption 分享取消的回调
         * @param platform 平台类型
         */
        @Override
        public void onCancel(SHARE_MEDIA platform) {
            Toast.makeText(ToShareActivity.this,"取消了",Toast.LENGTH_LONG).show();

        }
    };
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {


    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        UMShareAPI.get(this).release();
    }


}
