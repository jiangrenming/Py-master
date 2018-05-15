package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
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
import com.yuqi.admin.py.adapter.RewardAdapter;
import com.yuqi.admin.py.adapter.ShangPinAdapter;
import com.yuqi.admin.py.bean.RewardBean;
import com.yuqi.admin.py.bean.ShareBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

public class RewardActivity extends BaseActivity {

    private int user_id;
    private ListView lv_reward;
    private RewardBean bean;
    private RewardAdapter adapter;
    private TextView tv_reward_total;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reward);
        lv_reward = (ListView) findViewById(R.id.lv_reward);
        tv_reward_total = (TextView) findViewById(R.id.tv_reward_total);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id");
        intent.getExtras();
        rewardRequest();
    }

    private void rewardRequest() {
        RequestParams params = new RequestParams();
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("分享请求数据=", "数据="+params);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"MyRewards.action"+"?user_id="+user_id,
                params, new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(RewardActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        String result = responseInfo.result;
                        Log.e("分享数据=", "数据=" + result);
                        Gson gson = new Gson();//初始化
                        bean = gson.fromJson(result, RewardBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：
                        String state = bean.getStatus();
                        switch (state){
                            case "200":
                                tv_reward_total.setText(String.valueOf(bean.getData().get_$SUMGetMoney180())+"元");
                                adapter = new RewardAdapter(RewardActivity.this,bean);
                                lv_reward.setAdapter(adapter);
                                break;
                            case "210":
                                ToastUtil.show(RewardActivity.this,"快去邀请别人注册哦");
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
        return "我的奖励";
    }

    @Override
    public void onClick(View v) {
//        switch (v.getId()) {
//            case R.id.btn_reward://我的奖励
//
//                break;
//        }
    }
}