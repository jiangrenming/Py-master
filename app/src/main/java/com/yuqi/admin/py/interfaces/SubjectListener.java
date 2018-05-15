package com.yuqi.admin.py.interfaces;

/**
 * Created by zhanghongwei on 2017/12/22.
 */

public interface SubjectListener {
    void addGoods(int goodsID,int goodsnum);//添加
    void changeGoodsNum(int goodsID, int goodsnum);//数量变化
    void removeGoods(int goodsID);//删除
}
