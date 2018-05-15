package com.yuqi.admin.py.adapter;

import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
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
import com.yuqi.admin.py.activity.ExpressActivity;
import com.yuqi.admin.py.activity.SCommodityDetailsActivity;
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
 *待发货适配器
 */
public class SpDeliveryAdapter extends BaseAdapter implements ListAdapter {

    MyPendingPaymentBean mArrayList;
    private Context context;
    private LayoutInflater inflater;

    public SpDeliveryAdapter(Context context, MyPendingPaymentBean mArrayList) {
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
            hold.wait_huangse.setVisibility(View.GONE);
            hold.wait_heise.setText("提醒发货");

            hold.finding_express = (TextView) convertView.findViewById(R.id.finding_express);
            hold.finding_express.setVisibility(View.VISIBLE);
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
            wait_jindu.setText("买家已付款");

            hold.ll_wait1.addView(v);
        }

            final List<getShoppingtrolleyBean.ObjectBean> finalDingDan = dingDan;
            hold.wait_huangse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent  intent = new Intent(context, SConfirmationSingleActivity.class);

                    intent.putExtra("dingDan", (Serializable)  dingDan);
                    context.startActivity(intent);
                }
            });
                            //取消订单
            final int finalCommodity_ids = commodity_ids1;
            final ViewHold finalHold = hold;
            hold.wait_heise.setOnClickListener(new MyClick(position));

            hold.finding_express.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
            Intent intent = new Intent(context, ExpressActivity.class);
            Bundle bundle = new Bundle();
            intent.putExtra("postid", mArrayList.getOrders().get(position).getOrder().getOrder_number()+"");
            intent.putExtras(bundle);
            context.startActivity(intent);
                }
            });
            hold.wait_dingdanhao.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View v) {
                    ClipboardManager cmb = (ClipboardManager) context.getSystemService(Context.CLIPBOARD_SERVICE);
                    cmb.setText(mArrayList.getOrders().get(position).getOrder().getOrder_number()+"".trim()); //将内容放入粘贴管理器,在别的地方长按选择"粘贴"即可
                    cmb.getText();//获取粘贴信息
                    ToastUtil.show(context, "订单号已复制");
                    return true;
                }
            });

        } else {
            hold = (ViewHold) convertView.getTag();
        }

        return convertView;

    }

    class ViewHold {
        TextView wait_dingdanhao,wait_shijian,wait_jianshu,wait_zongjinge,wait_heise,wait_huangse,finding_express;
        LinearLayout ll_wait1,ll_quanbu;
    }

    class MyClick implements View.OnClickListener {

        private int click_position;

        public MyClick(int click_position) {
            this.click_position = click_position;
        }

        @Override
        public void onClick(View view) {


            ToastUtil.show(context, "已提醒,或立即联系彭友");
        }
    }
}
