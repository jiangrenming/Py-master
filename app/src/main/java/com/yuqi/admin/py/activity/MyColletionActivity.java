package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.ColletionListAdapter;
import com.yuqi.admin.py.adapter.VideoListAdapter;
import com.yuqi.admin.py.bean.ColletionBean;
import com.yuqi.admin.py.bean.VideoBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2018/4/13/013.
 */

public class MyColletionActivity extends BaseActivity{


    private int user_id;
    private ListView listView;
    private ColletionBean colletionBean;
    private ColletionListAdapter colletionAdapter;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_colletion);
        listView = (ListView) findViewById(R.id.colletion_listview);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        user_id = bundle.getInt("user_id");
        intent.getExtras();
        initColletionList();

    }

    private void initColletionList() {

        RequestParams params = new RequestParams();
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        params.addQueryStringParameter("user_id",user_id+"");
        Log.e(CommonData.REQUEST_PARAMETER, user_id+"");
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "getCommoditycollect.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(MyColletionActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        ColletionBean colletionBean = gson.fromJson(result, ColletionBean.class);
                        String status = colletionBean.getStatus();
                        switch (status) {
                            case "200":
                                colletionToBean(colletionBean);

                                break;
                            case "210":
                                ToastUtil.show(MyColletionActivity.this,"网络错误");
                                break;
                        }
                    }



                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });


    }

    private void colletionToBean(ColletionBean colletionBean){
        colletionAdapter = new ColletionListAdapter(MyColletionActivity.this ,colletionBean);
        listView.setAdapter(colletionAdapter);
        colletionAdapter.notifyDataSetInvalidated();

    }

    @Override
    public String title_text() {
        return "商品收藏";
    }

    @Override
    public void onClick(View v) {

    }
}
