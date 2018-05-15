package com.yuqi.admin.py.bean;

/**
 * Created by Administrator on 2017/12/25.
 * 支付宝订单实体类
 */
public class AlipayBean {


    /**
     * result : alipay_sdk=alipay-sdk-java-dynamicVersionNo&app_id=2017121900978814&biz_content=%7B%22body%22%3A%22%E6%82%A8%E8%B4%AD%E4%B9%B0%E5%BD%AD%E5%8F%8B%E8%81%9A%E6%B1%87%E5%95%86%E5%93%81%E5%85%B150%E5%85%83%22%2C%22out_trade_no%22%3A%222017122516128517837c26b%22%2C%22passback_params%22%3A%22%25C4%25FA%25B9%25BA%25C2%25F2%25C5%25ED%25D3%25D1%25BE%25DB%25BB%25E3%25C9%25CC%25C6%25B7%25B9%25B250%25D4%25AA%22%2C%22product_code%22%3A%22QUICK_WAP_PAY%22%2C%22seller_id%22%3A%222088921013613086%22%2C%22subject%22%3A%22%E5%BD%AD%E5%8F%8B%E8%81%9A%E6%B1%87%E5%95%86%E5%9F%8E%E4%BB%98%E6%AC%BE%22%2C%22timeout_express%22%3A%2230m%22%2C%22total_amount%22%3A%2250%22%7D&charset=UTF-8&format=json&method=alipay.trade.app.pay&notify_url=http%3A%2F%2Fwww.pengyoujuhui.com%3A8021%2Fpyjh%2Fapi%2Falipay%2Fnotify_url.action&sign=psfODiArfp7%2FdSjG4eMEAIefZ4r2Umq1XrLGdtAGILUFWyDSlSu8Mr75IKHXny50hLR6fzham4hpRjtQHjfyl%2BRt206a80ks3hxbYK7Ov%2B8FocreYxhAqH7DPLeyAV462nP%2BrMKuCMx%2BnC%2FenMPQHJlt3PpF2Vc6aZf7aUHOVKE%2BA%2FTx%2BoTsmJf2h6ndlqbIhszr4vQF6SZwjvjzFbm7e377iREY6Rb%2BDCllJQv5JAKFhsRR8ZoMyBACJoKTM6Mspk6%2B5lg1urqpBIWlejheqT1qSeAnOIs94INb9CciwoeAopnoq%2BH0q9wJGZsN8X3pVgC8c3GlJWPqzQFgXH1oXw%3D%3D&sign_type=RSA2&timestamp=2017-12-25+16%3A15%3A03&version=1.0
     * msg : 订单生成成功
     * status : 0
     */

    private String result;
    private String msg;
    private int status;

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
