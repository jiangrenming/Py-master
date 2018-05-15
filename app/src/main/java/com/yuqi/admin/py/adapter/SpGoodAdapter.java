package com.yuqi.admin.py.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.activity.SConfirmationSingleActivity;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.bean.MyPendingPaymentBean;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/12/23.
 *
 *待收货 适配器
 */
public class SpGoodAdapter extends BaseAdapter implements ListAdapter {

    MyPendingPaymentBean mArrayList;
    private Context context;
    private LayoutInflater inflater;

    public SpGoodAdapter(Context context, MyPendingPaymentBean mArrayList) {
        super();
        this.mArrayList = mArrayList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mArrayList.getOrders().size();
    }

    @Override
    public Object getItem(int position) {
        return mArrayList.getOrders().get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHold hold = null;
        if (convertView == null) {
            hold = new ViewHold();
            convertView = View.inflate(context, R.layout.item_wait, null);
            hold.wait_dingdanhao = (TextView) convertView.findViewById(R.id.wait_dingdanhao);
            hold.wait_shijian = (TextView) convertView.findViewById(R.id.wait_shijian);
            hold.wait_jianshu = (TextView) convertView.findViewById(R.id.wait_jianshu);
            hold.wait_zongjinge = (TextView) convertView.findViewById(R.id.wait_zongjinge);
            hold.ll_wait1 = (LinearLayout)convertView.findViewById(R.id.ll_wait1);

            hold.wait_huangse = (TextView) convertView.findViewById(R.id.wait_huangse);
            hold.wait_heise = (TextView) convertView.findViewById(R.id.wait_heise);

            hold.wait_huangse = (TextView) convertView.findViewById(R.id.wait_huangse);
            hold.wait_heise = (TextView) convertView.findViewById(R.id.wait_heise);
            hold.wait_huangse.setText("确认收货");
            hold.wait_heise.setVisibility(View.GONE);

            convertView.setTag(hold);




        hold.wait_dingdanhao.setText(mArrayList.getOrders().get(position).getOrder().getOrder_number());
        hold.wait_shijian.setText(mArrayList.getOrders().get(position).getOrder().getCreateTime());
        hold.wait_jianshu.setText(mArrayList.getOrders().get(position).getPictures().size()+"");
        hold.wait_zongjinge.setText(Double.toString(mArrayList.getOrders().get(position).getOrder().getOrderPrice()));
        int commodity_ids1 = 0;

        int counts1 = 0;
        String pictures1="";
        Double price1 = 0.0;
        String namename1 = "";
        final List<getShoppingtrolleyBean.ObjectBean> dingDan = new ArrayList<>();

        for (int i =0; i<mArrayList.getOrders().get(position).getPictures().size(); i++){
            View v = View.inflate(context, R.layout.item_wait1, null);
            LinearLayout ll_quanbu1 = (LinearLayout) v.findViewById(R.id.ll_quanbu1);
            ImageView wait_tupian = (ImageView) v.findViewById(R.id.wait_tupian);
            TextView wait_miaoshu = (TextView) v.findViewById(R.id.wait_miaoshu);
            TextView wait_jiage = (TextView) v.findViewById(R.id.wait_jiage);
            TextView wait_shuliang = (TextView) v.findViewById(R.id.wait_shuliang);
            TextView wait_jindu = (TextView) v.findViewById(R.id.wait_jindu);

            commodity_ids1 =mArrayList.getOrders().get(position).getCommodities().get(i).getId();
            counts1 = mArrayList.getOrders().get(position).getCounts().get(i);
            pictures1 =  mArrayList.getOrders().get(position).getPictures().get(i);
            price1 =mArrayList.getOrders().get(position).getCommodities().get(i).getCommodityPrice();
            namename1 = mArrayList.getOrders().get(position).getCommodities().get(i).getCommodityName();

            getShoppingtrolleyBean.ObjectBean info = new getShoppingtrolleyBean.ObjectBean();
            info.setCommodity_id(commodity_ids1);
            info.setCommodityNumber(counts1);
            info.setPicture(pictures1);
            info.setCommodityPrice(price1);
            info.setCommodityName(namename1);
            dingDan.add(info);


            //商品图片
            Glide.with(context)
                    .load(mArrayList.getOrders().get(position).getPictures().get(i))
                    .placeholder(R.mipmap.icon_empty)
                    .error(R.mipmap.icon_error)
                    .into(wait_tupian);
            //商品描述
            wait_miaoshu.setText(mArrayList.getOrders().get(position).getCommodities().get(i).getCommodityName());
            //商品价格commodityPrice
            wait_jiage.setText("￥"+mArrayList.getOrders().get(position).getCommodities().get(i).getCommodityPrice());
            //商品个数
            wait_shuliang.setText("x"+mArrayList.getOrders().get(position).getCounts().get(i));
            wait_jindu.setText("卖家已发货");

            hold.ll_wait1.addView(v);
        }

            final List<getShoppingtrolleyBean.ObjectBean> finalDingDan = dingDan;
            //确认收货
            hold.wait_huangse.setOnClickListener(new MyClick(position));


        } else {
            hold = (ViewHold) convertView.getTag();
        }
        return convertView;
    }

    class ViewHold {
        TextView wait_dingdanhao,wait_shijian,wait_jianshu,wait_zongjinge,wait_heise,wait_huangse;
        LinearLayout ll_wait1,ll_quanbu;
    }

    class MyClick implements View.OnClickListener {

        private int click_position;

        public MyClick(int click_position) {
            this.click_position = click_position;
        }

        @Override
        public void onClick(View view) {
            final int order_id1 =   mArrayList.getOrders().get(click_position).getOrder().getOrder_id();
            AlertDialog dialog =  new  AlertDialog.Builder(context)
                    .setTitle("确认操作" )
                    .setMessage("确认已收货!" )
                    .setPositiveButton("是", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            RequestParams params1 = new RequestParams();
                            params1.addQueryStringParameter("order_id",order_id1+"");
                            params1.addQueryStringParameter("orderStatus",3+"");

                            HttpUtils http = new HttpUtils();
                            http.configCurrentHttpCacheExpiry(1000 * 10);
                            http.send(HttpRequest.HttpMethod.POST,
                                    CommonData.URL + "deleteOrderByID.action",
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
                                                    ToastUtil.show(context, "操作成功");
                                                    mArrayList.getOrders().remove(click_position);
                                                    SpGoodAdapter.this.notifyDataSetChanged();

                                                    break;
                                                case "210":
                                                    break;
                                            }
                                        }

                                        @Override
                                        public void onFailure(HttpException error, String msg) {
                                            DialogUtil.finish();
                                            ToastUtil.show(context, CommonData.REQUEST_EXCEOTON);
                                        }
                                    });
                            dialog.dismiss();
                        }
                    })
                    .setNegativeButton("否", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    })
                    .create();
            dialog.show();
            notifyDataSetChanged();
        }
    }
}
