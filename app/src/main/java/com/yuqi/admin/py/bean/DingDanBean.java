package com.yuqi.admin.py.bean;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 */
public class DingDanBean implements Serializable {

    private List<DingdanBean> dingdan;

    public List<DingdanBean> getDingdan() {
        return dingdan;
    }

    public void setDingdan(List<DingdanBean> dingdan) {
        this.dingdan = dingdan;
    }

    public static class DingdanBean implements Serializable {
        /**
         * commodity_id : 7 商品ID
         * orderNumber : 1  订单商品数量
         * Picture :        物品图片
         * Sales : 4
         * Express :
         * CommodityPrice : 0
         * CommodityName :
         */

        private int commodity_id;
        private int orderNumber;
        private String Picture;
        private int Sales;
        private String Express;
        private double CommodityPrice;
        private String CommodityName;

        public int getCommodity_id() {
            return commodity_id;
        }

        public void setCommodity_id(int commodity_id) {
            this.commodity_id = commodity_id;
        }

        public int getOrderNumber() {
            return orderNumber;
        }

        public void setOrderNumber(int orderNumber) {
            this.orderNumber = orderNumber;
        }

        public String getPicture() {
            return Picture;
        }

        public void setPicture(String picture) {
            Picture = picture;
        }

        public int getSales() {
            return Sales;
        }

        public void setSales(int sales) {
            Sales = sales;
        }

        public String getExpress() {
            return Express;
        }

        public void setExpress(String express) {
            Express = express;
        }

        public Double getCommodityPrice() {
            return CommodityPrice;
        }

        public void setCommodityPrice(double commodityPrice) {
            CommodityPrice = commodityPrice;
        }

        public String getCommodityName() {
            return CommodityName;
        }

        public void setCommodityName(String commodityName) {
            CommodityName = commodityName;
        }


    }
}
