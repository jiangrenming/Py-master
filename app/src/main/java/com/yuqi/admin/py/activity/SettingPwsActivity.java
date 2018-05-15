package com.yuqi.admin.py.activity;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
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
import com.yuqi.admin.py.PasswordCallBack;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.IsPayPwsBean;
import com.yuqi.admin.py.bean.PushPwsBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.MDProgressBar;
import com.yuqi.admin.py.view.PasswordKeyboard;
import com.yuqi.admin.py.view.PasswordKeypad;
import com.yuqi.admin.py.view.PasswordView;

public class SettingPwsActivity extends BaseActivity implements View.OnClickListener, PasswordKeyboard.OnPasswordInputListener,
        MDProgressBar.OnPasswordCorrectlyListener {
    private boolean state;
    private TextView errorMsgTv;

    private PasswordCallBack mCallback;

    private RelativeLayout passwordContainer;

    private MDProgressBar progressBar;

    private PasswordView passwordView;

    private int passwordCount;

    private boolean passwordState = true;

    PasswordKeyboard numberKeyBoard;

    private StringBuffer mPasswordBuffer = new StringBuffer();

    private final int STATUS1=0;
    private final int STATUS2=1;

    private int user_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.password_keypad);
        errorMsgTv = (TextView) findViewById(R.id.error_msg);
        TextView forgetPasswordTv = (TextView) findViewById(R.id.forget_password);
        TextView cancelTv = (TextView) findViewById(R.id.cancel_dialog);

        passwordContainer = (RelativeLayout) findViewById(R.id.password_content);
        progressBar = (MDProgressBar) findViewById(R.id.password_progressBar);
        progressBar.setOnPasswordCorrectlyListener(this);
        passwordView = (PasswordView) findViewById(R.id.password_inputBox);

        setPasswordCount(6);
        //设置密码长度
        if (passwordCount > 0) {
            passwordView.setPasswordCount(passwordCount);
        }

        numberKeyBoard = (PasswordKeyboard) findViewById(R.id.password_keyboard);
        numberKeyBoard.setOnPasswordInputListener(this);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id");
        intent.getExtras();


        cancelTv.setOnClickListener(this);
        forgetPasswordTv.setOnClickListener(this);



        setCallback(new PasswordCallBack() {
            int i=0;
            String pws1="";
            String pws2="";
            @Override
            public void onForgetPassword() {
                Toast.makeText(getApplicationContext(),"修改密码",Toast.LENGTH_LONG).show();
            }

            @Override
            public void onInputCompleted(final CharSequence password) {
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

                        switch(i)
                        {
                            case STATUS1:
                                pws1 = password.toString();
                                numberKeyBoard.resetKeyboard();
                                passwordView.clearPassword();
                                progressBar.setVisibility(View.GONE);
                                passwordContainer.setVisibility(View.VISIBLE);
                                setPasswordState(false, "请再次输入支付密码");
                                i=1;
                                break;
                            case STATUS2:
                                pws2 = password.toString();
                                if(pws2.equals(pws1)){
                                    setPasswordState(true, "支付密码设置完成");

                                    RequestParams params = new RequestParams();
                                    HttpUtils http = new HttpUtils();
                                    http.configCurrentHttpCacheExpiry(1000 * 10);
                                    Log.e("获取默认地址=",  user_id +"");

//                                    int pws = Integer.parseInt(pws2);
                                    http.send(HttpRequest.HttpMethod.POST,
                                            CommonData.URL + "updatePayPassword.action?user_id=" + user_id +"&&newPWD=" + pws2,
                                            params, new RequestCallBack<String>() {
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
                                                    Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result + "/");
                                                    String result = responseInfo.result;

                                                    Gson gson = new Gson();//初始化
                                                    PushPwsBean bean = gson.fromJson(result, PushPwsBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                                                    String state = bean.getState();
                                                    switch (state) {
                                                        case "200":
                                                            ToastUtil.show(SettingPwsActivity.this, "支付密码设置完成");
                                                            finish();
                                                            break;
                                                        case "210":
                                                            ToastUtil.show(SettingPwsActivity.this, "网络错误");
                                                            break;
                                                    }
                                                }

                                                @Override
                                                public void onFailure(HttpException error, String msg) {
                                                    DialogUtil.finish();
                                                    Log.e(CommonData.REQUEST_EXCEOTON, "失败=" + error.toString() + "/" + msg);
                                                }
                                            });




                                }else{
                                    setPasswordState(false, "两次密码不一致重新输入");
                                    i=0;
                                }

                                break;
                            default:

                                break;
                        }

//                        if (state) {
//                            pws1[0] = password.toString();
//                            ToastUtil.show(SettingPwsActivity.this, pws1[0]);
//                            setPasswordState(true);
//                            state = true;
//                            numberKeyBoard.resetKeyboard();
//                            passwordView.clearPassword();
//                            progressBar.setVisibility(View.GONE);
//                            passwordContainer.setVisibility(View.VISIBLE);
////                            errorMsgTv.setText(msg);
//                        } else {
//                            pws2[0] = password.toString();
//                            ToastUtil.show(SettingPwsActivity.this, password.toString());
//                            if(pws2[0].equals(password.toString())){
////                                setPasswordState(false, "两次密码一致");
//                                state = true;
//                            }else{
//                                setPasswordState(false, "请再次输入支付密码");
//                                state = false;
//                            }


//                        }
                    }
                },1000);
            }

            @Override
            public void onPasswordCorrectly() {
                ToastUtil.show(SettingPwsActivity.this,"支付密码设置成功");
                finish();
            }

            @Override
            public void onCancel() {
                //todo:做一些埋点类的需求
                finish();
            }
        });


    }


    /**
     * 设置密码长度
     */
    public void setPasswordCount(int passwordCount) {
        this.passwordCount = passwordCount;
    }


    @Override
    public String title_text() {
        return null;
    }

    @Override
    public void onClick(View v) {
        if (R.id.cancel_dialog == v.getId()) {
            if (mCallback != null) {
                mCallback.onCancel();
            }
//            dismiss();
        } else if (R.id.forget_password == v.getId()) {
            if (mCallback != null) {
                mCallback.onForgetPassword();
            }
        }
    }

    public void setCallback(PasswordCallBack callBack) {
        this.mCallback = callBack;
    }

    public void setPasswordState(boolean correct) {
        setPasswordState(correct, "");
    }

    public void setPasswordState(boolean correct, String msg) {
        passwordState = correct;
        if (correct) {
            progressBar.setSuccessfullyStatus();
            ToastUtil.show(SettingPwsActivity.this,msg);
//            numberKeyBoard.resetKeyboard();
        } else {
            numberKeyBoard.resetKeyboard();
            passwordView.clearPassword();
            progressBar.setVisibility(View.GONE);
            passwordContainer.setVisibility(View.VISIBLE);
            errorMsgTv.setText(msg);
        }
    }

    @Override
    public void onPasswordCorrectly() {
        if (mCallback != null) {
            mCallback.onPasswordCorrectly();
        }
    }

    private void startLoading(CharSequence password) {
        passwordContainer.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        if (mCallback != null) {
            mCallback.onInputCompleted(password);
}
    }

    @Override
    public void onInput(String character) {
        if (PasswordKeyboard.DEL.equals(character)) {
            if (mPasswordBuffer.length() > 0) {
                mPasswordBuffer.delete(mPasswordBuffer.length() - 1, mPasswordBuffer.length());
            }
        } else if (PasswordKeyboard.DONE.equals(character)) {


        } else {
            if (!passwordState) {
                if (!TextUtils.isEmpty(errorMsgTv.getText())) {
                    errorMsgTv.setText("");
                }
            }
            mPasswordBuffer.append(character);
        }
        passwordView.setPassword(mPasswordBuffer);
        if (mPasswordBuffer.length() == passwordView.getPasswordCount()) {
            startLoading(mPasswordBuffer);
        }
    }
}
