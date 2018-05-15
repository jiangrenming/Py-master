package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 * 立即支付 实体类
 */
public class APPqueryIntegralcommodityByIDBean {

    /**
     * object : {"integralcommoditypictures":[{"id":1,"integralCommodity_id":1,"picturePath":"http://120.77.242.12/image/yantie.jpg","ifSign":1,"showOrder":1},{"id":2,"integralCommodity_id":1,"picturePath":"http://120.77.242.12/image/yantie.jpg","ifSign":0,"showOrder":2}],"integralcommodities":{"id":1,"commodityName":"基础网站建设","integralPrice":3000,"introduce":"专业为企业建设开发基础网站--例如官网、门户等","pictureURL":"http://120.77.242.12/image/yantie.jpg"}}
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
         * integralcommoditypictures : [{"id":1,"integralCommodity_id":1,"picturePath":"http://120.77.242.12/image/yantie.jpg","ifSign":1,"showOrder":1},{"id":2,"integralCommodity_id":1,"picturePath":"http://120.77.242.12/image/yantie.jpg","ifSign":0,"showOrder":2}]
         * integralcommodities : {"id":1,"commodityName":"基础网站建设","integralPrice":3000,"introduce":"专业为企业建设开发基础网站--例如官网、门户等","pictureURL":"http://120.77.242.12/image/yantie.jpg"}
         */

        private IntegralcommoditiesBean integralcommodities;
        private List<IntegralcommoditypicturesBean> integralcommoditypictures;

        public IntegralcommoditiesBean getIntegralcommodities() {
            return integralcommodities;
        }

        public void setIntegralcommodities(IntegralcommoditiesBean integralcommodities) {
            this.integralcommodities = integralcommodities;
        }

        public List<IntegralcommoditypicturesBean> getIntegralcommoditypictures() {
            return integralcommoditypictures;
        }

        public void setIntegralcommoditypictures(List<IntegralcommoditypicturesBean> integralcommoditypictures) {
            this.integralcommoditypictures = integralcommoditypictures;
        }

        public static class IntegralcommoditiesBean {
            /**
             * id : 1
             * commodityName : 基础网站建设
             * integralPrice : 3000
             * introduce : 专业为企业建设开发基础网站--例如官网、门户等
             * pictureURL : http://120.77.242.12/image/yantie.jpg
             */

            private int id;
            private String commodityName;
            private int integralPrice;
            private String introduce;
            private String pictureURL;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getCommodityName() {
                return commodityName;
            }

            public void setCommodityName(String commodityName) {
                this.commodityName = commodityName;
            }

            public int getIntegralPrice() {
                return integralPrice;
            }

            public void setIntegralPrice(int integralPrice) {
                this.integralPrice = integralPrice;
            }

            public String getIntroduce() {
                return introduce;
            }

            public void setIntroduce(String introduce) {
                this.introduce = introduce;
            }

            public String getPictureURL() {
                return pictureURL;
            }

            public void setPictureURL(String pictureURL) {
                this.pictureURL = pictureURL;
            }
        }

        public static class IntegralcommoditypicturesBean {
            /**
             * id : 1
             * integralCommodity_id : 1
             * picturePath : http://120.77.242.12/image/yantie.jpg
             * ifSign : 1
             * showOrder : 1
             */

            private int id;
            private int integralCommodity_id;
            private String picturePath;
            private int ifSign;
            private int showOrder;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public int getIntegralCommodity_id() {
                return integralCommodity_id;
            }

            public void setIntegralCommodity_id(int integralCommodity_id) {
                this.integralCommodity_id = integralCommodity_id;
            }

            public String getPicturePath() {
                return picturePath;
            }

            public void setPicturePath(String picturePath) {
                this.picturePath = picturePath;
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
    }
}
