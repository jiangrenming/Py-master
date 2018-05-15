package com.yuqi.admin.py.activity;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.APPqueryCommodityBean;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2017/11/24.
 *      编辑发布信息
 */
public class ReleaseInformationActivity extends BaseActivity {
    private LinearLayout ri_choose_nan,ri_choose_nv;
    private ImageView ri_nan,ri_nv;
    int choose1 = 1;

    private EditText ri_name,ri_phone,ri_content;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_wycg_tianxiexinxi);
        init();

        ri_choose_nan.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ri_nan.setImageResource(R.drawable.choose2);
                    ri_nv.setImageResource(R.drawable.choose1);
                    choose1 =1;
                }
        });

        ri_choose_nv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ri_nan.setImageResource(R.drawable.choose1);
                    ri_nv.setImageResource(R.drawable.choose2);
                    choose1=2;
                }
        });

    }


    private void init() {
        ri_choose_nan = (LinearLayout) findViewById(R.id.ri_choose_nan);
        ri_choose_nv = (LinearLayout) findViewById(R.id.ri_choose_nv);
        ri_nan = (ImageView)findViewById(R.id.ri_nan);
        ri_nv = (ImageView)findViewById(R.id.ri_nv);
        ri_name = (EditText)findViewById(R.id.ri_name);
        ri_phone=(EditText)findViewById(R.id.ri_phone);
        ri_content = (EditText)findViewById(R.id.ri_content);

        ri_nan.setImageResource(R.drawable.choose2);
    }

    @Override
    public String title_text() {
        return "填写信息";
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()){
            case R.id.ri_submit:
                String mResult = isContentEmpty();
                String number = ri_phone.getText().toString();
                boolean judge = StringUtil.isMobile(number);

                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    if (judge == true) {
                        submit();
                    } else {
                        ToastUtil.show(ReleaseInformationActivity.this,"请输入正确的手机号码!");
                        return;
                    }

                }
                break;

        }
    }
    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.ri_name))){return "请填写姓名";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.ri_phone))){return "请填写电话";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.ri_content))){return "请填写内容";}
        return "";
    }
    private void submit() {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("name",ri_name.getText()+"");// 名字
        params.addQueryStringParameter("gender",choose1+"");// 性别
        params.addQueryStringParameter("phoneNumber",ri_phone.getText()+"");// 电话
        params.addQueryStringParameter("demandContext",ri_content.getText()+"");//内容
        HttpUtils http = new HttpUtils();

        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("请求数据=", "请求数据="+params);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"releaseDemand.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(ReleaseInformationActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("商品详情数据=", responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        Bean bean = gson.fromJson(result,Bean.class);//存商品数据
                        String state = bean.getState();
                        switch (state) {
                            case "200":
                                Intent intent = new Intent(ReleaseInformationActivity.this ,PublishSuccessfullyActivity.class);
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
                        Log.e("请求异常后的回调方法", "失败="+error.toString() + "/"+ msg);
                    }
                });
    }


}
