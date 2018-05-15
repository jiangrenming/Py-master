package com.yuqi.admin.py.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.AddressAdapter;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.bean.DingDanBean;
import com.yuqi.admin.py.bean.DshowShippingAddressALLBean;
import com.yuqi.admin.py.bean.XiuGdzBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.ResizableImageView;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 *
 * 收货地址管理
 *      获取全部地址
 */
public class SEditorsActivity extends BaseActivity  {
    private LinearLayout lv_tianxieshouhuodizhi;
    DshowShippingAddressALLBean syDizhi;
    private List<DingDanBean.DingdanBean> commodity;
    private Context mContext;
    XiuGdzBean xgdz;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_spdd_dizhibianji);
        mContext = this;
        // 订单信息
        commodity = (List<DingDanBean.DingdanBean>) getIntent().getSerializableExtra("dingDan");
        initView();
        // 获取全部地址
        AddressAdapterHTTP();
    }
    //控件初始化
    private void initView() {
        lv_tianxieshouhuodizhi = (LinearLayout) findViewById(R.id.lv_tianxieshouhuodizhi);
    }
    // 获取全部地址
    private void AddressAdapterHTTP() {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", CommonData.user_id+"");

        Log.e(CommonData.REQUEST_PARAMETER,"user_id"+ CommonData.user_id);
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "showShippingAddressALL.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SEditorsActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        syDizhi = gson.fromJson(result, DshowShippingAddressALLBean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = syDizhi.getState();
                        switch (state) {
                            case "200":
                                initDizhi(syDizhi);
                                break;
                            case "210":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
//                        DialogUtil.finish();
                        ToastUtil.show(SEditorsActivity.this,CommonData.REQUEST_EXCEOTON);
                    }
                });

    }
    @Override
    public String title_text() {
        return "管理收货地址";
    }
    //添加收货地址
    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.dzbj_xinzengshouhuodizhi://添加收货地址
                Intent intent = new Intent();
                intent.setClass(SEditorsActivity.this,SEditors1Activity.class);
                intent.putExtra("dingDan", (Serializable) commodity);
                startActivity(intent);
                finish();
                break;
        }
    }
    //地址管理数据
    private void initDizhi(DshowShippingAddressALLBean syDizhi)  {
        List<DshowShippingAddressALLBean.ObjectBean> bean =  syDizhi.getObject();
        View v;
        for(final DshowShippingAddressALLBean.ObjectBean bean1 : bean) {
            v = View.inflate(mContext, R.layout.item_dizhixinxi, null);

            final LinearLayout ll_quanbu = (LinearLayout)v.findViewById(R.id.ll_quanbu);

            final TextView  dz_xm = (TextView) v.findViewById(R.id.dz_xm);
            final TextView  dz_sjh = (TextView) v.findViewById(R.id.dz_sjh);
            final TextView  dz_lsgs = (TextView) v.findViewById(R.id.dz_lsgs);
            final TextView  dz_xxdz = (TextView) v.findViewById(R.id.dz_xxdz);
            final ImageView  dz_mr = (ImageView) v.findViewById(R.id.dz_mr);
            final ImageView  dz_bj = (ImageView) v.findViewById(R.id.dz_bj);
            final ImageView dz_sc = (ImageView) v.findViewById(R.id.dz_sc);
            final int ifDefaultAddress = bean1.getIfDefaultAddress();
            if (ifDefaultAddress==1){
                dz_mr.setImageResource(R.mipmap.xuanzhong);
            }else {
                dz_mr.setImageResource(R.mipmap.meixuanzhong);
            }
            dz_xm.setText(bean1.getConsignee());
            dz_sjh.setText(bean1.getPhoneNumber());
            dz_lsgs.setText(bean1.getShippingAddress());
            dz_xxdz.setText(bean1.getDetailedAddress());
            final int getId = bean1.getId();
            final String setConsignee = dz_xm.getText().toString();
            final String setPhoneNumber = dz_sjh.getText().toString();
            final String setShippingAddress = dz_lsgs.getText().toString();
            final String setStringdetailedAddress = dz_xxdz.getText().toString();
            lv_tianxieshouhuodizhi.addView(v);

            //编辑地址
            dz_bj.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    XiuGdzBean xgdz = new XiuGdzBean();
                    xgdz.setId(getId+"");
                    xgdz.setConsignee(dz_xm.getText().toString());
                    xgdz.setPhoneNumber(dz_sjh.getText().toString());
                    xgdz.setShippingAddress(dz_lsgs.getText().toString());
                    xgdz.setStringdetailedAddress(dz_xxdz.getText().toString());
                    xgdz.setIfDefaultAddress(ifDefaultAddress+"");
                    Intent intent = new Intent(SEditorsActivity.this, SEditors2Activity.class);
                    intent.putExtra("dingDan", (Serializable) commodity);
                    intent.putExtra("xgdz", (Serializable) xgdz);
                    startActivity (intent);
                    finish();
                }
            });
            //设置默认地址
           dz_mr.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    defaultAddress(getId,setConsignee,setPhoneNumber,setShippingAddress,setStringdetailedAddress,dz_mr,bean1);
                }
           });
            //删除地址
           dz_sc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    deleteShippingAddress(getId, ll_quanbu);
                }
            });
            //选择现用地址
            ll_quanbu.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    xgdz = new XiuGdzBean();
                    xgdz.setId(getId+"");
                    xgdz.setConsignee(dz_xm.getText().toString());
                    xgdz.setPhoneNumber(dz_sjh.getText().toString());
                    xgdz.setShippingAddress(dz_lsgs.getText().toString());
                    xgdz.setStringdetailedAddress(dz_xxdz.getText().toString());
                    xgdz.setIfDefaultAddress(ifDefaultAddress+"");
                    Intent intent = new Intent(SEditorsActivity.this,SConfirmationSingleActivity.class);
                    intent.putExtra("xgdz",(Serializable) xgdz);
                    intent.putExtra("dingDan", (Serializable) commodity);
                    startActivity(intent);
                    finish();
                }
            });
        }
    }
    //设置默认地址
    private void defaultAddress(final int getId,final String setConsignee,final String setPhoneNumber,final String setShippingAddress,final String setStringdetailedAddress, final ImageView dz_mr, final DshowShippingAddressALLBean.ObjectBean bean1) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("id",getId+"");
        params1.addQueryStringParameter("user_id",CommonData.user_id+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "defaultAddress.action",
                params1,
                new RequestCallBack<String>() {
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
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String result = responseInfo.result;

                        Gson gson = new Gson();//初始化
                        Bean morendizhi = gson.fromJson(result, Bean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = morendizhi.getState();
                        switch (state) {
                            case "200":
                                ToastUtil.show(SEditorsActivity.this, "默认设置成功");
                                dz_mr.setImageResource(R.mipmap.xuanzhong);
                                // 重新获取一次全部地址信息
//                                lv_tianxieshouhuodizhi.clearFocus();
//                                syDizhi.getObject().clear();
//                                AddressAdapterHTTP();
                                xgdz = new XiuGdzBean();
                                xgdz.setId(getId+"");
                                xgdz.setConsignee(setConsignee);
                                xgdz.setPhoneNumber(setPhoneNumber);
                                xgdz.setShippingAddress(setShippingAddress);
                                xgdz.setStringdetailedAddress(setStringdetailedAddress);

                                Intent intent = new Intent(SEditorsActivity.this,SConfirmationSingleActivity.class);
                                intent.putExtra("xgdz",(Serializable) xgdz);
                                intent.putExtra("dingDan", (Serializable) commodity);
                                startActivity(intent);
                                finish();
                                break;
                            case "210":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SEditorsActivity.this, CommonData.REQUEST_EXCEOTON);
                    }
                });
    }
    //删除地址
    private void deleteShippingAddress(int getId, final LinearLayout ll_quanbu) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("id",getId+"");

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "deleteShippingAddress.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
//                        DialogUtil.start(SEditors2Activity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        Bean xiugai = gson.fromJson(result, Bean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = xiugai.getState();
                        switch (state) {
                            case "200":
                                ToastUtil.show(SEditorsActivity.this, "删除成功");
                                ll_quanbu.setVisibility(View.GONE);

                                break;
                            case "210":
                                break;
                        }
                    }

                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SEditorsActivity.this, CommonData.REQUEST_EXCEOTON);
                    }
                });

    }
}
