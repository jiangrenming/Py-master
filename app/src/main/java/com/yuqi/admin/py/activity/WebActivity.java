package com.yuqi.admin.py.activity;


import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;

public class WebActivity extends BaseActivity {

	WebView web;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_web);
		init();
	}
	@SuppressLint({"SetJavaScriptEnabled","JavascriptInterface"})
	private void init(){
		web = (WebView)findViewById(R.id.web);
		WebSettings setting = web.getSettings();
		setting.setLoadWithOverviewMode(true);
		setting.setJavaScriptCanOpenWindowsAutomatically(true);
		setting.setJavaScriptEnabled(true);
		setting.setUseWideViewPort(true);
		web.addJavascriptInterface(new MyInterface(this),"my");
		web.setWebViewClient(new WebViewClient(){
			@Override
			public boolean shouldOverrideUrlLoading(WebView view, String url) {
				view.loadUrl(url);
				return true;
			}
		});
		web.loadUrl(getIntent().getStringExtra("url"));
		
		web.setWebChromeClient(wvcc);
		
		web.setOnKeyListener(new View.OnKeyListener() {  
	            @Override  
	            public boolean onKey(View v, int keyCode, KeyEvent event) {  
	                if (event.getAction() == KeyEvent.ACTION_DOWN) {  
	                    if (keyCode == KeyEvent.KEYCODE_BACK && web.canGoBack()) {  
	                    	//表示按返回键时的操作
	                    	web.goBack();   //后退  
	                        //webview.goForward();//前进
	                        return true;    //已处理  
	                    }  
	                }  
	                return false;  
	            }  
	        });
	}
	 WebChromeClient wvcc = new WebChromeClient() {  
         @Override  
         public void onReceivedTitle(WebView view, String title) {  
             super.onReceivedTitle(view, title);  
             setHeadTitle(title);
         }  

     };

	@Override
	public String title_text() {
		return "彭友聚汇";
	}

	@Override
	public void onClick(View v) {

	}

	class MyInterface{
		Context context;
		public MyInterface(Context context){
			this.context = context;
		}
		@JavascriptInterface
		public void intoActivity(int type,String data){
//
		}
		@JavascriptInterface
		public void intoActivity(int type)
		{

		}
	}
    @Override
    protected void onDestroy() {
    	super.onDestroy();
    	web.destroy();
    }
}
