package com.yuqi.admin.py.data;

import android.content.Intent;
import android.graphics.Bitmap;

import com.yuqi.admin.py.bean.LoginBean;

/**
 * Created by Administrator on 2017/4/26.
 */

public class CommonData {
    public static int windowWidth = 0;
    public static int windowHeight = 0;

    // APP_ID 替换为你的应用从官方网站申请到的合法appId
    public static String APP_ID = "wxd220618f0c9781b5";
    public final  static String BASE_URL="http://192.168.0.111:8080/pyjh/app/";//user/login
//    http://192.168.0.111:8080/pyjh/app/userinfo/personal/center?user_id=97
    public final static String URL="http://139.224.238.234:8021/pyjh/";//正式
    public final static String DEBUG_URL="http://192.168.0.111:8080/pyjh/app/user/";//测试
    public final static String alipayURL="http://139.224.238.234:8021/pyjh/api/alipay/";
    public final static String weixinURL="http://139.224.238.234:8021/pyjh/api/weixin/";

//    public final static String  URL="http://192.168.1.117:7755/pyjh/";
//    public final static String  alipayURL="http://192.168.1.117:7755/pyjh/api/alipay/";
    public static final String APP_UPDATE_SERVER_URL = "http://www.pengyoujuhui.com:8021/version/version.json";
    public static String state;          //登录成功状态

    public static String accounts;            //用户账号
    public static String password;            //用户密码
    public static String token;            //临时id，唯一标识
    public static int user_id;             //用户ID
    public static String portrait;            //用户头像
    public static String nickname;             //昵称
    public static int gender;          //性别
    public static String mailbox;    //邮箱
    public static String phoneNumber;          //手机号
    public static String companyName;         //公司名
    public static double balance;       //余额
    public static String creationTime;          //创建时间
    public static int company_integral;          //积分

    //快递查询
    public static String ORDER_EXPRESS = "http://m.kuaidi100.com/index_all.html";

    public static final int TYPENUM_CONTENT = 4;
    public static final int TUPIAN = 1;
    public static final int SHIPIAN = 2;
    public static final int LUYIN = 3;

    public static final String REQUEST_PARAMETER= "请求参数=" ;
    public static final String REQUEST_SUCCESS= "返回参数=" ;
    public static final String REQUEST_EXCEOTON= "请求异常=" ;

    public static final String LISTVIEW_FIRSTLOAD_NULL = "没有任何记录" ;
    public static final String LISTVIEW_LOADING = "加载中，请稍后" ;
    public static final String LISTVIEW_LOADING_FAILD = "加载失败，点击重新加载" ;
    public static final String LISTVIEW_REFRESHING_TOUCH_DOWN = "下拉加载" ;
    public static final String LISTVIEW_REFRESHING_TOUCH_HALF = "释放刷新" ;
    public static final String LISTVIEW_REFRESHING = "正在刷新，请稍后" ;

}
