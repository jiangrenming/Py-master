package com.yuqi.admin.py.bean;

import android.os.Parcelable;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by Administrator on 2018/1/9.
 */
public class WeChatHaymentBean {

    /**
     * msg : 获取prepayid成功,生成订单成功
     * resultMap : {"appid":"wxd220618f0c9781b5","partnerid":"1495277322","prepayid":"wx20180109131301f629e980f10669985664","package":"Sign=WXPay","noncestr":"f514cec81cb148559cf475e7426eed5e","timestamp":"1515474781","sign":"CB17718AF9C14B86560ACB01B23EEDD4"}
     * orderNum : 2018010913130100000
     * status : 200
     */

    private String msg;
    private ResultMapBean resultMap;
    private String orderNum;
    private String status;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public ResultMapBean getResultMap() {
        return resultMap;
    }

    public void setResultMap(ResultMapBean resultMap) {
        this.resultMap = resultMap;
    }

    public String getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(String orderNum) {
        this.orderNum = orderNum;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class ResultMapBean implements Serializable {
        /**
         * appid : wxd220618f0c9781b5
         * partnerid : 1495277322
         * prepayid : wx20180109131301f629e980f10669985664
         * package : Sign=WXPay
         * noncestr : f514cec81cb148559cf475e7426eed5e
         * timestamp : 1515474781
         * sign : CB17718AF9C14B86560ACB01B23EEDD4
         */

        private String appid;
        private String partnerid;
        private String prepayid;
        private String packageX;
        private String noncestr;
        private String timestamp;
        private String sign;

        public String getAppid() {
            return appid;
        }

        public void setAppid(String appid) {
            this.appid = appid;
        }

        public String getPartnerid() {
            return partnerid;
        }

        public void setPartnerid(String partnerid) {
            this.partnerid = partnerid;
        }

        public String getPrepayid() {
            return prepayid;
        }

        public void setPrepayid(String prepayid) {
            this.prepayid = prepayid;
        }

        public String getPackageX() {
            return packageX;
        }

        public void setPackageX(String packageX) {
            this.packageX = packageX;
        }

        public String getNoncestr() {
            return noncestr;
        }

        public void setNoncestr(String noncestr) {
            this.noncestr = noncestr;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public void setTimestamp(String timestamp) {
            this.timestamp = timestamp;
        }

        public String getSign() {
            return sign;
        }

        public void setSign(String sign) {
            this.sign = sign;
        }
    }
}
