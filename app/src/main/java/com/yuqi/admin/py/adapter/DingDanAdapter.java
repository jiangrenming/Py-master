package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.lidroid.xutils.BitmapUtils;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.DingDanBean;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.utils.ImageUtil;

import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 * 订单详情
 */
public class DingDanAdapter extends BaseAdapter {
    private Context context;
    private List<getShoppingtrolleyBean.ObjectBean> data;

    public DingDanAdapter(Context context,List<getShoppingtrolleyBean.ObjectBean> data ) {
        this.context = context;
        this.data = data;
    }

    @Override
    public int getCount() {
        return data.size();
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
            convertView = View.inflate(context, R.layout.item_querendindanxinxi, null);
            hold.qrddxx_tupian =(ImageView) convertView.findViewById(R.id.qrddxx_tupian);
            hold.qrddxx_miaoshu =(TextView) convertView.findViewById(R.id.qrddxx_miaoshu);
            hold.qrddxx_jine =(TextView) convertView.findViewById(R.id.qrddxx_jine);
            hold.qrddxx_shuliang =(TextView) convertView.findViewById(R.id.qrddxx_shuliang);

            convertView.setTag(hold);
        } else {
            hold = (ViewHold) convertView.getTag();
        }
        String qrddxx_miaoshu = String.valueOf(data.get(position).getCommodityName());
        hold.qrddxx_miaoshu.setText(qrddxx_miaoshu);
        String qrddxx_jine = "￥"+String.valueOf(data.get(position).getCommodityPrice());
        hold.qrddxx_jine.setText(qrddxx_jine);
        String qrddxx_shuliang = "x"+String.valueOf(data.get(position).getCommodityNumber());
        hold.qrddxx_shuliang.setText(qrddxx_shuliang);

        ImageUtil.loadImg(hold.qrddxx_tupian,data.get(position).getPicture());
//        Log.e("图片---",data.get(position).getPicture());

//        BitmapUtils bitmapUtils = new BitmapUtils(context);
//        // 加载网络图片
//        bitmapUtils.display(hold.qrddxx_tupian,data.get(position).getPicture());

        return convertView;
    }



    class ViewHold {
        TextView qrddxx_miaoshu,qrddxx_jine,qrddxx_shuliang;
        ImageView qrddxx_tupian,qrddxx_jian,qrddxx_jia;
    }
}
