package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.lljjcoder.Interface.OnCityItemClickListener;
import com.lljjcoder.bean.CityBean;
import com.lljjcoder.bean.DistrictBean;
import com.lljjcoder.bean.ProvinceBean;
import com.lljjcoder.citywheel.CityConfig;
import com.lljjcoder.style.citylist.Toast.ToastUtils;
import com.lljjcoder.style.citypickerview.CityPickerView;
import com.lljjcoder.style.citythreelist.ProvinceActivity;
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
 * 添加收货地址
 */
public class SEditors1Activity extends BaseActivity{
    private EditText shoujianrenxingming,shoujihao,shouhuodizhi,xiangxidizhi;
    private ImageView moren;
    private TextView submit;
    private boolean moRen =true;
    private int moR =1;
    private List<DingDanBean.DingdanBean> commodity;
    private LinearLayout ll_area;
    private TextView edt_area;
    private String area;
    //申明对象
    CityPickerView mPicker=new CityPickerView();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_spdd_dizhixinxixiugai);
        /**
         * 预先加载仿iOS滚轮实现的全部数据
         */
        mPicker.init(this);
        commodity = (List<DingDanBean.DingdanBean>) getIntent().getSerializableExtra("dingDan");
        init();

    }

    private void init() {
        shoujianrenxingming = (EditText)findViewById(R.id.shoujianrenxingming);
        shoujihao = (EditText)findViewById(R.id.shoujihao);
        shouhuodizhi = (EditText)findViewById(R.id.shouhuodizhi);
        xiangxidizhi = (EditText)findViewById(R.id.xiangxidizhi);
        moren = (ImageView) findViewById(R.id.moren);
        ll_area = (LinearLayout) findViewById(R.id.ll_area);
        edt_area = (TextView) findViewById(R.id.edt_area);
        submit = (TextView)findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setText("完成");



    }

    @Override
    public String title_text() {
        return "添加收货地址";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.submit:
                Log.e("请求=",area);

                String mResult = isContentEmpty();
                if (!StringUtil.isEmpty(mResult)){
                    ToastUtil.show(this,mResult);
                }else {
                    addShippingAddressHTTP();
                }
                break;

            case R.id.moren:
                    if (moRen){
                        moren.setImageResource(R.mipmap.moren);
                        moR = 1;
                        moRen = true;
                    }else {
                        moren.setImageResource(R.mipmap.moren1);
                        moR = 0;
                        moRen = false;
                    }
                break;
            case R.id.ll_area:
                //添加默认的配置，不需要自己定义
                CityConfig cityConfig = new CityConfig.Builder().build();
                mPicker.setConfig(cityConfig);


                //监听选择点击事件及返回结果
                mPicker.setOnCityItemClickListener(new OnCityItemClickListener(){

                @Override
                public void onSelected(ProvinceBean province, CityBean city, DistrictBean district) {
                    if(province != null&city != null&district!= null){
                       area = province.getName()+city.getName()+district.getName();
                        edt_area.setText(area);
                    }
            }

            @Override
            public void onCancel() {
                ToastUtils.showLongToast(SEditors1Activity.this, "已取消");
            }
        }

                );
                //显示
                mPicker.showCityPicker( );

        }
    }

    private String isContentEmpty(){
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shoujianrenxingming))){return "请填写收件人";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shoujihao))){return "请填写手机号";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.shouhuodizhi))){return "请填写隶属公司";}
        if(StringUtil.isEmpty(StringUtil.getText(this, R.id.xiangxidizhi))){return "请填写详细地址";}
        if(area.equals("")){return "请选择所在地区";}
        return "";
    }

    private void addShippingAddressHTTP() {

//        {"id":278,"user_id":207,"consignee":"羽琪","phoneNumber":"15012790742","shippingAddress":"测试","detailedAddress":"南翔","ifDefaultAddress":1,"company":null}],"state":"200"}

        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", CommonData.user_id+"");
        params1.addQueryStringParameter("consignee", shoujianrenxingming.getText().toString());
        params1.addQueryStringParameter("phoneNumber", shoujihao.getText().toString());
        params1.addQueryStringParameter("company", shouhuodizhi.getText().toString());
        params1.addQueryStringParameter("detailedAddress", xiangxidizhi.getText().toString());
        params1.addQueryStringParameter("ifDefaultAddress", moR+"" );
        params1.addQueryStringParameter("shippingAddress", area);

        Log.e("请求=",area+shoujianrenxingming.getText().toString()+shouhuodizhi.getText().toString()+xiangxidizhi.getText().toString());
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "addShippingAddress.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SEditors1Activity.this);
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
                        String result = responseInfo.result;
                        Gson gson = new Gson();//初始化
                        Bean xiugai = gson.fromJson(result, Bean.class);//result为请求后返回的JSON数据,可以直接使用XUtils获得,NewsData.class为一个bean.如以下数据：

                        String state = xiugai.getState();
                        switch (state) {
                            case "200":
                                ToastUtil.show(SEditors1Activity.this, "地址添加成功");
                                Intent intent = new Intent();
                                intent.setClass(SEditors1Activity.this,SEditorsActivity.class);
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
                        ToastUtil.show(SEditors1Activity.this, CommonData.REQUEST_EXCEOTON);
                    }
                });

    }
}
