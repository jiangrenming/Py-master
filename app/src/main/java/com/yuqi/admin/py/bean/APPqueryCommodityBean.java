package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/20.
 */
public class APPqueryCommodityBean {

    /**
     * object : {"commodity":{"id":4,"commodityType_id":1,"commodityName":"好机友手机病健康贴眼贴专克眼睛干涩、酸痛、红血丝","commodityPrice":50,"express":0,"sales":0},"commoditypictures":[{"id":1,"commodity_id":4,"picture":"http://120.77.242.12/image/yantie.jpg","ifSign":1,"showOrder":1},{"id":6,"commodity_id":4,"picture":"http://120.77.242.12/image/yantie.jpg","ifSign":0,"showOrder":2}],"commodityparticulars":[{"id":1,"commodity_id":4,"character":"眼贴","picture":"http://120.77.242.12/image/dd1.png","showOrder":1},{"id":6,"commodity_id":4,"character":"眼贴","picture":"http://120.77.242.12/image/dd6.jpg","showOrder":2}]}
     * state : 200
     */

    private ObjectBean object;
    private String state;

    public ObjectBean getObject() {
        return object;
    }

    public void setObject(ObjectBean object) {
        this.object = object;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public static class ObjectBean {
        /**
         * commodity : {"id":4,"commodityType_id":1,"commodityName":"好机友手机病健康贴眼贴专克眼睛干涩、酸痛、红血丝","commodityPrice":50,"express":0,"sales":0}
         * commoditypictures : [{"id":1,"commodity_id":4,"picture":"http://120.77.242.12/image/yantie.jpg","ifSign":1,"showOrder":1},{"id":6,"commodity_id":4,"picture":"http://120.77.242.12/image/yantie.jpg","ifSign":0,"showOrder":2}]
         * commodityparticulars : [{"id":1,"commodity_id":4,"character":"眼贴","picture":"http://120.77.242.12/image/dd1.png","showOrder":1},{"id":6,"commodity_id":4,"character":"眼贴","picture":"http://120.77.242.12/image/dd6.jpg","showOrder":2}]
         */

        private CommodityBean commodity;
        private List<CommoditypicturesBean> commoditypictures;
        private List<CommodityparticularsBean> commodityparticulars;

        public CommodityBean getCommodity() {
            return commodity;
        }

        public void setCommodity(CommodityBean commodity) {
            this.commodity = commodity;
        }

        public List<CommoditypicturesBean> getCommoditypictures() {
            return commoditypictures;
        }

        public void setCommoditypictures(List<CommoditypicturesBean> commoditypictures) {
            this.commoditypictures = commoditypictures;
        }

        public List<CommodityparticularsBean> getCommodityparticulars() {
            return commodityparticulars;
        }

        public void setCommodityparticulars(List<CommodityparticularsBean> commodityparticulars) {
            this.commodityparticulars = commodityparticulars;
        }

        public static class CommodityBean {
            /**
             * id : 4
             * commodityType_id : 1
             * commodityName : 好机友手机病健康贴眼贴专克眼睛干涩、酸痛、红血丝
             * commodityPrice : 50.0
             * express : 0.0
             * sales : 0
             */

            private int id;
            private int commodityType_id;
            private String commodityName;
            private double commodityPrice;
            private double express;
            private int sales;

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
        }

        public static class CommoditypicturesBean {
            /**
             * id : 1
             * commodity_id : 4
             * picture : http://120.77.242.12/image/yantie.jpg
             * ifSign : 1
             * showOrder : 1
             */

            private int id;
            private int commodity_id;
            private String picture;
            private int ifSign;
            private int showOrder;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(int commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getIfSign() {
                return ifSign;
            }

            public void setIfSign(int ifSign) {
                this.ifSign = ifSign;
            }

            public int getShowOrder() {
                return showOrder;
            }

            public void setShowOrder(int showOrder) {
                this.showOrder = showOrder;
            }
        }

        public static class CommodityparticularsBean {
            /**
             * id : 1
             * commodity_id : 4
             * character : 眼贴
             * picture : http://120.77.242.12/image/dd1.png
             * showOrder : 1
             */

            private int id;
            private int commodity_id;
            private String character;
            private String picture;
            private int showOrder;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getCommodity_id() {
                return commodity_id;
            }

            public void setCommodity_id(int commodity_id) {
                this.commodity_id = commodity_id;
            }

            public String getCharacter() {
                return character;
            }

            public void setCharacter(String character) {
                this.character = character;
            }

            public String getPicture() {
                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }

            public int getShowOrder() {
                return showOrder;
            }

            public void setShowOrder(int showOrder) {
                this.showOrder = showOrder;
            }
        }
    }
}
