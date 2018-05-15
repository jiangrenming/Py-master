package com.yuqi.admin.py.activity;

import android.content.Intent;
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
import android.widget.ImageView;
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
import com.yuqi.admin.py.bean.AuthCodeBean;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.bean.LoginBean;
import com.yuqi.admin.py.bean.MobileCodeBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.My2CookieStore;
import com.yuqi.admin.py.utils.MyCookieStore;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import org.apache.http.client.CookieStore;
import org.apache.http.cookie.Cookie;
import org.apache.http.impl.client.DefaultHttpClient;

import java.util.List;

/**
 * Created by Administrator on 2017/11/27.
 *  修改密码1
 */
public class PasswordActivity1 extends BaseActivity {
    private EditText wjmm_shoujihao,wjmm_psw1,wjmm_psw2,wjmm_identifying_code;
    private Button btn_captcha_psw;
    private SpannableStringBuilder sb;
    private CountDownTimer countTimer;
    private Intent intent;
    private  String authCode;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password1);
        init();
    }
    private MobileCodeBean bean;
    private String session_id;
    private RequestParams params;
    private HttpUtils http;
    private void init() {
        wjmm_shoujihao= (EditText)findViewById(R.id.wjmm_shoujihao);
        wjmm_psw1= (EditText)findViewById(R.id.wjmm_psw1);
        wjmm_psw2= (EditText)findViewById(R.id.wjmm_psw2);
        wjmm_identifying_code= (EditText)findViewById(R.id.wjmm_identifying_code);
        btn_captcha_psw= (Button)findViewById(R.id.btn_captcha_psw);
        countTimer = new CountTimer(10000, 1000);
        sb = new SpannableStringBuilder();
        wjmm_shoujihao.setText(CommonData.accounts);

    }
    //指定onClick属性方式
    public void get2CodeClick(View v){

        boolean judge = StringUtil.isMobile(wjmm_shoujihao.getText().toString());
                if(judge!=true){
                    ToastUtil.show(PasswordActivity1.this, "请填写正确手机号码");
                    return ;
                }else {
                    countTimer.start();
                    params = new RequestParams();
                    params.addBodyParameter("accounts",wjmm_shoujihao.getText()+"");// 账号
                    http = new HttpUtils();
                    http.configCookieStore(My2CookieStore.cookieStore);
                    http.configCurrentHttpCacheExpiry(1000 * 10);
                    http.send(HttpRequest.HttpMethod.POST,
                            CommonData.URL+"MobileCode.action",
                            params,new RequestCallBack<String>(){
                                @Override
                                public void onSuccess(ResponseInfo<String> responseInfo) {
                                    DefaultHttpClient dh = (DefaultHttpClient) http.getHttpClient();
                                    My2CookieStore.cookieStore = dh.getCookieStore();
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
                                    String  result =  responseInfo.result;
                                    Log.e("验证码数据=", "数据="+result);//660 账号不存在 {"mobilecode":"494036","state":"200"}
                                    //210:验证码获取失败 400：帐号已存在
                                    Gson gson = new Gson();//初始化
                                    bean = gson.fromJson(result, MobileCodeBean.class);
                                    String state = bean.getState();
                                    authCode = bean.getMobilecode();
                                    Log.e("authCode=", "数据="+authCode);
                                    switch (state) {
                                        case "200"://验证码获取成功
                                            ToastUtil.show(PasswordActivity1.this, "验证码获取成功");
                                            break;
                                        case "210"://210:验证码获取失败
                                            ToastUtil.show(PasswordActivity1.this, "验证码获取失败");
                                            break;
                                        case "400":
                                            ToastUtil.show(PasswordActivity1.this, "账号已存在");
                                            break;
                                        case "660"://账号不存在
                                            ToastUtil.show(PasswordActivity1.this, "账号不存在");
                                            break;
                                    }
                                }

                                @Override
                                public void onStart() {
                                    super.onStart();
                                    DialogUtil.start(PasswordActivity1.this);
                                }

                                @Override
                                public void onLoading(long total, long current, boolean isUploading) {
                                    super.onLoading(total, current, isUploading);
                                    DialogUtil.finish();
                                }

                                @Override
                                public void onFailure(HttpException e, String s) {

                                }
                            });
                            }
    }

    @Override
    public String title_text() {
        return "重置登录密码";
    }

    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wjmm_shoujihao))){return "请填写手机号";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wjmm_psw1))){return "请填写你要设置的新密码";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wjmm_psw2))){return "请填写你要设置的新密码";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.wjmm_identifying_code))){return "请填写收到的验证码";}
        return "";
    }

    private String dentifying_code;
    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.wjmm_tijiao:
                String mResult = isContentEmpty();
                String number = wjmm_shoujihao.getText().toString();
                dentifying_code = wjmm_identifying_code.getText().toString();
                boolean judge = StringUtil.isMobile(number);
                Log.e("请求成功后的回调方法===", dentifying_code + "/"+authCode);
                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    if (judge == true&& wjmm_psw1.getText().toString().equals(wjmm_psw2.getText().toString())==true) {
                        submit();
                    } else if (judge == false){
                        ToastUtil.show(PasswordActivity1.this, "请输入正确的手机号码!");
                        return;
                    }else if(wjmm_psw1.getText().toString().equals(wjmm_psw2.getText().toString())==false){
                        ToastUtil.show(PasswordActivity1.this, "两次密码不一致!");
                        return;
                    }
//                    else if(!dentifying_code.equals(authCode)){
//                        ToastUtil.show(PasswordActivity1.this, "请输入正确的验证码!");
//                        return;
//                    }else if(!wjmm_psw1.getText().toString().equals(wjmm_psw2.getText().toString())){
//                        ToastUtil.show(PasswordActivity1.this, "两次密码输入不一致!");
//                        return;
//                    }
//                    else if (judge1 == false){
//                        ToastUtil.show(PasswordActivity1.this, "请输入正确的邮箱地址!");
//                        return;
//                    }
                }
                break;
        }
    }


    private void submit() {
        params = new RequestParams();
        params.addBodyParameter("accounts",wjmm_shoujihao.getText()+"");// 账号
        params.addBodyParameter("password",wjmm_psw2.getText()+"");// 修改后的密码
        params.addBodyParameter("code",wjmm_identifying_code.getText()+"");// 邮箱
        http = new HttpUtils();
        params.addHeader("JSESSIONID",session_id);
        http.configCurrentHttpCacheExpiry(1000*10);
        http.configCookieStore(My2CookieStore.cookieStore);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"forgetPassword.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(PasswordActivity1.this);
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
                        Gson gson = new Gson();//初始化
                        Bean bean = gson.fromJson(result, Bean.class);
                        String state = bean.getState();
                        switch (state){
                            case "200":
                                finish();
                                ToastUtil.show(PasswordActivity1.this,"修改成功");
                                break;
                            case "210":
                                ToastUtil.show(PasswordActivity1.this,"修改失败");
                                break;
                            case "500":
                                ToastUtil.show(PasswordActivity1.this,"密码错误");
                                break;
                            case "404":
                                ToastUtil.show(PasswordActivity1.this,"帐号不存在");
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
            ForegroundColorSpan colorSpan = new ForegroundColorSpan(ContextCompat.getColor(PasswordActivity1.this, android.R.color.holo_blue_dark));
            sb.setSpan(colorSpan, 0, index, Spannable.SPAN_INCLUSIVE_EXCLUSIVE);
            btn_captcha_psw.setText(sb);
            //设置倒计时中的按钮外观
            btn_captcha_psw.setClickable(false);//倒计时过程中将按钮设置为不可点击
            btn_captcha_psw.setBackgroundColor(Color.parseColor("#c7c7c7"));
            btn_captcha_psw.setTextColor(ContextCompat.getColor(PasswordActivity1.this, android.R.color.black));
            btn_captcha_psw.setTextSize(16);
        }

        /**
         * 倒计时完成后调用
         */
        @Override
        public void onFinish() {
            Log.e("Tag", "倒计时完成");
            //设置倒计时结束之后的按钮样式
            btn_captcha_psw.setBackgroundColor(ContextCompat.getColor(PasswordActivity1.this, android.R.color.holo_blue_light));
            btn_captcha_psw.setTextColor(ContextCompat.getColor(PasswordActivity1.this, android.R.color.white));
            btn_captcha_psw.setTextSize(18);
            btn_captcha_psw.setText("重新发送");
            btn_captcha_psw.setClickable(true);
        }
    }
}
