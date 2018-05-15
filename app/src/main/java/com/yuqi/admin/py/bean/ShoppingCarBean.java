package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by zhanghongwei on 2017/12/22.
 * 购物车实体类
 */

public class ShoppingCarBean {



    private List<ShoppingcarData> goodsLis;

    public List<ShoppingcarData> getGoodsLis() {
        return goodsLis;
    }

    public void setGoodsLis(List<ShoppingcarData> goodsLis) {
        this.goodsLis = goodsLis;
    }


    public static class ShoppingcarData{
        private String str;

        public String getStr() {
            return str;
        }

        public void setStr(String str) {
            this.str = str;
        }

        private String picurl;
        private int userID;

        public String getPicurl() {
            return picurl;
        }

        public void setPicurl(String picurl) {
            this.picurl = picurl;
        }

        private int GoodsID;
        private int GoodsNum;

        public int getUserID() {
            return userID;
        }

        public void setUserID(int userID) {
            this.userID = userID;
        }

        public int getGoodsID() {
            return GoodsID;
        }

        public void setGoodsID(int goodsID) {
            GoodsID = goodsID;
        }

        public int getGoodsNum() {
            return GoodsNum;
        }

        public void setGoodsNum(int goodsNum) {
            GoodsNum = goodsNum;
        }
    }
}
