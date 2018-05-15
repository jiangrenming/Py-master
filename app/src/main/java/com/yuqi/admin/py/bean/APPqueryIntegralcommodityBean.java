package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/12/26.
 *
 * 积分商城
 *
 */
public class APPqueryIntegralcommodityBean {


    /**
     * companyIntegral : 0
     * integralcommodities : [{"id":3,"commodityName":"APP开发","integralPrice":5000,"introduce":"暂无","pictureURL":"http://120.77.242.12/image/APPkaifa---.jpg"},{"id":4,"commodityName":"策划","integralPrice":5000,"introduce":"暂无","pictureURL":"http://120.77.242.12/image/cehua---.jpg"},{"id":7,"commodityName":"视频制作","integralPrice":5000,"introduce":"暂无","pictureURL":"http://120.77.242.12/image/shipingzhizuo---.jpg"},{"id":8,"commodityName":"网站","integralPrice":5000,"introduce":"暂无","pictureURL":"http://120.77.242.12/image/wangzhan---.jpg"},{"id":9,"commodityName":"微信开发","integralPrice":5000,"introduce":"暂无","pictureURL":"http://120.77.242.12/image/weixinkaifa---.jpg"},{"id":10,"commodityName":"展会","integralPrice":5000,"introduce":"暂未","pictureURL":"http://120.77.242.12/image/zhanhui.jpg"}]
     * companyName : 羽琪科技
     * state : 200
     */

    private int companyIntegral;
    private String companyName;
    private String state;
    private List<IntegralcommoditiesBean> integralcommodities;

    public int getCompanyIntegral() {
        return companyIntegral;
    }

    public void setCompanyIntegral(int companyIntegral) {
        this.companyIntegral = companyIntegral;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<IntegralcommoditiesBean> getIntegralcommodities() {
        return integralcommodities;
    }

    public void setIntegralcommodities(List<IntegralcommoditiesBean> integralcommodities) {
        this.integralcommodities = integralcommodities;
    }

    public static class IntegralcommoditiesBean {
        /**
         * id : 3
         * commodityName : APP开发
         * integralPrice : 5000
         * introduce : 暂无
         * pictureURL : http://120.77.242.12/image/APPkaifa---.jpg
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
}
