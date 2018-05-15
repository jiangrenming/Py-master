package com.yuqi.admin.py.bean;

import java.io.Serializable;

/**
 * Created by jiangrenming on 2018/5/15.
 */

public class ProjectItem implements Serializable{

    private int  id ;
    private int commodityType_id;
    private String commodityName;
    private String commodityPrice;
    private String express;
    private int  sales;
    private int stock;
    private String shelfTime;
    private String downTime;
    private int state;
    private  int ifactivity;
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

    public String getCommodityPrice() {
        return commodityPrice;
    }

    public void setCommodityPrice(String commodityPrice) {
        this.commodityPrice = commodityPrice;
    }

    public String getExpress() {
        return express;
    }

    public void setExpress(String express) {
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

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
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
