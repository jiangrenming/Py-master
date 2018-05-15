package com.yuqi.admin.py.bean;

import java.security.acl.Permission;

/**
 * Created by Administrator on 2017/11/30.
 */
public class LoginBean implements Permission{
//    /**
//     * msg : success
//     * code : 200
//     * data : {"user_id":219}
//     */
//
//    private String msg;
//    private String code;
//    private DataBean data;
//
//    public String getMsg() {
//        return msg;
//    }
//
//    public void setMsg(String msg) {
//        this.msg = msg;
//    }
//
//    public String getCode() {
//        return code;
//    }
//
//    public void setCode(String code) {
//        this.code = code;
//    }
//
//    public DataBean getData() {
//        return data;
//    }
//
//    public void setData(DataBean data) {
//        this.data = data;
//    }
//
//    public static class DataBean {
//        /**
//         * user_id : 219
//         */
//
//        private int user_id;
//
//        public int getUser_id() {
//            return user_id;
//        }
//
//        public void setUser_id(int user_id) {
//            this.user_id = user_id;
//        }
//    }


    /**
     * object : {"user":{"id":1,"accounts":"18797765489","password":"123456","token":null},"userinfo":{"id":11,"user_id":1,"portrait":"空","nickname":"21211221","gender":1,"mailbox":null,"phoneNumber":"51513546","companyName":null,"company_integral":0,"balance":99,"creationTime":"2017-12-7"}}
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
         * user : {"id":1,"accounts":"18797765489","password":"123456","token":null}
         * userinfo : {"id":11,"user_id":1,"portrait":"空","nickname":"21211221","gender":1,"mailbox":null,"phoneNumber":"51513546","companyName":null,"company_integral":0,"balance":99,"creationTime":"2017-12-7"}
         */

        private UserBean user;
        private UserinfoBean userinfo;

        public UserBean getUser() {
            return user;
        }

        public void setUser(UserBean user) {
            this.user = user;
        }

        public UserinfoBean getUserinfo() {
            return userinfo;
        }

        public void setUserinfo(UserinfoBean userinfo) {
            this.userinfo = userinfo;
        }

        public static class UserBean {
            /**
             * id : 1
             * accounts : 18797765489
             * password : 123456
             * token : null
             */

            private int id;
            private String accounts;
            private String password;
            private Object token;

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public String getAccounts() {
                return accounts;
            }

            public void setAccounts(String accounts) {
                this.accounts = accounts;
            }

            public String getPassword() {
                return password;
            }

            public void setPassword(String password) {
                this.password = password;
            }

            public Object getToken() {
                return token;
            }

            public void setToken(Object token) {
                this.token = token;
            }
        }

        public static class UserinfoBean {
            /**
             * id : 11
             * user_id : 1
             * portrait : 空
             * nickname : 21211221
             * gender : 1
             * mailbox : null
             * phoneNumber : 51513546
             * companyName : null
             * company_integral : 0
             * balance : 99.0
             * creationTime : 2017-12-7
             */

            private int id;
            private int user_id;
            private String portrait;
            private String nickname;
            private int gender;
            private Object mailbox;
            private String phoneNumber;
            private Object companyName;
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

            public String getNickname() {
                return nickname;
            }

            public void setNickname(String nickname) {
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

            public Object getCompanyName() {
                return companyName;
            }

            public void setCompanyName(Object companyName) {
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
}
