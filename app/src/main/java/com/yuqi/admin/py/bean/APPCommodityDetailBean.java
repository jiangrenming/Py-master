package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/4/9/009.
 */

public class APPCommodityDetailBean {
    /**
     * data : {"msg":"http://116.62.162.19/youbao/upload/videos/bmh.mp4","commodity":{"id":415,"commodityType_id":14,"commodityName":"美式焦糖--甜蜜 中罐70g ","commodityPrice":9.4,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-03-08 16:10:36","downTime":null,"state":1,"ifactivity":2,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg"},"commoditypictures":[{"id":1538,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356962.jpg","ifSign":0,"showOrder":2},{"id":1539,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357073.jpg","ifSign":0,"showOrder":3},{"id":1540,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357244.jpg","ifSign":0,"showOrder":4},{"id":1541,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357365.jpg","ifSign":0,"showOrder":5},{"id":1542,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357446.jpg","ifSign":0,"showOrder":6},{"id":1543,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357607.jpg","ifSign":0,"showOrder":7}],"commodityparticulars":[{"id":3155,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg","showOrder":1},{"id":3156,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636070a.jpg","showOrder":2},{"id":3157,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636091b.jpg","showOrder":3},{"id":3158,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636108c.jpg","showOrder":4},{"id":3159,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636124d.jpg","showOrder":5}]}
     * ifSign : 0
     * status : 200
     */

    private DataBean data;
    private String ifSign;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getIfSign() {
        return ifSign;
    }

    public void setIfSign(String ifSign) {
        this.ifSign = ifSign;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        /**
         * msg : http://116.62.162.19/youbao/upload/videos/bmh.mp4
         * commodity : {"id":415,"commodityType_id":14,"commodityName":"美式焦糖--甜蜜 中罐70g ","commodityPrice":9.4,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-03-08 16:10:36","downTime":null,"state":1,"ifactivity":2,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg"}
         * commoditypictures : [{"id":1538,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356962.jpg","ifSign":0,"showOrder":2},{"id":1539,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357073.jpg","ifSign":0,"showOrder":3},{"id":1540,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357244.jpg","ifSign":0,"showOrder":4},{"id":1541,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357365.jpg","ifSign":0,"showOrder":5},{"id":1542,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357446.jpg","ifSign":0,"showOrder":6},{"id":1543,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357607.jpg","ifSign":0,"showOrder":7}]
         * commodityparticulars : [{"id":3155,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg","showOrder":1},{"id":3156,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636070a.jpg","showOrder":2},{"id":3157,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636091b.jpg","showOrder":3},{"id":3158,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636108c.jpg","showOrder":4},{"id":3159,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636124d.jpg","showOrder":5}]
         */

        private String msg;
        private CommodityBean commodity;
        private List<CommoditypicturesBean> commoditypictures;
        private List<CommodityparticularsBean> commodityparticulars;

        public String getMsg() {
            return msg;
        }

        public void setMsg(String msg) {
            this.msg = msg;
        }

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
             * id : 415
             * commodityType_id : 14
             * commodityName : 美式焦糖--甜蜜 中罐70g
             * commodityPrice : 9.4
             * express : 0.0
             * sales : 0
             * stock : 1000
             * shelfTime : 2018-03-08 16:10:36
             * downTime : null
             * state : 1
             * ifactivity : 2
             * picture : http://116.62.162.19/youbao/upload/images/15204966356221.jpg
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

        public static class CommoditypicturesBean {
            /**
             * id : 1538
             * commodity_id : 415
             * picture : http://116.62.162.19/youbao/upload/images/15204966356962.jpg
             * ifSign : 0
             * showOrder : 2
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
             * id : 3155
             * commodity_id : 415
             * character : 无
             * picture : http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg
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


//    /**
//     * object : {"msg":"0","commodity":{"id":415,"commodityType_id":14,"commodityName":"美式焦糖--甜蜜 中罐70g ","commodityPrice":9.4,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-03-08 16:10:36","downTime":null,"state":1,"ifactivity":2,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg"},"commoditypictures":[{"id":1537,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg","ifSign":0,"showOrder":1},{"id":1538,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356962.jpg","ifSign":0,"showOrder":2},{"id":1539,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357073.jpg","ifSign":0,"showOrder":3},{"id":1540,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357244.jpg","ifSign":0,"showOrder":4},{"id":1541,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357365.jpg","ifSign":0,"showOrder":5},{"id":1542,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357446.jpg","ifSign":0,"showOrder":6},{"id":1543,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357607.jpg","ifSign":0,"showOrder":7}],"commodityparticulars":[{"id":3155,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg","showOrder":1},{"id":3156,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636070a.jpg","showOrder":2},{"id":3157,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636091b.jpg","showOrder":3},{"id":3158,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636108c.jpg","showOrder":4},{"id":3159,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636124d.jpg","showOrder":5}]}
//     * state : 200
//     */
//
//    private ObjectBean object;
//    private String state;
//
//    public ObjectBean getObject() {
//        return object;
//    }
//
//    public void setObject(ObjectBean object) {
//        this.object = object;
//    }
//
//    public String getState() {
//        return state;
//    }
//
//    public void setState(String state) {
//        this.state = state;
//    }
//
//    public static class ObjectBean {
//        /**
//         * msg : 0
//         * commodity : {"id":415,"commodityType_id":14,"commodityName":"美式焦糖--甜蜜 中罐70g ","commodityPrice":9.4,"express":0,"sales":0,"stock":1000,"shelfTime":"2018-03-08 16:10:36","downTime":null,"state":1,"ifactivity":2,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg"}
//         * commoditypictures : [{"id":1537,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356221.jpg","ifSign":0,"showOrder":1},{"id":1538,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966356962.jpg","ifSign":0,"showOrder":2},{"id":1539,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357073.jpg","ifSign":0,"showOrder":3},{"id":1540,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357244.jpg","ifSign":0,"showOrder":4},{"id":1541,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357365.jpg","ifSign":0,"showOrder":5},{"id":1542,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357446.jpg","ifSign":0,"showOrder":6},{"id":1543,"commodity_id":415,"picture":"http://116.62.162.19/youbao/upload/images/15204966357607.jpg","ifSign":0,"showOrder":7}]
//         * commodityparticulars : [{"id":3155,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg","showOrder":1},{"id":3156,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636070a.jpg","showOrder":2},{"id":3157,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636091b.jpg","showOrder":3},{"id":3158,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636108c.jpg","showOrder":4},{"id":3159,"commodity_id":415,"character":"无","picture":"http://139.224.238.234:8021/image/1520496636124d.jpg","showOrder":5}]
//         */
//
//        private String msg;
//        private CommodityBean commodity;
//        private List<CommoditypicturesBean> commoditypictures;
//        private List<CommodityparticularsBean> commodityparticulars;
//
//        public String getMsg() {
//            return msg;
//        }
//
//        public void setMsg(String msg) {
//            this.msg = msg;
//        }
//
//        public CommodityBean getCommodity() {
//            return commodity;
//        }
//
//        public void setCommodity(CommodityBean commodity) {
//            this.commodity = commodity;
//        }
//
//        public List<CommoditypicturesBean> getCommoditypictures() {
//            return commoditypictures;
//        }
//
//        public void setCommoditypictures(List<CommoditypicturesBean> commoditypictures) {
//            this.commoditypictures = commoditypictures;
//        }
//
//        public List<CommodityparticularsBean> getCommodityparticulars() {
//            return commodityparticulars;
//        }
//
//        public void setCommodityparticulars(List<CommodityparticularsBean> commodityparticulars) {
//            this.commodityparticulars = commodityparticulars;
//        }
//
//        public static class CommodityBean {
//            /**
//             * id : 415
//             * commodityType_id : 14
//             * commodityName : 美式焦糖--甜蜜 中罐70g
//             * commodityPrice : 9.4
//             * express : 0.0
//             * sales : 0
//             * stock : 1000
//             * shelfTime : 2018-03-08 16:10:36
//             * downTime : null
//             * state : 1
//             * ifactivity : 2
//             * picture : http://116.62.162.19/youbao/upload/images/15204966356221.jpg
//             */
//
//            private int id;
//            private int commodityType_id;
//            private String commodityName;
//            private double commodityPrice;
//            private double express;
//            private int sales;
//            private int stock;
//            private String shelfTime;
//            private Object downTime;
//            private int state;
//            private int ifactivity;
//            private String picture;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCommodityType_id() {
//                return commodityType_id;
//            }
//
//            public void setCommodityType_id(int commodityType_id) {
//                this.commodityType_id = commodityType_id;
//            }
//
//            public String getCommodityName() {
//                return commodityName;
//            }
//
//            public void setCommodityName(String commodityName) {
//                this.commodityName = commodityName;
//            }
//
//            public double getCommodityPrice() {
//                return commodityPrice;
//            }
//
//            public void setCommodityPrice(double commodityPrice) {
//                this.commodityPrice = commodityPrice;
//            }
//
//            public double getExpress() {
//                return express;
//            }
//
//            public void setExpress(double express) {
//                this.express = express;
//            }
//
//            public int getSales() {
//                return sales;
//            }
//
//            public void setSales(int sales) {
//                this.sales = sales;
//            }
//
//            public int getStock() {
//                return stock;
//            }
//
//            public void setStock(int stock) {
//                this.stock = stock;
//            }
//
//            public String getShelfTime() {
//                return shelfTime;
//            }
//
//            public void setShelfTime(String shelfTime) {
//                this.shelfTime = shelfTime;
//            }
//
//            public Object getDownTime() {
//                return downTime;
//            }
//
//            public void setDownTime(Object downTime) {
//                this.downTime = downTime;
//            }
//
//            public int getState() {
//                return state;
//            }
//
//            public void setState(int state) {
//                this.state = state;
//            }
//
//            public int getIfactivity() {
//                return ifactivity;
//            }
//
//            public void setIfactivity(int ifactivity) {
//                this.ifactivity = ifactivity;
//            }
//
//            public String getPicture() {
//                return picture;
//            }
//
//            public void setPicture(String picture) {
//                this.picture = picture;
//            }
//        }
//
//        public static class CommoditypicturesBean {
//            /**
//             * id : 1537
//             * commodity_id : 415
//             * picture : http://116.62.162.19/youbao/upload/images/15204966356221.jpg
//             * ifSign : 0
//             * showOrder : 1
//             */
//
//            private int id;
//            private int commodity_id;
//            private String picture;
//            private int ifSign;
//            private int showOrder;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCommodity_id() {
//                return commodity_id;
//            }
//
//            public void setCommodity_id(int commodity_id) {
//                this.commodity_id = commodity_id;
//            }
//
//            public String getPicture() {
//                return picture;
//            }
//
//            public void setPicture(String picture) {
//                this.picture = picture;
//            }
//
//            public int getIfSign() {
//                return ifSign;
//            }
//
//            public void setIfSign(int ifSign) {
//                this.ifSign = ifSign;
//            }
//
//            public int getShowOrder() {
//                return showOrder;
//            }
//
//            public void setShowOrder(int showOrder) {
//                this.showOrder = showOrder;
//            }
//        }
//
//        public static class CommodityparticularsBean {
//            /**
//             * id : 3155
//             * commodity_id : 415
//             * character : 无
//             * picture : http://139.224.238.234:8021/image/1520496635776366924420156123227.jpg
//             * showOrder : 1
//             */
//
//            private int id;
//            private int commodity_id;
//            private String character;
//            private String picture;
//            private int showOrder;
//
//            public int getId() {
//                return id;
//            }
//
//            public void setId(int id) {
//                this.id = id;
//            }
//
//            public int getCommodity_id() {
//                return commodity_id;
//            }
//
//            public void setCommodity_id(int commodity_id) {
//                this.commodity_id = commodity_id;
//            }
//
//            public String getCharacter() {
//                return character;
//            }
//
//            public void setCharacter(String character) {
//                this.character = character;
//            }
//
//            public String getPicture() {
//                return picture;
//            }
//
//            public void setPicture(String picture) {
//                this.picture = picture;
//            }
//
//            public int getShowOrder() {
//                return showOrder;
//            }
//
//            public void setShowOrder(int showOrder) {
//                this.showOrder = showOrder;
//            }
//        }
//    }
}
