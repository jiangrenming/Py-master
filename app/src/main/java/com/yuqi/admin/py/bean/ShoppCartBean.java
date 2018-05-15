package com.yuqi.admin.py.bean;

import java.util.List;

/**
 * Created by Administrator on 2018/1/12.
 */
public class ShoppCartBean {

    /**
     * object : [{"id":197,"commodity_id":156,"typeName":"企业","commodityName":"磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油","commodityNumber":1,"commodityPrice":100,"picture":"http://139.224.238.234:8021/image/1515379882723磁悬浮润滑油.jpg"},{"id":207,"commodity_id":158,"typeName":"企业","commodityName":"负离子能量眼镜","commodityNumber":2,"commodityPrice":800,"picture":"http://139.224.238.234:8021/image/1515379992520负离子眼镜.jpg"},{"id":208,"commodity_id":167,"typeName":"企业","commodityName":"联想（Lenovo）天逸510 Pro商用台式电脑整机（i5-7400 8G 1T GT730 2G独显 三年上门 Win10 Office）23英寸","commodityNumber":1,"commodityPrice":5099,"picture":"http://139.224.238.234:8021/image/1515548972947联想-1.jpg"},{"id":201,"commodity_id":161,"typeName":"洗护","commodityName":"希诺不锈钢真空保温杯XN-8600｜XN-8601｜XN-8602｜XN-8603","commodityNumber":1,"commodityPrice":218,"picture":"http://139.224.238.234:8021/image/15154142153881515413866(1).jpg"},{"id":202,"commodity_id":196,"typeName":"洗护","commodityName":"沙宣去屑洗发水水润去屑750ml（洗发露洗头膏新老包装随机发货） ","commodityNumber":1,"commodityPrice":60.9,"picture":"http://139.224.238.234:8021/image/15155508850661-1.jpg"},{"id":205,"commodity_id":159,"typeName":"食品","commodityName":"美式焦糖--甜蜜","commodityNumber":1,"commodityPrice":12,"picture":"http://139.224.238.234:8021/image/15153800352861.jpg"},{"id":206,"commodity_id":163,"typeName":"食品","commodityName":"马来西亚天然燕窝水挑雅致款(100g)","commodityNumber":1,"commodityPrice":0.02,"picture":"http://139.224.238.234:8021/image/1515414749089商品图－1.jpg"},{"id":199,"commodity_id":189,"typeName":"手机","commodityName":"OPPO R11s 星幕新年版 全面屏双摄拍照手机 全网通4G+64G 双卡双待手机新年红","commodityNumber":2,"commodityPrice":3199,"picture":"http://139.224.238.234:8021/image/15155505096001-1.jpg"},{"id":200,"commodity_id":190,"typeName":"手机","commodityName":"荣耀9 全网通 标配版 4GB+64GB 魅海蓝 移动联通电信4G手机 双卡双待 ","commodityNumber":2,"commodityPrice":2299,"picture":"http://139.224.238.234:8021/image/15155505517941-1.jpg"},{"id":196,"commodity_id":154,"typeName":"其他","commodityName":"［1盒装］中科特膳美膳美姿纤美营养素 135g／盒（15g*9）","commodityNumber":1,"commodityPrice":198,"picture":"http://139.224.238.234:8021/image/1515379775394zkts1-1.jpg"},{"id":203,"commodity_id":202,"typeName":"箱包","commodityName":"小米（MI）90分旅行箱拉杆箱 男女万向轮登机行李箱 20英寸 星空灰  ","commodityNumber":1,"commodityPrice":299,"picture":"http://139.224.238.234:8021/image/15155514902571-1.jpg"},{"id":204,"commodity_id":205,"typeName":"箱包","commodityName":"ELLE女包 屋顶几何造型单肩包女手提包斜跨包手拎包女士包包70831 军绿色  ","commodityNumber":1,"commodityPrice":619,"picture":"http://139.224.238.234:8021/image/15155530837164-1.jpg"},{"id":198,"commodity_id":157,"typeName":"电器","commodityName":"车载空气净化器","commodityNumber":1,"commodityPrice":499,"picture":"http://139.224.238.234:8021/image/1515379927301车载净化器.jpg"}]
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
         * id : 197
         * commodity_id : 156
         * typeName : 企业
         * commodityName : 磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油
         * commodityNumber : 1
         * commodityPrice : 100.0
         * picture : http://139.224.238.234:8021/image/1515379882723磁悬浮润滑油.jpg
         */

        private int id;
        private int commodity_id;
        private String typeName;
        private String commodityName;
        private int commodityNumber;
        private double commodityPrice;
        private String picture;

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

        public String getTypeName() {
            return typeName;
        }

        public void setTypeName(String typeName) {
            this.typeName = typeName;
        }

        public String getCommodityName() {
            return commodityName;
        }

        public void setCommodityName(String commodityName) {
            this.commodityName = commodityName;
        }

        public int getCommodityNumber() {
            return commodityNumber;
        }

        public void setCommodityNumber(int commodityNumber) {
            this.commodityNumber = commodityNumber;
        }

        public double getCommodityPrice() {
            return commodityPrice;
        }

        public void setCommodityPrice(double commodityPrice) {
            this.commodityPrice = commodityPrice;
        }

        public String getPicture() {
            return picture;
        }

        public void setPicture(String picture) {
            this.picture = picture;
        }
    }
}
