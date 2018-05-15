package com.yuqi.admin.py.view;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.ObserverManager;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.DingDanBean;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.interfaces.Deletesth;
import com.yuqi.admin.py.interfaces.IselectInter;
import com.yuqi.admin.py.utils.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zhanghongwei on 2017/12/22.
 */

public class ShoppCarViewSItem extends LinearLayout {

    private ImageView iv_tupian,iv_xuan,iv_jian,iv_jia;
    private EditText et_shulian;
    private TextView tv_shanchu,wbj_danjia,tv_bianji,iv_miaoshu,wbj_shulian;
    public getShoppingtrolleyBean.ObjectBean data;
    private Context mContext;
    private Deletesth deletesth;
    private ShoppCarViewSItem shoppCarViewBItem;
    private TextView tijiaodd;
    private boolean xuan = false;
    private IselectInter iselectInter;
    private LinearLayout iv;

    public ShoppCarViewSItem(Context context, getShoppingtrolleyBean.ObjectBean data) {
        this(context,null,data);
    }

    public ShoppCarViewSItem(Context context, @Nullable AttributeSet attrs,getShoppingtrolleyBean.ObjectBean data) {
        this(context, attrs,0,data);
    }

    public ShoppCarViewSItem(Context context, @Nullable AttributeSet attrs, int defStyleAttr,getShoppingtrolleyBean.ObjectBean data) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        shoppCarViewBItem = this;
        LayoutInflater.from(context).inflate(R.layout.item_gwc_weibianji,this);
        this.data = data;

        initView();
    }
    public void setSelectI(IselectInter i){this.iselectInter = i;};
    public void setCallback(Deletesth deletesth){
        this.deletesth = deletesth;
    }

    public void setVisi(boolean hiden){
        //这里你就协商你需要隐藏或是展现的的控件啊
        if (hiden){
            iv.setVisibility(View.VISIBLE);
            tv_shanchu.setVisibility(View.VISIBLE);
            wbj_danjia.setVisibility(View.GONE);
            wbj_shulian.setVisibility(View.GONE);
        }else {
            iv.setVisibility(View.GONE);
            tv_shanchu.setVisibility(View.GONE);
            wbj_danjia.setVisibility(View.VISIBLE);
            wbj_shulian.setVisibility(View.VISIBLE);
        }

    }

    private void initView(){
        final ObserverManager manager = ObserverManager.getInstance();
        iv_xuan = (ImageView) findViewById(R.id.iv_xuan);
        iv_tupian = (ImageView) findViewById(R.id.iv_tupian);
        iv_jian = (ImageView) findViewById(R.id.iv_jian);
        iv_jia = (ImageView) findViewById(R.id.iv_jia);
        et_shulian = (EditText) findViewById(R.id.et_shulian);
        iv_miaoshu = (TextView) findViewById(R.id.iv_miaoshu);
        tv_shanchu = (TextView) findViewById(R.id.tv_shanchu);
        wbj_danjia = (TextView) findViewById(R.id.wbj_danjia);
        wbj_shulian = (TextView) findViewById(R.id.wbj_shulian);
        iv = (LinearLayout)findViewById(R.id.iv);


        /**
         * 将data的数据设定到上面的控件上去，并添加相对于的点击事件
         */
        Glide.with(mContext)
                .load(data.getPicture())
                .placeholder(R.mipmap.icon_empty)
                .error(R.mipmap.icon_error)
                .into(iv_tupian);

        et_shulian.setText(String.valueOf(data.getCommodityNumber()));
        wbj_danjia.setText("￥"+String.valueOf(data.getCommodityPrice()));
        iv_miaoshu.setText(data.getCommodityName());
        wbj_shulian.setText("x"+data.getCommodityNumber());

        iv_jian.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!et_shulian.getText().toString().equals("0")) {
                    System.out.println(et_shulian.getText().toString());
                    et_shulian.setText(String.valueOf(Integer.valueOf(et_shulian.getText().toString()) - 1));
                }
            }
        });
        iv_jia.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                et_shulian.setText(String.valueOf(Integer.valueOf(et_shulian.getText().toString())+1));
            }
        });

        tv_shanchu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog dialog =  new  AlertDialog.Builder(mContext)
                        .setTitle("确认操作" )
                        .setMessage("确定删除商品吗？" )
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                deletesth.deletGoodsId(data.getCommodity_id(),shoppCarViewBItem);
                                deletesthHTTP();
                                dialog.dismiss();
                            }
                        })
                        .setNegativeButton("否", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
//                                shoppCarViewBItem.tv_shanchu.setVisibility(View.INVISIBLE);

                            }
                        })
                        .create();
                dialog.show();
            }
        });


//        tv_shanchu.setOnClickListener(new OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                manager.removeGoods(12);
//                manager.changeGoodsNum(12,2);
//            }
//        });

        iv_xuan.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!xuan){
                    iv_xuan.setImageResource(R.mipmap.xuanzhong);
                    xuan = true;
                    iselectInter.push(true,shoppCarViewBItem);
                }else{
                    iv_xuan.setImageResource(R.mipmap.meixuanzhong);
                    xuan = false;
                    iselectInter.push(false,shoppCarViewBItem);
                }
            }
        });
    }

    public void setSelect(boolean select){
        if(select){
            if(!xuan){
                iv_xuan.setImageResource(R.mipmap.xuanzhong);
                xuan = true;
                iselectInter.push(true,shoppCarViewBItem);
            }
        }else{
            if(xuan){
                iv_xuan.setImageResource(R.mipmap.meixuanzhong);
                xuan = false;
                iselectInter.push(false,shoppCarViewBItem);
            }
        }
    }

    private void deletesthHTTP() {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("id",data.getId()+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "deleteShoppingtrolley.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        com.yuqi.admin.py.utils.DialogUtil.start(mContext);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        com.yuqi.admin.py.utils.DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        com.yuqi.admin.py.utils.DialogUtil.finish();
                        Log.e("购物车删除=", responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        getShoppingtrolleyBean  CarBeanData = gson.fromJson(result, getShoppingtrolleyBean.class);//存商品数据
                        String state = CarBeanData.getState();
                        switch (state) {
                            case "200":
//                                addView(CarBeanData);
//                                ShoppCarViewBItem bItem = new ShoppCarViewBItem(mContext);
                                initView();

                                break;
                            case "210":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        com.yuqi.admin.py.utils.DialogUtil.finish();
                        ToastUtil.show(mContext,"网络异常");
                    }
                });
    }
}