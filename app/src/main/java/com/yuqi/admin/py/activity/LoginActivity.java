package com.yuqi.admin.py.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Message;
import android.os.StrictMode;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.HttpParams;
import com.lzy.okgo.request.BaseRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.LoginBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.service.BaseService;
import com.yuqi.admin.py.service.impl.UserService;
import com.yuqi.admin.py.service.listener.CommonResultListener;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.My2CookieStore;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import okhttp3.Call;

/**
 * Created by Administrator on 2017/11/22.
 *      登录界面
 */
public class LoginActivity extends BaseActivity {
    private EditText ll_shoujihao ,ll_mima;
    //先定义
    SharedPreferences sp = null;
    private UserService userService;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //初始化控件
        init();
//        userService = new UserService(this);
    }

    /**
     * 
     */
    private void init() {
        ll_shoujihao = (EditText)findViewById(R.id.ll_shoujihao);
        ll_mima = (EditText)findViewById(R.id.ll_mima);

        //1、获取Preferences
        SharedPreferences settings = getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        //2、取出数据
        String name = settings.getString("zcname","" );
        ll_shoujihao.setText(name);
    }


    @Override
    public String title_text() {
        return null;
    }

    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.ll_shoujihao))){return "请填写账号";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.ll_mima))){return "请填写密码";}
        return "";
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent();
        switch (v.getId()){
            case R.id.l_login:
                String mResult = isContentEmpty();
                String number = ll_shoujihao.getText().toString();
                boolean judge = StringUtil.isMobile(number);

                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    if (judge == true) {
                        submit();
                    } else {
                        ToastUtil.show(LoginActivity.this, "请输入正确的手机号码!");
                        return;
                    }
                }
                break;

            case R.id.l_Register_new_account:
                    intent.setClass(LoginActivity.this, RegisterActivity.class);
                    startActivity(intent);
                break;

            case R.id.l_Forgot_password:
                    intent.setClass(LoginActivity.this, PasswordActivity1.class);
                    startActivity(intent);
                break;
        }
    }

    private HttpUtils http;
    private void submit() {
        RequestParams params = new RequestParams();
        params.addBodyParameter("accounts",ll_shoujihao.getText()+"");// 账号
        params.addBodyParameter("password",ll_mima.getText()+"");// 密码
        http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
//        HttpParams params = new HttpParams();
//        params.put("accounts", ll_shoujihao.getText()+"");
//        params.put("password", ll_mima.getText()+"");
//        OkGo.post("http://192.168.0.111:8080/pyjh/app/user/login")
//                .tag(this)//url请求地址
//                .params(params)
//                .isMultipart(true)
//                .execute(new StringCallback() {
//
//
//                    @Override
//                    public void onSuccess(String s, Call call, okhttp3.Response response) {
////                        Gson son = new Gson();
////                        jiluInfo info = son.fromJson(s, jiluInfo.class);//jiluInfo 解析实体类
////                        int getStatus = info.getStatus();
////                        Message msg = new Message();
////                        if (getStatus == 200) {
//
//
//                        Log.i("success",s);
//                        }
////                        else {
////                            Toast.makeText(getApplicationContext(), "constant.SYSTEM_ERROR", Toast.LENGTH_SHORT).show();
////
////                        }
//                    @Override
//                    public void onError(Call call, okhttp3.Response response, Exception e) {
//                        super.onError(call, response, e);
//                        Toast.makeText(getApplicationContext(), "constant.ON_ERROR", Toast.LENGTH_SHORT).show();
//                    }
//                    @Override
//                    public void onBefore(BaseRequest request) {
//                        super.onBefore(request);
//                        Log.i("request","onBefore: =="+request.getParams());
//                    }
//                    @Override
//                    public void upProgress(long currentSize, long totalSize, float progress, long networkSpeed) {
//                        //这里回调上传进度(该回调在主线程,可以直接更新ui)
//                    }
//                });

//        userService.login(ll_shoujihao.getText()+"", ll_mima.getText()+"", new CommonResultListener<String>(this) {
//            @Override
//            public void successHandle(String result) {
//                Log.e("返回数据=", "返回数据="+result);
//                LoginAction.login(result, LoginActivity.this);
//                finish();
//                        ActivityActionHelper.goToMain(LoginActivity.this);
//                 finish();
//            }
//        });
//        OkGo.post(CommonData.BASE_URL + url + urlParam).upJson(jsonObject).tag(url).execute(new BaseService.DefaultJsonObjectCallback(responseListener));
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"login.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(LoginActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("请求成功后的回调方法", responseInfo.result + "/");
                        String  result =  responseInfo.result;
                        boolean a = false;


                            Gson gson = new Gson();//初始化
                            LoginBean login = gson.fromJson(result, LoginBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：
                            String  state = login.getState();
                            switch (state){
                                case "200":
                                    CommonData.accounts = login.getObject().getUser().getAccounts();
//                                    CommonData.password = login.getObject().getUser().getPassword();
//                                    CommonData.token = (String) login.getObject().getUser().getToken();
                                    CommonData.user_id =  login.getObject().getUser().getId();
                                    CommonData.portrait = login.getObject().getUserinfo().getPortrait();
                                    CommonData.nickname = login.getObject().getUserinfo().getNickname();
                                    CommonData.gender = login.getObject().getUserinfo().getGender();
                                    CommonData.mailbox = (String) login.getObject().getUserinfo().getMailbox();
                                    CommonData.phoneNumber = login.getObject().getUserinfo().getPhoneNumber();
                                    CommonData.companyName = (String) login.getObject().getUserinfo().getCompanyName();
                                    CommonData.balance = login.getObject().getUserinfo().getBalance();
                                    CommonData.creationTime = login.getObject().getUserinfo().getCreationTime();
                                    CommonData.company_integral = login.getObject().getUserinfo().getCompany_integral();
                                    Intent intent = new Intent();
                                    intent.setClass(LoginActivity.this, ShouyActivity.class);
                                    Bundle bundle = new Bundle();
                                    intent.putExtra("user_id",login.getObject().getUser().getId());
                                    intent.putExtras(bundle);
                                    CommonData.state = state;
//                                    ToastUtil.show(LoginActivity.this,"登录成功");
                                    startActivity(intent);
                                    finish();
                                    dl();
                                    break;
                                case "500":
                                    ToastUtil.show(LoginActivity.this,"密码错误");
                                    break;
                                case "404":
                                    ToastUtil.show(LoginActivity.this,"帐号不存在");
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
    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
    private void dl() {
            sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
            //2、让setting处于编辑状态
            SharedPreferences.Editor editor = sp.edit();
            //3、存放数据
            editor.putString("zcname", ll_shoujihao.getText().toString().trim());
            //4、完成提交
            editor.commit();
    }

}
