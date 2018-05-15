package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.activity.SCommodityDetailsActivity;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.ImageUtil;

/**
 * Created by Administrator on 2017/12/12.
 * 首页商品
 */
public class ShangPinAdapter extends BaseAdapter {
    private Context context;
    APPHomePageBean data ;

    public ShangPinAdapter(Context context,   APPHomePageBean data ) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.getObject().getCommodities().size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHold hold = null;

        if (convertView == null) {
            hold = new ViewHold();
            convertView = View.inflate(context, R.layout.item_shangpin, null);
            hold.sp_xiangqing =(LinearLayout) convertView.findViewById(R.id.sp_xiangqing);
            hold.zh_tupian =(ImageView) convertView.findViewById(R.id.r1);
            hold.zh_jiankangtie =(TextView) convertView.findViewById(R.id.r11);
            hold.tv_jiage =(TextView) convertView.findViewById(R.id.tv_jiage);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        hold.zh_jiankangtie.setText(data.getObject().getCommodities().get(position).getCommodityName());
        hold.tv_jiage.setText("￥"+String.valueOf(data.getObject().getCommodities().get(position).getCommodityPrice()));
        Glide.with(context).load(data.getObject().getCommodities().get(position).getPicture()).placeholder(R.mipmap.icon_stub).error(R.mipmap.icon_error).into(hold.zh_tupian);

//        BitmapUtils bitmapUtils = new BitmapUtils(context);
//        // 加载网络图片
//        String img =  TestUTF.test(data.getObject().getCommodities().get(position).getPicture());

//        Log.e("加载网络图片",img);
//        bitmapUtils.display(hold.zh_tupian,img);

        hold.sp_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int user_id = CommonData.user_id;
                if(user_id ==0){
                    return;
                }
                Intent intent = new Intent(context, SCommodityDetailsActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("Commodity_id",data.getObject().getCommodities().get(position).getId()+"");
                intent.putExtra("Picture",data.getObject().getCommodities().get(position).getPicture());
                intent.putExtra("user_id",user_id);
//                intent.putExtra("",data.getObject().getCommodities().get(position).getPicture());
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }



    class ViewHold {
        LinearLayout sp_xiangqing;
        TextView zh_jiankangtie,tv_jiage;
        ImageView zh_tupian;
    }
}
