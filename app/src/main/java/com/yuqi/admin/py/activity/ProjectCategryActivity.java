package com.yuqi.admin.py.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.text.SpanWatcher;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.dl7.recycler.helper.RecyclerViewHelper;
import com.dl7.recycler.listener.OnRecyclerViewItemClickListener;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.ProjectAdapter;
import com.yuqi.admin.py.bean.APPCommodityBean;
import com.yuqi.admin.py.bean.ProjectCategryBean;
import com.yuqi.admin.py.data.CommonData;

/**
 *
 * @author jiangrenming
 * @date 2018/5/15
 */

public class ProjectCategryActivity extends BaseActivity implements View.OnClickListener{


    private RecyclerView recycler;
    private TextView top_title_name;
    private ImageView title_back_iv;
    private ProjectAdapter mAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_project);
        recycler = (RecyclerView) findViewById(R.id.recycler);
        top_title_name = (TextView) findViewById(R.id.top_title_name);
        title_back_iv = (ImageView) findViewById(R.id.title_back_iv);
        title_back_iv.setOnClickListener(this);
        int type = getIntent().getIntExtra("type", -1);
        if (type == 3){
            top_title_name.setText("端午粽子专区");
        }else if (type == 6){
            top_title_name.setText("大嘴猴专区");
        }else if (type == 8){
            top_title_name.setText("精品家纺专区");
        }else  if (type == 9){
            top_title_name.setText("艺术生活专区");
        }
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("type_code", String.valueOf(type));
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);
        http.send(HttpRequest.HttpMethod.POST, CommonData.URL + "APPactivityCommodity.action", params, new RequestCallBack<String>() {
            @Override
            public void onSuccess(ResponseInfo<String> responseInfo) {
                Log.i("获取的数据:","获取的数据:"+responseInfo.result);
                if (!TextUtils.isEmpty(responseInfo.result)){
                    Gson gson = new Gson();//初始化
                   final  ProjectCategryBean commodityBean = gson.fromJson(responseInfo.result, ProjectCategryBean.class);//存商品数据
                    if (mAdapter == null){
                        mAdapter = new ProjectAdapter(ProjectCategryActivity.this,commodityBean.getData());
                        RecyclerViewHelper.initRecyclerViewG(ProjectCategryActivity.this,recycler,true,mAdapter,2);
                    }else {
                        mAdapter.cleanItems();
                        mAdapter.addItems(commodityBean.getData());
                    }
                    mAdapter.setOnItemClickListener(new OnRecyclerViewItemClickListener() {
                        @Override
                        public void onItemClick(View view, int position) {
                            Intent intent = new Intent(ProjectCategryActivity.this, SCommodityDetailsActivity.class);
                            Bundle bundle = new Bundle();
                            intent.putExtra("Commodity_id",commodityBean.getData().get(position).getId()+"");
                            intent.putExtra("Picture",commodityBean.getData().get(position).getPicture());
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }
                    });
                }
            }

            @Override
            public void onFailure(HttpException e, String s) {
                Log.i("获取的数据:","获取的数据:"+s);
            }
        });

    }

    @Override
    public String title_text() {
        return null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.title_back_iv:
                finish();
                break;
            default:
                break;

        }
    }
}
