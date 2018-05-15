package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.Bean;
import com.yuqi.admin.py.bean.DingDanBean;
import com.yuqi.admin.py.bean.XiuGdzBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.StringUtil;
import com.yuqi.admin.py.utils.ToastUtil;

import java.io.Serializable;
import java.util.List;

/**
 * Created by Administrator on 2017/12/21.
 *
 * 编辑收货地址
 */
public class SEditors2Activity extends BaseActivity{
    private EditText shoujianrenxingming,shoujihao,shouhuodizhi,xiangxidizhi;
    private ImageView moren;
    private TextView submit;

    private boolean moRen;
    private int moR;
    private XiuGdzBean person;
    private List<DingDanBean.DingdanBean> commodity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_spdd_dizhixinxixiugai);
        commodity = (List<DingDanBean.DingdanBean>) getIntent().getSerializableExtra("dingDan");
        person = (XiuGdzBean) getIntent().getSerializableExtra("xgdz");
        init();
    }

    private void init() {
        shoujianrenxingming = (EditText)findViewById(R.id.shoujianrenxingming);
        shoujihao = (EditText)findViewById(R.id.shoujihao);
        shouhuodizhi = (EditText)findViewById(R.id.shouhuodizhi);
        xiangxidizhi = (EditText)findViewById(R.id.xiangxidizhi);
        moren = (ImageView) findViewById(R.id.moren);

        submit = (TextView)findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setText("保存");

        shoujianrenxingming.setText(person.getConsignee());
        shoujihao.setText(person.getPhoneNumber());
        shouhuodizhi.setText(person.getShippingAddress());
        xiangxidizhi.setText(person.getStringdetailedAddress());
        moR = Integer.parseInt(person.getIfDefaultAddress());
        if (moR != 1){
            moren.setImageResource(R.mipmap.moren1);
            moRen = false;
        }else {
            moren.setImageResource(R.mipmap.moren);
            moRen = true;
        }
    }

    @Override
    public String title_text() {
        return "编辑收货信息";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                String mResult = isContentEmpty();
                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    updateShippingAddressHTTP();
                }

                break;
            case R.id.moren:
                if (moRen){
                    moren.setImageResource(R.mipmap.moren);
                    moR = 0;
                    moRen = false;
                }else {
                    moren.setImageResource(R.mipmap.moren1);
                    moR = 1;
                    moRen = true;
                }
                break;
        }
    }
    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shoujianrenxingming))){return "请填写收件人";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shoujihao))){return "请填写手机号";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shouhuodizhi))){return "请填写隶属公司";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.xiangxidizhi))){return "请填写详细地址";}
        return "";
    }

    //修改收货地址
    private void updateShippingAddressHTTP() {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("id",person.getId()+"");
        params1.addQueryStringParameter("consignee",shoujianrenxingming.getText().toString());
        params1.addQueryStringParameter("phoneNumber", shoujihao.getText().toString());
        params1.addQueryStringParameter("shippingAddress", shouhuodizhi.getText().toString());
        params1.addQueryStringParameter("detailedAddress", xiangxidizhi.getText().toString());

        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "updateShippingAddress.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SEditors2Activity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);

                        DialogUtil.finish();
                    }

                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS, responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        Bean xiugai = gson.fromJson(result, Bean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = xiugai.getState();
                        switch (state) {
                            case "200":
                                ToastUtil.show(SEditors2Activity.this,"地址修改成功");
                                Intent intent = new Intent();
                                intent.setClass(SEditors2Activity.this,SEditorsActivity.class);
                                intent.putExtra("dingDan", (Serializable) commodity);
                                startActivity(intent);
                                finish();
                                break;
                            case "210":
                                break;
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(SEditors2Activity.this,CommonData.REQUEST_EXCEOTON);
                    }
                });
    }
}
