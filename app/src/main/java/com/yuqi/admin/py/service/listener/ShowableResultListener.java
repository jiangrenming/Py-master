package com.yuqi.admin.py.service.listener;

/**
 * Created by jill on 2016/12/28.
 */

public interface ShowableResultListener<T> extends ResultListener<T>{

    boolean isShowToast();

    boolean isShowLoading();
}
