package com.yuqi.admin.py.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.dl7.recycler.adapter.BaseQuickAdapter;
import com.dl7.recycler.adapter.BaseViewHolder;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.ProjectItem;

import java.util.List;

/**
 *
 * @author jiangrenming
 * @date 2018/5/15
 */

public class ProjectAdapter extends BaseQuickAdapter<ProjectItem> {


    public ProjectAdapter(Context context, List<ProjectItem> data) {
        super(context, data);
    }

    @Override
    protected int attachLayoutRes() {
        return R.layout.item_shangpin;
    }

    @Override
    protected void convert(BaseViewHolder holder, ProjectItem item) {
        ImageView img = holder.getView(R.id.r1);
        ImageView gouwuche = holder.getView(R.id.gouwuche);
        gouwuche.setVisibility(View.GONE);
        holder.setText(R.id.r11,item.getCommodityName())
                .setText(R.id.tv_jiage,("￥"+ (item.getCommodityPrice() == null ? "0.00" : item.getCommodityPrice())));
        Glide.with(mContext).load(item.getPicture()).placeholder(R.mipmap.icon_empty).error(R.mipmap.icon_error).into(img);
    }
}
