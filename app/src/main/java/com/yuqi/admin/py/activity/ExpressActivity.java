package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.data.CommonData;

public class ExpressActivity extends BaseActivity {

    private WebView mWebView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_express);
        mWebView = (WebView) findViewById(R.id.web_express);
        initWebView();
    }

    private String postId;

    private void initWebView() {
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        postId = bundle.getString("postid");

        postId = "3101624653800";

        mWebView.loadUrl(CommonData.ORDER_EXPRESS);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                //返回值是true的时候控制去WebView打开，为false调用系统浏览器或第三方浏览器
                view.loadUrl(url);
                return true;
            }
        });
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);
    }

    @Override
    public String title_text() {
        return "快递查询";
    }

    @Override
    public void onClick(View v) {

    }
}
