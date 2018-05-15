package com.yuqi.admin.py.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.ForegroundColorSpan;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.AuthCodeBean;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.bean.InvitationBean;
import com.yuqi.admin.py.bean.LoginBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.MyCookieStore;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.HttpCookie;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.List;

/**
 * Created by Administrator on 2017/11/23.
 * 注册
 */
public class RegisterActivity extends BaseActivity {
    private EditText zc_shoujihao,zc_mima,zc_lishugongsi;
    private EditText et_invitation_code;
    private EditText et_auth_code;
    private Button btnCaptcha;

    private SpannableStringBuilder sb;

    private String tv_response,tv_jsessionid;
    private SharedPreferences preference;
    private SharedPreferences.Editor editor;
    //先定义
    SharedPreferences sp = null;

    Intent intent = new Intent();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        zc_shoujihao = (EditText)findViewById(R.id.zc_shoujihao);
        zc_mima = (EditText)findViewById(R.id.zc_mima);
        zc_lishugongsi = (EditText)findViewById(R.id.zc_lishugongsi);
        et_invitation_code = (EditText)findViewById(R.id.et_invitation_code);
        et_auth_code = (EditText) findViewById(R.id.et_auth_code);
        btnCaptcha = (Button) findViewById(R.id.btn_captcha);
        countTimer = new CountTimer(60000, 1000);
        sb = new SpannableStringBuilder();

        preference=getSharedPreferences("cookie", MODE_PRIVATE);
        editor=preference.edit();
    }

    private RequestParams params;
    private HttpUtils http;
    private String session_id;
    //指定onClick属性方式
    public void getCodeClick(View v) {
        boolean judge = StringUtil.isMobile(zc_shoujihao.getText().toString());
        if(StringUtil.isEmpty(zc_shoujihao.getText().toString())&&judge!=true){
            ToastUtil.show(RegisterActivity.this, "请填写正确手机号码");
            return;
        }
        countTimer.start();
            params = new RequestParams();
            params.addBodyParameter("accounts", zc_shoujihao.getText() + "");// 账号
            http = new HttpUtils();
            http.configCookieStore(MyCookieStore.cookieStore);
            http.configCurrentHttpCacheExpiry(1000 * 10);
            http.send(HttpRequest.HttpMethod.POST,
                    CommonData.URL + "getMobileCode.action",
                    params, new RequestCallBack<String>() {
                        @Override
                        public void onSuccess(ResponseInfo<String> responseInfo) {
                            DialogUtil.finish();

                            DefaultHttpClient dh = (DefaultHttpClient) http.getHttpClient();
                                                 MyCookieStore.cookieStore = dh.getCookieStore();
                                                CookieStore cs = dh.getCookieStore();
                                                   List<Cookie> cookies = cs.getCookies();
                                                    session_id = null;
                                                  for (int i = 0; i < cookies.size(); i++) {
                                                             if ("JSESSIONID".equals(cookies.get(i).getName())) {
                                                                 session_id = cookies.get(i).getValue();
                                                                   break;
                                                                }
                                                        }
                                                    Log.d("jack", "比较sessionid" + session_id);

                            String result = responseInfo.result;
                            Log.e("验证码数据=", "数据=" + result);
                            //210:验证码获取失败 400：帐号已存在
                            Gson gson = new Gson();//初始化
                            AuthCodeBean bean = gson.fromJson(result, AuthCodeBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：
                            String state = bean.getState();
                            authCode = bean.getCode();
                            switch (state) {
                                case "200"://验证码获取成功
                                    ToastUtil.show(RegisterActivity.this, "验证码获取成功");
                                    break;
                                case "210"://210:验证码获取失败
                                    ToastUtil.show(RegisterActivity.this, "验证码获取失败");
                                    break;
                                case "400":
                                    ToastUtil.show(RegisterActivity.this, "账号已存在");
                                    break;
                            }
                        }
                        @Override
                        public void onStart() {
                            super.onStart();
                            DialogUtil.start(RegisterActivity.this);
                        }

                        @Override
                        public void onLoading(long total, long current, boolean isUploading) {
                            super.onLoading(total, current, isUploading);
                            DialogUtil.finish();
                        }

                        @Override
                        public void onFailure(HttpException e, String s) {
                            ToastUtil.show(RegisterActivity.this, "服务器异常");

                        }
                    });
    }

    private CountDownTimer countTimer;
    private String authCode;
    @Override
    public String title_text() {
        return "注册";
    }

    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.zc_lishugongsi))){return "请填写隶属公司";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.zc_shoujihao))){return "请填写账号";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.zc_mima))){return "请填写密码";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.et_auth_code))){return "请填写验证码";}
        return "";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.zc_zhuce:
                String mResult = isContentEmpty();
                String number = zc_shoujihao.getText().toString();
//                code = et_auth_code.getText().toString();
//                String invitationCode = et_invitation_code.getText().toString();
                boolean judge = StringUtil.isMobile(number);
                Log.e("验证码数据=", "数据="+authCode);
                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    if (judge == true) {
                        submit();
                    }
//                    else if(code!=authCode){
//                        ToastUtil.show(RegisterActivity.this, "验证码输入错误!");
//                        return;
//                    }
                    else if(judge != true){
                        ToastUtil.show(RegisterActivity.this, "请输入正确的手机号码!");
                        return;
                    }else {
                        ToastUtil.show(RegisterActivity.this, "error!");
                    }
                }
                break;

            case R.id.zc_laoyonghu:
                intent.setClass(RegisterActivity.this,LoginActivity.class);
                startActivity(intent);
                finish();
                break;

        }
    }

    private void submit() {

        params = new RequestParams();
        params.addBodyParameter("accounts",zc_shoujihao.getText()+"");// 账号
        params.addBodyParameter("password",zc_mima.getText()+"");// 密码
        params.addBodyParameter("company",zc_lishugongsi.getText()+"");// 隶属公司
        params.addBodyParameter("invitationCode",et_invitation_code.getText()+"");// 邀请码
        params.addBodyParameter("code",et_auth_code.getText()+"");// 验证码
        http = new HttpUtils();
        params.addHeader("JSESSIONID",session_id);
        http.configCurrentHttpCacheExpiry(1000*10);
        Log.e("注册请求数据=", "数据="+et_auth_code.getText()+"");
        http.configCookieStore(MyCookieStore.cookieStore);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"registration.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(RegisterActivity.this);
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
                        Log.e("注册数据=", "数据="+result);
                        Gson gson = new Gson();//初始化
                        InvitationBean bean = gson.fromJson(result, InvitationBean.class);
                        String state = bean.getState();
                        Intent intent = new Intent();
                        switch (state){
                            case "200"://没有邀请码的注册
                                intent.setClass(RegisterActivity.this, LoginActivity.class);
                                ToastUtil.show(RegisterActivity.this,"注册成功");
                                startActivity(intent);
                                zh();
                                finish();
                                break;
                            case "210":
                                ToastUtil.show(RegisterActivity.this,"注册失败");
                                break;
                            case "400":
                                ToastUtil.show(RegisterActivity.this,"账号已存在");
                                break;
                            case "2000" ://有邀请码的注册2Y8BS7
                                intent.setClass(RegisterActivity.this, LoginActivity.class);
                                ToastUtil.show(RegisterActivity.this,"注册成功,现金红包已到您的个人中心");
                                startActivity(intent);
                                zh();
                                finish();
                                break;
                            case "605" :
                                ToastUtil.show(RegisterActivity.this,"验证码错误");
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        Log.e("注册请求失败", "失败="+error.toString() + "/"+ msg);
                    }
                });
    }

    private void zh() {
        sp = this.getSharedPreferences("userinfo", Context.MODE_PRIVATE);
        //2、让setting处于编辑状态
        SharedPreferences.Editor editor = sp.edit();
        //3、存放数据
        editor.putString("zcname", zc_shoujihao.getText().toString().trim());
//        zc_mima.setText(sp.getString("zcpswd", null));
        //4、完成提交
        editor.commit();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        countTimer.cancel();
    }



    /**
     * 点击按钮后倒计时
     */
    class CountTimer extends CountDownTimer {

        public CountTimer(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        /**
         * 倒计时过程中调用
         * @param millisUntilFinished
         */
        @Override
        public void onTick(long millisUntilFinished) {
            Log.e("Tag", "millisUntilFinished=" + millisUntilFinished);
//            Log.e("Tag", "倒计时=" + (millisUntilFinished/1000));
            //处理后的倒计时数值
            int time = (int) (Math.round((double) millisUntilFinished / 1000) - 1);
//            btnCaptcha.setText(String.valueOf(time)+"s后重新发送");
            //拼接要显示的字符串
            sb.clear();
            Log.e("Tag", "字符长度=" + sb.length());
            sb.append(String.valueOf(time));
            sb.append("s后重新发送");
            int index = String.valueOf(sb).indexOf("后");
            Log.e("Tag", "字符长度2=" + sb.length() + ",index=" + index);
            //给秒数和单位设置蓝色前景色
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(RegisterActivity.this, android.R.color.holo_blue_dark));
            sb.setSpan(colorSpan, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            btnCaptcha.setText(sb);
            //设置倒计时中的按钮外观
            btnCaptcha.setClickable(false);//倒计时过程中将按钮设置为不可点击
            btnCaptcha.setBackgroundColor(Color.parseColor("#c7c7c7"));
            btnCaptcha.setTextColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.black));
            btnCaptcha.setTextSize(16);
        }

        /**
         * 倒计时完成后调用
         */
        @Override
        public void onFinish() {
            Log.e("Tag", "倒计时完成");
            //设置倒计时结束之后的按钮样式
            btnCaptcha.setBackgroundColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.holo_blue_light));
            btnCaptcha.setTextColor(ContextCompat.getColor(RegisterActivity.this, android.R.color.white));
            btnCaptcha.setTextSize(18);
            btnCaptcha.setText("重新发送");
            btnCaptcha.setClickable(true);
        }
    }
}
