package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/11.
 * 历史搜索记录
 *
 */
public class APPqueryHistorySeekByIDShopBean {


    /**
     * object : [{"id":212,"commodityType_id":15,"commodityName":"晨光AR智能地球仪14.2cm ASD99823","commodityPrice":29.18,"express":0,"sales":0,"picture":"http://139.224.238.234:8021/image/15155565490821515490964(1).jpg"}]
     * state : 200
     */

    private String state;
    private List<ObjectBean> object;

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<ObjectBean> getObject() {
        return object;
    }

    public void setObject(List<ObjectBean> object) {
        this.object = object;
    }

    public static class ObjectBean {
        /**
         * id : 212
         * commodityType_id : 15
         * commodityName : 晨光AR智能地球仪14.2cm ASD99823
         * commodityPrice : 29.18
         * express : 0.0
         * sales : 0
         * picture : http://139.224.238.234:8021/image/15155565490821515490964(1).jpg
         */

        private int id;
        private int commodityType_id;
        private String commodityName;
        private double commodityPrice;
        private double express;
        private int sales;
        private String picture;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getCommodityType_id() {
            return commodityType_id;
        }

        public void setCommodityType_id(int commodityType_id) {
            this.commodityType_id = commodityType_id;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public double getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(double commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public double getExpress() {
            return express;
        }

        public void setExpress(double express) {
            this.express = express;
        }

        public int getSales() {
            return sales;
        }

        public void setSales(int sales) {
            this.sales = sales;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
