package com.yuqi.admin.py.bean;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.List;

/**
 * Created by Administrator on 2017/12/19.
 */
public class APPHomePageBean {

    /**
     * object : {"Activitycommodities":[{"id":77,"commodityType_id":11,"commodityName":"耳机：华硕（ASUS）玩家国度 猛禽 聚变300 7.1头戴式电竞游戏耳机Rog Fusion 300吃鸡绝地求生耳机","commodityPrice":899,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143840024371-1.jpg"}],"commodities":[{"id":77,"commodityType_id":11,"commodityName":"耳机：华硕（ASUS）玩家国度 猛禽 聚变300 7.1头戴式电竞游戏耳机Rog Fusion 300吃鸡绝地求生耳机","commodityPrice":899,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143840024371-1.jpg"},{"id":78,"commodityType_id":11,"commodityName":"华为：荣耀9 全网通 标配版 4GB+64GB 魅海蓝 移动联通电信4G手机 双卡双待","commodityPrice":2299,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143841083621-1.jpg"},{"id":79,"commodityType_id":11,"commodityName":"Oppo：OPPO R11s 星幕新年版 全面屏双摄拍照手机 全网通4G+64G 双卡双待手机新年红","commodityPrice":3199,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143842524131-1.jpg"},{"id":80,"commodityType_id":11,"commodityName":"充电器：Capshi 苹果充电器 1A手机充电头+苹果数据线1.2米 中国红 iphone5/5s/6/6s/Plus/7/8/X/iPad/Air/Pro","commodityPrice":23.9,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143843697651-1.jpg"},{"id":81,"commodityType_id":11,"commodityName":"平板：Apple iPad Pro 平板电脑 10.5 英寸（64G WLAN版/A10X芯片/Retina屏/Multi-Touch技术 MQDT2CH/A）深空灰色","commodityPrice":4888,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143857155791-1.jpg"},{"id":82,"commodityType_id":11,"commodityName":"手机壳：飚爱 苹果X手机壳透明iPhoneX/10钢化玻璃软边硬壳 硅胶防摔保护套 男女超薄新款 腮红金","commodityPrice":58,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143859590591-1.jpg"},{"id":83,"commodityType_id":11,"commodityName":"小米：小米MIX2 全网通 6GB+64GB 黑色 移动联通电信4G手机 双卡双待","commodityPrice":3299,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143862220471-1.jpg"},{"id":89,"commodityType_id":9,"commodityName":"健康贴：健康贴","commodityPrice":50,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514394842577yankantie.jpg"},{"id":90,"commodityType_id":9,"commodityName":"中科特膳1：［1盒装］中科特膳美膳美姿纤美营养素 135g／盒（15g*9）","commodityPrice":198,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514395508872zkts1-1.jpg"},{"id":91,"commodityType_id":10,"commodityName":"戴尔：戴尔(DELL)灵越3668-R1848台式电脑整机(i5-7400 8G 1T 2G独显 DVD WIFI 三年上门 有线键鼠)23.6英寸","commodityPrice":4999,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514395635369daier-1.jpg"},{"id":92,"commodityType_id":10,"commodityName":"戴尔：戴尔DELL XPS13-9360-R3609S 13.3英寸轻薄窄边框笔记本电脑(i5-8250U 8G 256GSSD IPS Win10)无忌银","commodityPrice":7198,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514396748065daier-t-1.jpg"},{"id":93,"commodityType_id":15,"commodityName":"办公杯：玉泉 创意陶瓷杯子 牛奶杯咖啡杯子 马克杯办公室 藤声","commodityPrice":30,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143976993291-1.jpg"},{"id":94,"commodityType_id":15,"commodityName":"办公椅：USUN 电脑椅办公椅电竞人体工学椅家用休闲转椅老板座椅职员工椅子可躺 U21-草绿","commodityPrice":398,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143979109291-1.jpg"},{"id":95,"commodityType_id":14,"commodityName":"海之蓝：洋河蓝色经典 海之蓝 52度 整箱装白酒 480ml*6瓶（内含3个礼袋） 口感绵柔浓香型","commodityPrice":930,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143980472161-1.jpg"},{"id":96,"commodityType_id":14,"commodityName":"江中猴姑：早餐米稀猴菇代餐粉营养早餐米昔米糊谷物酥性苏打饼干 米稀 猴头菇米稀 450g*2盒","commodityPrice":196,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143983721051-1.jpg"},{"id":97,"commodityType_id":12,"commodityName":"VS:沙宣去屑洗发水水润去屑750ml（洗发露洗头膏新老包装随机发货）","commodityPrice":60.09,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143988869911-1.jpg"},{"id":98,"commodityType_id":12,"commodityName":"蓝月亮：蓝月亮套装10.32斤：2kg亮白洗衣液薰衣草*2+500g翻盖*2+80g旅行装*2","commodityPrice":85,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143989653821-1.jpg"},{"id":99,"commodityType_id":13,"commodityName":"小米（MI）90分旅行箱拉杆箱 男女万向轮登机行李箱 20英寸 星空灰","commodityPrice":299,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15143996503501-1.jpg"},{"id":100,"commodityType_id":9,"commodityName":"中科特膳5：［5盒起售］中科特膳美膳美姿纤美营养素 135g/盒（15g*9） ","commodityPrice":156,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514515757549zkts1-1.jpg"},{"id":101,"commodityType_id":9,"commodityName":"太湖清水大闸蟹：现货正宗太湖清水大闸蟹 鲜活","commodityPrice":998,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514515861195太湖清水大闸蟹.jpg"},{"id":102,"commodityType_id":9,"commodityName":"磁悬浮润滑油：磁悬浮润滑油 SN／GF-5 0W-30 0W-40 汽油机油","commodityPrice":100,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514515938678磁悬浮润滑油.jpg"},{"id":103,"commodityType_id":9,"commodityName":"车载空气净化器：车载空气净化器","commodityPrice":499,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514516001659车载净化器.jpg"},{"id":104,"commodityType_id":9,"commodityName":"负离子能量眼睛（黑色、蓝色、白色、红色、儿童款）","commodityPrice":800,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514516216876负离子眼镜.jpg"},{"id":105,"commodityType_id":9,"commodityName":"手工玉米花：美式焦糖--甜蜜","commodityPrice":12,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145163356071.jpg"},{"id":106,"commodityType_id":9,"commodityName":"11.\t墙纸：爱舍东方（ASEast） 现代简约欧式3D无纺鹿皮背景壁纸卧室客厅电视沙发背景墙纸 暮光 00375银灰色 5.3平方","commodityPrice":138,"express":0,"sales":0,"picture":"http://120.77.242.12/image/1514517163937墙纸-1.jpg"},{"id":107,"commodityType_id":13,"commodityName":"ELLE女包 屋顶几何造型单肩包女手提包斜跨包手拎包女士包包70831 军绿色 ","commodityPrice":619,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145178023004-1.jpg"},{"id":108,"commodityType_id":13,"commodityName":"【合金铝框】EAZZ铝框拉杆箱 万向轮行李箱男女士登机箱20/24/28英寸旅行箱密码箱 高端 铝框-银色 20英寸丨登机箱","commodityPrice":259,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145179086223-1.jpg"},{"id":109,"commodityType_id":13,"commodityName":"七匹狼（SEPTWOLVES）男士公文包 商务手提包笔记本电脑包男包单肩包 M1000335-101 黑色","commodityPrice":339,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145180273645-1.jpg"},{"id":110,"commodityType_id":13,"commodityName":"鹃羚 万向轮拉杆箱女18英寸小行李箱学生旅行箱包男商务登机箱密码箱 香槟色+化妆包 18英寸【登机箱】","commodityPrice":159,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145181053941-1.jpg"},{"id":111,"commodityType_id":13,"commodityName":"小米（MI）90分旅行箱拉杆箱 男女万向轮登机行李箱 20英寸 星空灰 ","commodityPrice":299,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145181668191-1.jpg"},{"id":112,"commodityType_id":13,"commodityName":"妮狼拉杆箱旅行箱包皮箱密码箱行李箱万向轮箱子男女学生登机箱潮 白天图案 24英寸","commodityPrice":262,"express":0,"sales":0,"picture":"http://120.77.242.12/image/15145183543111-1.jpg"}],"pictures":[{"id":4,"picture_url":"http://120.77.242.12/image/pyjhbanner1.png","first":1,"theme_url":"http://120.77.242.12/image/pyjhbanner1.png"},{"id":5,"picture_url":"http://120.77.242.12/image/pyjhbanner2.png","first":2,"theme_url":"http://120.77.242.12/image/pyjhbanner2.png"},{"id":6,"picture_url":"http://120.77.242.12/image/pyjhbanner3.png","first":3,"theme_url":"http://120.77.242.12/image/pyjhbanner3.png"},{"id":7,"picture_url":"http://120.77.242.12/image/pyjhbanner4.png","first":4,"theme_url":"http://120.77.242.12/image/pyjhbanner4.png"}],"demands":[{"id":7,"name":"彭先生","gender":1,"phoneNumber":"15201884835","demandContext":"我需要采购一批办公用品","auditStatus":1,"accomplishStatus":1,"createTime":"2017-12-27 21:09:31"}]}
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
        private List<ActivitycommoditiesBean> Activitycommodities;
        private List<CommoditytypesBean> commoditytypes;
        private List<CommoditiesBean> commodities;
        private List<PicturesBean> pictures;
        private List<DemandsBean> demands;

        public List<ActivitycommoditiesBean> getActivitycommodities() {
            return Activitycommodities;
        }

        public void setActivitycommodities(List<ActivitycommoditiesBean> activitycommodities) {
            Activitycommodities = activitycommodities;
        }
        public List<CommoditytypesBean> getCommoditytypes() {
            return commoditytypes;
        }

        public void setCommoditytypes(List<CommoditytypesBean> commoditytypes) {
            this.commoditytypes = commoditytypes;
        }

        public List<CommoditiesBean> getCommodities() {
            return commodities;
        }

        public void setCommodities(List<CommoditiesBean> commodities) {
            this.commodities = commodities;
        }

        public List<PicturesBean> getPictures() {
            return pictures;
        }

        public void setPictures(List<PicturesBean> pictures) {
            this.pictures = pictures;
        }

        public List<DemandsBean> getDemands() {
            return demands;
        }

        public void setDemands(List<DemandsBean> demands) {
            this.demands = demands;
        }

        public static class ActivitycommoditiesBean {
            /**
             * id : 77
             * commodityType_id : 11
             * commodityName : 耳机：华硕（ASUS）玩家国度 猛禽 聚变300 7.1头戴式电竞游戏耳机Rog Fusion 300吃鸡绝地求生耳机
             * commodityPrice : 899.0
             * express : 0.0
             * sales : 0
             * picture : http://120.77.242.12/image/15143840024371-1.jpg
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
                try {
                    String strGBK = URLEncoder.encode(picture, "GBK");
                    System.out.println(strGBK);
                    String strUTF8 = URLDecoder.decode(picture, "UTF-8");
                    System.out.println(strUTF8);
                } catch (UnsupportedEncodingException e) {
                    e.printStackTrace();
                }

                return picture;
            }

            public void setPicture(String picture) {
                this.picture = picture;
            }
        }

        public static class CommoditytypesBean {
            /**
             * id : 10
             * typeName : 电器
             * superiorId : 0
             */

            private int id;
            private String typeName;
            private int superiorId;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getTypeName() {
                return typeName;
            }

            public void setTypeName(String typeName) {
                this.typeName = typeName;
            }

            public int getSuperiorId() {
                return superiorId;
            }

            public void setSuperiorId(int superiorId) {
                this.superiorId = superiorId;
            }
        }

        public static class CommoditiesBean {
            /**
             * id : 77
             * commodityType_id : 11
             * commodityName : 耳机：华硕（ASUS）玩家国度 猛禽 聚变300 7.1头戴式电竞游戏耳机Rog Fusion 300吃鸡绝地求生耳机
             * commodityPrice : 899.0
             * express : 0.0
             * sales : 0
             * picture : http://120.77.242.12/image/15143840024371-1.jpg
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

        public static class PicturesBean {
            /**
             * id : 4
             * picture_url : http://120.77.242.12/image/pyjhbanner1.png
             * first : 1
             * theme_url : http://120.77.242.12/image/pyjhbanner1.png
             */

            private int id;
            private String picture_url;
            private int first;
            private String theme_url;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getPicture_url() {
                return picture_url;
            }

            public void setPicture_url(String picture_url) {
                this.picture_url = picture_url;
            }

            public int getFirst() {
                return first;
            }

            public void setFirst(int first) {
                this.first = first;
            }

            public String getTheme_url() {
                return theme_url;
            }

            public void setTheme_url(String theme_url) {
                this.theme_url = theme_url;
            }
        }

        public static class DemandsBean {
            /**
             * id : 7
             * name : 彭先生
             * gender : 1
             * phoneNumber : 15201884835
             * demandContext : 我需要采购一批办公用品
             * auditStatus : 1
             * accomplishStatus : 1
             * createTime : 2017-12-27 21:09:31
             */

            private int id;
            private String name;
            private int gender;
            private String phoneNumber;
            private String demandContext;
            private int auditStatus;
            private int accomplishStatus;
            private String createTime;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public int getGender() {
                return gender;
            }

            public void setGender(int gender) {
                this.gender = gender;
            }

            public String getPhoneNumber() {
                return phoneNumber;
            }

            public void setPhoneNumber(String phoneNumber) {
                this.phoneNumber = phoneNumber;
            }

            public String getDemandContext() {
                return demandContext;
            }

            public void setDemandContext(String demandContext) {
                this.demandContext = demandContext;
            }

            public int getAuditStatus() {
                return auditStatus;
            }

            public void setAuditStatus(int auditStatus) {
                this.auditStatus = auditStatus;
            }

            public int getAccomplishStatus() {
                return accomplishStatus;
            }

            public void setAccomplishStatus(int accomplishStatus) {
                this.accomplishStatus = accomplishStatus;
            }

            public String getCreateTime() {
                return createTime;
            }

            public void setCreateTime(String createTime) {
                this.createTime = createTime;
            }
        }
    }
}
