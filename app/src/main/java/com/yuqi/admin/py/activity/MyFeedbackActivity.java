package com.yuqi.admin.py.activity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
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
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;


/**
 * Created by Administrator on 2018/1/4.
 * 意见反馈界面
 *
 */
public class MyFeedbackActivity extends BaseActivity {
    private EditText et_Content;
    private TextView tv_submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_feedback);
        init();
    }

    private void init() {
        et_Content = (EditText) findViewById(R.id.et_Content);
        tv_submit = (TextView) findViewById(R.id.tv_submit);
    }

    @Override
    public String title_text() {
        return  "意见反馈";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_submit:
                String mResult = isContentEmpty();
                if (!StringUtil.isEmpty(mResult)) {
                    ToastUtil.show(this, mResult);
                } else {
//                    String submit = "你反馈的意见是" + et_Content;
//                    ToastUtil.show(this, submit);
                    submit();
                }
                break;
        }
    }

    private void submit() {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", CommonData.user_id+"");
        params1.addQueryStringParameter("feedbackContext", et_Content.getText().toString());

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "Feedback.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(MyFeedbackActivity.this);
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
                        Bean xiugai = gson.fromJson(result, Bean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = xiugai.getState();
                        switch (state) {
                            case "200":
                                finish();
                                break;
                            case "210":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(MyFeedbackActivity.this, CommonData.REQUEST_EXCEOTON);
                    }
                });

    }

    private String isContentEmpty() {
        if (StringUtil.isEmpty(StringUtil.getText(this, R.id.et_Content))) {
            return "请填写好您的宝贵意见";
        }
        return "";
    }

}
