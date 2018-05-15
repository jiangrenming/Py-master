package com.yuqi.admin.py.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 *  首页根据不同的状态商品数据  实体类
 */
public class APPCommodityBean {


    /**
     * object : [{"id":4,"commodityType_id":1,"commodityName":"好机友手机病健康贴眼贴专克眼睛干涩、酸痛、红血丝","commodityPrice":50,"express":0,"sales":0,"picture":"http://120.77.242.12/image/yantie.jpg"},{"id":5,"commodityType_id":2,"commodityName":"中科特膳：［1盒装］中科特膳美膳美姿纤美营养素 135g／盒","commodityPrice":198,"express":0,"sales":0,"picture":"http://120.77.242.12/image/zhongketeshang-1he.jpg"},{"id":6,"commodityType_id":2,"commodityName":"中科特膳：［5盒装］中科特膳美膳美姿纤美营养素 135g／盒","commodityPrice":780,"express":0,"sales":0,"picture":"http://120.77.242.12/image/zhongketeshang-5he.jpg"},{"id":7,"commodityType_id":1,"commodityName":"太湖清水大闸蟹：现货正宗太湖清水大闸蟹 鲜活","commodityPrice":998,"express":0,"sales":0,"picture":"http://120.77.242.12/image/taihuqinshuidazhaxie.jpg"},{"id":8,"commodityType_id":1,"commodityName":"磁悬浮润滑油：磁悬浮润滑油SN/GF-50W 0W-40汽车机油","commodityPrice":100,"express":0,"sales":0,"picture":"http://120.77.242.12/image/cixuanfurunhuayou.jpg"}]
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
         * id : 4
         * commodityType_id : 1
         * commodityName : 好机友手机病健康贴眼贴专克眼睛干涩、酸痛、红血丝
         * commodityPrice : 50.0
         * express : 0.0
         * sales : 0
         * picture : http://120.77.242.12/image/yantie.jpg
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
//            try {
//                String strGBK = URLEncoder.encode(picture, "GBK");
//                System.out.println(strGBK);
//                String strUTF8 = URLDecoder.decode(picture, "UTF-8");
//                System.out.println(strUTF8);
//            } catch (UnsupportedEncodingException e) {
//                e.printStackTrace();
//            }
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
