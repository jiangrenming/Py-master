package com.yuqi.admin.py.bean;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Administrator on 2018/3/21/021.
 */

public class RewardBean {


    /**
     * data : {"COUNT(*)":1,"SUM(getMoney)":10.8,"MyRewards":[{"user_id":147,"accounts":"13247736181","registerTime":"2018-03-21 16:44:53","getMoney":10.8}]}
     * status : 200
     */

    private DataBean data;
    private String status;

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public static class DataBean {
        @SerializedName("COUNT(*)")
        private int _$COUNT24; // FIXME check this code
        @SerializedName("SUM(getMoney)")
        private double _$SUMGetMoney180; // FIXME check this code
        private List<MyRewardsBean> MyRewards;

        public int get_$COUNT24() {
            return _$COUNT24;
        }

        public void set_$COUNT24(int _$COUNT24) {
            this._$COUNT24 = _$COUNT24;
        }

        public double get_$SUMGetMoney180() {
            return _$SUMGetMoney180;
        }

        public void set_$SUMGetMoney180(double _$SUMGetMoney180) {
            this._$SUMGetMoney180 = _$SUMGetMoney180;
        }

        public List<MyRewardsBean> getMyRewards() {
            return MyRewards;
        }

        public void setMyRewards(List<MyRewardsBean> MyRewards) {
            this.MyRewards = MyRewards;
        }

        public static class MyRewardsBean {
            /**
             * user_id : 147
             * accounts : 13247736181
             * registerTime : 2018-03-21 16:44:53
             * getMoney : 10.8
             */

            private int user_id;
            private String accounts;
            private String registerTime;
            private double getMoney;

            public int getUser_id() {
                return user_id;
            }

            public void setUser_id(int user_id) {
                this.user_id = user_id;
            }

            public String getAccounts() {
                return accounts;
            }

            public void setAccounts(String accounts) {
                this.accounts = accounts;
            }

            public String getRegisterTime() {
                return registerTime;
            }

            public void setRegisterTime(String registerTime) {
                this.registerTime = registerTime;
            }

            public double getGetMoney() {
                return getMoney;
            }

            public void setGetMoney(double getMoney) {
                this.getMoney = getMoney;
            }
        }
    }
}
