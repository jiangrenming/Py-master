package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/13/013.
 */

public class ColletionBean {


    /**
     * commoditycollect : [{"id":369,"commodityType_id":12,"commodityName":"大嘴猴（Paul Frank）创意卡通礼品 自弹盖随手杯550ml PFC602-550 绿色","commodityPrice":25.3,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-26 12:23:13","downTime":null,"state":1,"ifactivity":2,"picture":"http://116.62.162.19/youbao/upload/images/1516940592770a.jpg"}]
     * status : 200
     */

    private String status;
    private List<CommoditycollectBean> commoditycollect;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<CommoditycollectBean> getCommoditycollect() {
        return commoditycollect;
    }

    public void setCommoditycollect(List<CommoditycollectBean> commoditycollect) {
        this.commoditycollect = commoditycollect;
    }

    public static class CommoditycollectBean {
        /**
         * id : 369
         * commodityType_id : 12
         * commodityName : 大嘴猴（Paul Frank）创意卡通礼品 自弹盖随手杯550ml PFC602-550 绿色
         * commodityPrice : 25.3
         * express : 0.0
         * sales : 0
         * stock : 1000
         * shelfTime : 2018-01-26 12:23:13
         * downTime : null
         * state : 1
         * ifactivity : 2
         * picture : http://116.62.162.19/youbao/upload/images/1516940592770a.jpg
         */

        private int id;
        private int commodityType_id;
        private String commodityName;
        private double commodityPrice;
        private double express;
        private int sales;
        private int stock;
        private String shelfTime;
        private Object downTime;
        private int state;
        private int ifactivity;
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

        public int getStock() {
            return stock;
        }

        public void setStock(int stock) {
            this.stock = stock;
        }

        public String getShelfTime() {
            return shelfTime;
        }

        public void setShelfTime(String shelfTime) {
            this.shelfTime = shelfTime;
        }

        public Object getDownTime() {
            return downTime;
        }

        public void setDownTime(Object downTime) {
            this.downTime = downTime;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public int getIfactivity() {
            return ifactivity;
        }

        public void setIfactivity(int ifactivity) {
            this.ifactivity = ifactivity;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
