package com.yuqi.admin.py.bean;

/**
 * Created by Administrator on 2018/1/3.
 * 账户中心实体类
 */
public class PersonalCenterBean {

    /**
     * companyBuyMoney : 0.0
     * msg : 成功！
     * companyIntegral : 0
     * accountCount : 3
     * balance : 0.0
     * personalBuyMoney : 0.0
     * personalIntegral : 0
     * state : 200
     * companyBuyCount : 0
     * personalBuyCount : 0
     * userinfo : {"id":64,"user_id":80,"portrait":"空","nickname":null,"gender":1,"mailbox":null,"phoneNumber":"13912345678","companyName":"羽琪科技","company_integral":0,"balance":0,"creationTime":"2018-01-02 18:08:28"}
     */

    private double companyBuyMoney;
    private String msg;
    private int companyIntegral;
    private int accountCount;
    private double balance;
    private double personalBuyMoney;
    private int personalIntegral;
    private String state;
    private int companyBuyCount;
    private int personalBuyCount;
    private UserinfoBean userinfo;

    public double getCompanyBuyMoney() {
        return companyBuyMoney;
    }

    public void setCompanyBuyMoney(double companyBuyMoney) {
        this.companyBuyMoney = companyBuyMoney;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCompanyIntegral() {
        return companyIntegral;
    }

    public void setCompanyIntegral(int companyIntegral) {
        this.companyIntegral = companyIntegral;
    }

    public int getAccountCount() {
        return accountCount;
    }

    public void setAccountCount(int accountCount) {
        this.accountCount = accountCount;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getPersonalBuyMoney() {
        return personalBuyMoney;
    }

    public void setPersonalBuyMoney(double personalBuyMoney) {
        this.personalBuyMoney = personalBuyMoney;
    }

    public int getPersonalIntegral() {
        return personalIntegral;
    }

    public void setPersonalIntegral(int personalIntegral) {
        this.personalIntegral = personalIntegral;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public int getCompanyBuyCount() {
        return companyBuyCount;
    }

    public void setCompanyBuyCount(int companyBuyCount) {
        this.companyBuyCount = companyBuyCount;
    }

    public int getPersonalBuyCount() {
        return personalBuyCount;
    }

    public void setPersonalBuyCount(int personalBuyCount) {
        this.personalBuyCount = personalBuyCount;
    }

    public UserinfoBean getUserinfo() {
        return userinfo;
    }

    public void setUserinfo(UserinfoBean userinfo) {
        this.userinfo = userinfo;
    }

    public static class UserinfoBean {
        /**
         * id : 64
         * user_id : 80
         * portrait : 空
         * nickname : null
         * gender : 1
         * mailbox : null
         * phoneNumber : 13912345678
         * companyName : 羽琪科技
         * company_integral : 0
         * balance : 0.0
         * creationTime : 2018-01-02 18:08:28
         */

        private int id;
        private int user_id;
        private String portrait;
        private Object nickname;
        private int gender;
        private Object mailbox;
        private String phoneNumber;
        private String companyName;
        private int company_integral;
        private double balance;
        private String creationTime;

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

        public String getPortrait() {
            return portrait;
        }

        public void setPortrait(String portrait) {
            this.portrait = portrait;
        }

        public Object getNickname() {
            return nickname;
        }

        public void setNickname(Object nickname) {
            this.nickname = nickname;
        }

        public int getGender() {
            return gender;
        }

        public void setGender(int gender) {
            this.gender = gender;
        }

        public Object getMailbox() {
            return mailbox;
        }

        public void setMailbox(Object mailbox) {
            this.mailbox = mailbox;
        }

        public String getPhoneNumber() {
            return phoneNumber;
        }

        public void setPhoneNumber(String phoneNumber) {
            this.phoneNumber = phoneNumber;
        }

        public String getCompanyName() {
            return companyName;
        }

        public void setCompanyName(String companyName) {
            this.companyName = companyName;
        }

        public int getCompany_integral() {
            return company_integral;
        }

        public void setCompany_integral(int company_integral) {
            this.company_integral = company_integral;
        }

        public double getBalance() {
            return balance;
        }

        public void setBalance(double balance) {
            this.balance = balance;
        }

        public String getCreationTime() {
            return creationTime;
        }

        public void setCreationTime(String creationTime) {
            this.creationTime = creationTime;
        }
    }
}
