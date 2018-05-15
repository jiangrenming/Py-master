package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.activity.SRedeemNowActivity;
import com.yuqi.admin.py.bean.APPqueryIntegralcommodityBean;
import com.yuqi.admin.py.view.ResizableImageView;

/**
 * Created by Administrator on 2017/12/12.
 * 积分商品
 */
public class JIFenAdapter extends BaseAdapter {
    private Context context;
    APPqueryIntegralcommodityBean data ;

    public JIFenAdapter(Context context, APPqueryIntegralcommodityBean data ) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.getIntegralcommodities().size();
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
            convertView = View.inflate(context, R.layout.item_jifenshangcheng, null);
            hold.jfsc_jifen =(TextView) convertView.findViewById(R.id.jfsc_jifen);
            hold.jfsc_tupian =(ResizableImageView) convertView.findViewById(R.id.jfsc_tupian);
            hold.jfsc_leixing =(TextView) convertView.findViewById(R.id.jfsc_leixing);
            hold.jfsc_duihuan =(LinearLayout) convertView.findViewById(R.id.jfsc_duihuan);
            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }

        String  jifen = data.getIntegralcommodities().get(position).getIntegralPrice()+"";
        hold.jfsc_jifen.setText(jifen);
        hold.jfsc_leixing.setText(data.getIntegralcommodities().get(position).getCommodityName());

        BitmapUtils bitmapUtils = new BitmapUtils(context);
        // 加载网络图片
        bitmapUtils.display(hold.jfsc_tupian,data.getIntegralcommodities().get(position).getPictureURL());

        hold.jfsc_duihuan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SRedeemNowActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("id",data.getIntegralcommodities().get(position).getId()+"");
                intent.putExtra("CommodityName",data.getIntegralcommodities().get(position).getCommodityName()+"");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }



    class ViewHold {
        TextView jfsc_leixing,jfsc_jifen;
        LinearLayout jfsc_duihuan;
        ResizableImageView jfsc_tupian;
    }
}
