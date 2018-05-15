package com.yuqi.admin.py.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.interfaces.Deletesth;
import com.yuqi.admin.py.interfaces.IselectInter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghongwei on 2017/12/22.
 */

public class ShoppCarViewBItem extends RelativeLayout implements Deletesth {
    public List<ShoppCarViewSItem> viewSItems = new ArrayList<>();
    private ImageView iv_select;
    private TextView tv_type,tv_success;
    private LinearLayout shop_container ,iv_leixing;
    private List<getShoppingtrolleyBean.ObjectBean> bean;
    private Context mContext;

    private IselectInter iselectInter;
    private ShoppCarViewBItem shoppCarViewBItem;

    private boolean isSuccess = false;
    private String SUCCESS = "完成";
    private String EDIT = "编辑";

    public ShoppCarViewBItem(Context context, List<getShoppingtrolleyBean.ObjectBean> beans, IselectInter iselectInter) {
        this(context,null,beans,iselectInter);
    }

    public ShoppCarViewBItem(Context context, AttributeSet attrs,List<getShoppingtrolleyBean.ObjectBean> beans, IselectInter iselectInter) {
        this(context, attrs,0,beans,iselectInter);
    }

    public ShoppCarViewBItem(Context context, AttributeSet attrs, int defStyleAttr,List<getShoppingtrolleyBean.ObjectBean> beans, IselectInter iselectInter) {
        super(context, attrs, defStyleAttr);
        mContext = context;
        LayoutInflater.from(context).inflate(R.layout.item_gwc_bianji,this);
        this.iselectInter = iselectInter;
        shoppCarViewBItem = this;
        this.bean = beans;
        initView();
        CreateView();
    }
    private void initView(){
        iv_select = (ImageView) shoppCarViewBItem.findViewById(R.id.iv_select);
        tv_success = (TextView) shoppCarViewBItem.findViewById(R.id.tv_success);//编辑
        tv_type = (TextView) shoppCarViewBItem.findViewById(R.id.tv_type);//类型
        shop_container = (LinearLayout) shoppCarViewBItem.findViewById(R.id.shop_container);
        iv_leixing = (LinearLayout) shoppCarViewBItem.findViewById(R.id.iv_leixing);

        for (int i = 0; i<bean.size(); i++){
            if (bean != null){
                iv_leixing.setVisibility(View.VISIBLE);
                tv_type.setText(bean.get(i).getTypeName());
                Log.e("类型----",bean.get(i).getTypeName());
                tv_success.setText("编辑");
            }else {
                iv_leixing.setVisibility(View.GONE);
                return;
            }
        }

        iv_select.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(iv_select.getTag()==null){
                    iv_select.setImageResource(R.mipmap.xuanzhong);
                    iv_select.setTag(R.mipmap.xuanzhong);
                    setChildSelect(true);
                }else{
                    if((int)iv_select.getTag() == R.mipmap.xuanzhong){
                        iv_select.setImageResource(R.mipmap.meixuanzhong);
                        iv_select.setTag(R.mipmap.meixuanzhong);
                        setChildSelect(false);
                    }else{
                        iv_select.setImageResource(R.mipmap.xuanzhong);
                        iv_select.setTag(R.mipmap.xuanzhong);
                        setChildSelect(true);
                    }
                }


            }
        });

        tv_success.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(tv_success.getText().equals(EDIT)){
                    tv_success.setText(SUCCESS);
                    setEditMode(true);
                }else{
                    tv_success.setText(EDIT);
                    setEditMode(false);
                }
            }
        });
    }

    private void setChildSelect(boolean select){
        for(ShoppCarViewSItem item : viewSItems){
            item.setSelect(select);
        }
    }

    private void setEditMode(boolean hiden){
        for(ShoppCarViewSItem viewSItem : viewSItems){
            viewSItem.setVisi(hiden);
        }
    }

    private void CreateView(){

        for(getShoppingtrolleyBean.ObjectBean data : bean){
            if(data != null){
                //这里根据数据加载
                ShoppCarViewSItem shoppCarViewSItem = new ShoppCarViewSItem(mContext,data);
                shoppCarViewSItem.setCallback(shoppCarViewBItem);
                shoppCarViewSItem.setSelectI(iselectInter);
                viewSItems.add(shoppCarViewSItem);

                shop_container.addView(shoppCarViewSItem);
            }else {
                LinearLayout  none = (LinearLayout) findViewById(R.id.ll_none);
                none.setVisibility(View.VISIBLE);
            }
        }
    }


    @Override
    public void deletGoodsId(int id, ShoppCarViewSItem viewSItem) {
        /**
         * 网络删除
         * 自己写好接口
         */
        shop_container.removeView(viewSItem);
        viewSItems.remove(viewSItem);

    }
}
