package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/8.
 * 待付款实体类
 */
public class MyPendingPaymentBean {

    /**
     * msg : 成功！
     * orders : [{"order":{"order_id":696,"user_id":84,"address_id":227,"payWay":"余额","orderPrice":6799,"orderStatus":1,"payAccount":"13412345678","createTime":"2018-01-13 14:59:40","order_number":"201801131401680092273a2"},"commodities":[{"id":167,"commodityType_id":15,"commodityName":"联想（Lenovo）天逸510 Pro商用台式电脑整机（i5-7400 8G 1T GT730 2G独显 三年上门 Win10 Office）23英寸","commodityPrice":5099,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-10 09:49:33","downTime":null,"state":1,"ifactivity":1},{"id":158,"commodityType_id":15,"commodityName":"负离子能量眼镜","commodityPrice":800,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:53:15","downTime":null,"state":1,"ifactivity":1},{"id":156,"commodityType_id":15,"commodityName":"磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油","commodityPrice":100,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:51:24","downTime":null,"state":1,"ifactivity":1}],"counts":[1,2,1],"pictures":["http://139.224.238.234:8021/image/1515548972947联想-1.jpg","http://139.224.238.234:8021/image/1515379992520负离子眼镜.jpg","http://139.224.238.234:8021/image/1515379882723磁悬浮润滑油.jpg"]},{"order":{"order_id":694,"user_id":84,"address_id":227,"payWay":"余额","orderPrice":499,"orderStatus":1,"payAccount":"13412345678","createTime":"2018-01-13 14:59:11","order_number":"201801131401309d002969b"},"commodities":[{"id":157,"commodityType_id":10,"commodityName":"车载空气净化器","commodityPrice":499,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:52:11","downTime":null,"state":1,"ifactivity":1}],"counts":[1],"pictures":["http://139.224.238.234:8021/image/1515379927301车载净化器.jpg"]},{"order":{"order_id":645,"user_id":84,"address_id":0,"payWay":"微信","orderPrice":200,"orderStatus":1,"payAccount":"13412345678","createTime":"2018-01-09 20:09:32","order_number":"2018010920093200033"},"commodities":[{"id":156,"commodityType_id":15,"commodityName":"磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油","commodityPrice":100,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:51:24","downTime":null,"state":1,"ifactivity":1}],"counts":[2],"pictures":["http://139.224.238.234:8021/image/1515379882723磁悬浮润滑油.jpg"]},{"order":{"order_id":508,"user_id":84,"address_id":227,"payWay":"支付宝","orderPrice":300,"orderStatus":1,"payAccount":"13412345678","createTime":"2018-01-08 19:59:21","order_number":"201801081901173a28f00f1"},"commodities":[{"id":152,"commodityType_id":16,"commodityName":"健康贴","commodityPrice":150,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 11:40:15","downTime":"2018-01-08 11:40:12","state":1,"ifactivity":1}],"counts":[2],"pictures":["http://139.224.238.234:8021/image/1515379563859健康贴.jpg"]}]
     * state : 200
     */

    private String msg;
    private String state;
    private List<OrdersBean> orders;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public List<OrdersBean> getOrders() {
        return orders;
    }

    public void setOrders(List<OrdersBean> orders) {
        this.orders = orders;
    }

    public static class OrdersBean {
        /**
         * order : {"order_id":696,"user_id":84,"address_id":227,"payWay":"余额","orderPrice":6799,"orderStatus":1,"payAccount":"13412345678","createTime":"2018-01-13 14:59:40","order_number":"201801131401680092273a2"}
         * commodities : [{"id":167,"commodityType_id":15,"commodityName":"联想（Lenovo）天逸510 Pro商用台式电脑整机（i5-7400 8G 1T GT730 2G独显 三年上门 Win10 Office）23英寸","commodityPrice":5099,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-10 09:49:33","downTime":null,"state":1,"ifactivity":1},{"id":158,"commodityType_id":15,"commodityName":"负离子能量眼镜","commodityPrice":800,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:53:15","downTime":null,"state":1,"ifactivity":1},{"id":156,"commodityType_id":15,"commodityName":"磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油","commodityPrice":100,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-01-08 10:51:24","downTime":null,"state":1,"ifactivity":1}]
         * counts : [1,2,1]
         * pictures : ["http://139.224.238.234:8021/image/1515548972947联想-1.jpg","http://139.224.238.234:8021/image/1515379992520负离子眼镜.jpg","http://139.224.238.234:8021/image/1515379882723磁悬浮润滑油.jpg"]
         */

        private OrderBean order;
        private List<CommoditiesBean> commodities;
        private List<Integer> counts;
        private List<String> pictures;

        public OrderBean getOrder() {
            return order;
        }

        public void setOrder(OrderBean order) {
            this.order = order;
        }

        public List<CommoditiesBean> getCommodities() {
            return commodities;
        }

        public void setCommodities(List<CommoditiesBean> commodities) {
            this.commodities = commodities;
        }

        public List<Integer> getCounts() {
            return counts;
        }

        public void setCounts(List<Integer> counts) {
            this.counts = counts;
        }

        public List<String> getPictures() {
            return pictures;
        }

        public void setPictures(List<String> pictures) {
            this.pictures = pictures;
        }

        public static class OrderBean {
            /**
             * order_id : 696
             * user_id : 84
             * address_id : 227
             * payWay : 余额
             * orderPrice : 6799.0
             * orderStatus : 1
             * payAccount : 13412345678
             * createTime : 2018-01-13 14:59:40
             * order_number : 201801131401680092273a2
             */

            private int order_id;
            private int user_id;
            private int address_id;
            private String payWay;
            private double orderPrice;
            private int orderStatus;
            private String payAccount;
            private String createTime;
            private String order_number;

            public int getOrder_id() {
                return order_id;
            }

            public void setOrder_id(int order_id) {
                this.order_id = order_id;
            }

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public int getAddress_id() {
                return address_id;
            }

            public void setAddress_id(int address_id) {
                this.address_id = address_id;
            }

            public String getPayWay() {
                return payWay;
            }

            public void setPayWay(String payWay) {
                this.payWay = payWay;
            }

            public double getOrderPrice() {
                return orderPrice;
            }

            public void setOrderPrice(double orderPrice) {
                this.orderPrice = orderPrice;
            }

            public int getOrderStatus() {
                return orderStatus;
            }

            public void setOrderStatus(int orderStatus) {
                this.orderStatus = orderStatus;
            }

            public String getPayAccount() {
                return payAccount;
            }

            public void setPayAccount(String payAccount) {
                this.payAccount = payAccount;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }

            public String getOrder_number() {
                return order_number;
            }

            public void setOrder_number(String order_number) {
                this.order_number = order_number;
            }
        }

        public static class CommoditiesBean {
            /**
             * id : 167
             * commodityType_id : 15
             * commodityName : 联想（Lenovo）天逸510 Pro商用台式电脑整机（i5-7400 8G 1T GT730 2G独显 三年上门 Win10 Office）23英寸
             * commodityPrice : 5099.0
             * express : 0.0
             * sales : 0
             * stock : 1000
             * shelfTime : 2018-01-10 09:49:33
             * downTime : null
             * state : 1
             * ifactivity : 1
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
        }
    }
}
