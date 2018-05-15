package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.activity.SCommodityDetailsActivity;
import com.yuqi.admin.py.bean.ColletionBean;
import com.yuqi.admin.py.bean.VideoBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.ToastUtil;

/**
 * Created by Administrator on 2018/4/13/013.
 */

public class ColletionListAdapter extends BaseAdapter {

    private Context context;
    private ColletionBean colletionBean;

    public ColletionListAdapter(Context cont, ColletionBean collectionBean ) {
        super();
        this.context = cont;
        this.colletionBean = collectionBean;
    }

    @Override
    public int getCount() {
        Log.i("getCount",colletionBean.getCommoditycollect().size()+"");
        return colletionBean.getCommoditycollect().size();
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
    ViewHolder viewHolder;

        if (convertView == null) {
            viewHolder = new ViewHolder();
            convertView = View.inflate(context, R.layout.item_shangpin, null);
            viewHolder.sp_xiangqing =(LinearLayout) convertView.findViewById(R.id.sp_xiangqing);
            viewHolder.zh_tupian =(ImageView) convertView.findViewById(R.id.r1);
            viewHolder.zh_jiankangtie =(TextView) convertView.findViewById(R.id.r11);
            viewHolder.tv_jiage =(TextView) convertView.findViewById(R.id.tv_jiage);
            convertView.setTag(viewHolder);

        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.zh_jiankangtie.setText(colletionBean.getCommoditycollect().get(position).getCommodityName());
        Log.i("getCount",colletionBean.getCommoditycollect().get(position).getCommodityName());
        viewHolder.tv_jiage.setText("￥"+String.valueOf(colletionBean.getCommoditycollect().get(position).getCommodityPrice()));
        Glide.with(context).load(colletionBean.getCommoditycollect().get(position).getPicture()).placeholder(R.mipmap.icon_stub).error(R.mipmap.icon_error).into(viewHolder.zh_tupian);

        viewHolder.sp_xiangqing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, SCommodityDetailsActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("Commodity_id", colletionBean.getCommoditycollect().get(position).getId()+"");
                intent.putExtra("user_id", CommonData.user_id+"");
                intent.putExtras(bundle);
                context.startActivity(intent);
            }
        });
        return convertView;
    }

    class ViewHolder {
        LinearLayout sp_xiangqing;
        TextView zh_jiankangtie,tv_jiage;
        ImageView zh_tupian;
    }
}
