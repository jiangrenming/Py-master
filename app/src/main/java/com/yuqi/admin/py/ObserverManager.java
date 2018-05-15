package com.yuqi.admin.py;

import com.yuqi.admin.py.bean.ShoppingCarBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.interfaces.ObserverListener;
import com.yuqi.admin.py.interfaces.SubjectListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhanghongwei on 2017/12/22.
 */

public class ObserverManager implements SubjectListener {

    private static ObserverManager instance;
    private ShoppingCarBean bean;
    private ObserverListener listener;

    private List<ObserverListener> list = new ArrayList<>();

    public static ObserverManager getInstance(){
        if(null == instance){
            synchronized (ObserverManager.class){
                if(null == instance){
                    instance = new ObserverManager();
                }
            }
        }
        return instance;
    }

    public void setEntity(ShoppingCarBean bean,ObserverListener listener){
        this.bean = bean;
        this.listener = listener;
    }

    public ShoppingCarBean getEntity(){
        return bean;
    }

    // 添加购物车 商品id  数量
    @Override
    public void addGoods(int goodsID, int goodsnum) {
        List<ShoppingCarBean.ShoppingcarData> lists = bean.getGoodsLis();
        ShoppingCarBean.ShoppingcarData data = new ShoppingCarBean.ShoppingcarData();
        data.setGoodsID(goodsID);
        data.setGoodsNum(goodsnum);
        data.setUserID(CommonData.user_id);
        lists.add(data);
        listener.changeGoods();
        senedMessage();
    }
    // 编辑购物车   数量
    @Override
    public void changeGoodsNum(int goodsID, int goodsnum) {
        List<ShoppingCarBean.ShoppingcarData> lists = bean.getGoodsLis();
        for(ShoppingCarBean.ShoppingcarData data1 : lists){
            if(data1.getGoodsID() == goodsID){
                data1.setGoodsNum(goodsnum);
            }
        }
        listener.changeGoods();
        senedMessage();
    }

    @Override
    public void removeGoods(int goodsID) {
        List<ShoppingCarBean.ShoppingcarData> lists = bean.getGoodsLis();
        for(ShoppingCarBean.ShoppingcarData data1 : lists){
            if(data1.getGoodsID() == goodsID){
                list.remove(data1);
            }
        }
        listener.changeGoods();
        senedMessage();
    }

    private void senedMessage(){
        /**
         * 新建一个线程来进行提交购物车数量的变化
         */


    }


}
