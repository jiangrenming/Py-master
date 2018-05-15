package com.yuqi.admin.py;

/**
 * Created by Administrator on 2018/3/8/008.
 */

public interface PasswordCallBack {
    void onForgetPassword();

    void onInputCompleted(CharSequence password);

    void onPasswordCorrectly();

    void onCancel();
}
