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
import com.yuqi.admin.py.bean.APPCommodityBean;
import com.yuqi.admin.py.utils.ImageUtil;


/**
 * Created by Administrator on 2017/12/12.
 * 商品综合
 */
public class ShangPinzhAdapter extends BaseAdapter {
    private Context context;
    APPCommodityBean data ;

    public ShangPinzhAdapter(Context context, APPCommodityBean data ) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.getObject().size();
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

        hold.zh_jiankangtie.setText(data.getObject().get(position).getCommodityName());
        hold.tv_jiage.setText("￥"+String.valueOf(data.getObject().get(position).getCommodityPrice()));

        Glide.with(context).load(data.getObject().get(position).getPicture()).placeholder(R.mipmap.icon_stub).error(R.mipmap.icon_error).into(hold.zh_tupian);

//        ImageUtil.loadImg(hold.zh_tupian,data.getObject().get(position).getPicture());
//        BitmapUtils bitmapUtils = new BitmapUtils(context);
//        // 加载网络图片
//        String img =  TestUTF.test(data.getObject().get(position).getPicture());
//        bitmapUtils.display(hold.zh_tupian,img);

        hold.sp_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SCommodityDetailsActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("Commodity_id",data.getObject().get(position).getId()+"");
                intent.putExtra("Picture",data.getObject().get(position).getPicture());
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
