package com.yuqi.admin.py.bean;

/**
 * Created by Administrator on 2017/12/22.
 * 默认地址实体类
 */
public class DshowDefaultShippingAddressBean {

    /**
     * object : {"id":1,"user_id":1,"consignee":"蒋","phoneNumber":"1234567899","shippingAddress":"北京市","detailedAddress":"朝阳区","ifDefaultAddress":1}
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
         * id : 1
         * user_id : 1
         * consignee : 蒋
         * phoneNumber : 1234567899
         * shippingAddress : 北京市
         * detailedAddress : 朝阳区
         * ifDefaultAddress : 1
         */

        private int id;
        private int user_id;
        private String consignee;
        private String phoneNumber;
        private String shippingAddress;
        private String detailedAddress;
        private int ifDefaultAddress;

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getUser_id() {
            return user_id;
        }

        public void setUser_id(int user_id) {
            this.user_id = user_id;
        }

        public String getConsignee() {
            return consignee;
        }

        public void setConsignee(String consignee) {
            this.consignee = consignee;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getShippingAddress() {
            return shippingAddress;
        }

        public void setShippingAddress(String shippingAddress) {
            this.shippingAddress = shippingAddress;
        }

        public String getDetailedAddress() {
            return detailedAddress;
        }

        public void setDetailedAddress(String detailedAddress) {
            this.detailedAddress = detailedAddress;
        }

        public int getIfDefaultAddress() {
            return ifDefaultAddress;
        }

        public void setIfDefaultAddress(int ifDefaultAddress) {
            this.ifDefaultAddress = ifDefaultAddress;
        }
    }
}
