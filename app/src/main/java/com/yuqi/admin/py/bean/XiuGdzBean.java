package com.yuqi.admin.py.bean;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;
import java.security.acl.Permission;

/**
 * Created by Administrator on 2017/12/18.
 *  修改地址
 */
public class XiuGdzBean  implements Serializable {

    private String Id;//地址id
    private String consignee; //收货人
    private String phoneNumber;//联系号码
    private String shippingAddress;//收货地址
    private String StringdetailedAddress;// 详细地址
    private String IfDefaultAddress;//默认选择

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
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

    public String getStringdetailedAddress() {
        return StringdetailedAddress;
    }

    public void setStringdetailedAddress(String stringdetailedAddress) {
        StringdetailedAddress = stringdetailedAddress;
    }

    public String getIfDefaultAddress() {
        return IfDefaultAddress;
    }

    public void setIfDefaultAddress(String ifDefaultAddress) {
        IfDefaultAddress = ifDefaultAddress;
    }

}

