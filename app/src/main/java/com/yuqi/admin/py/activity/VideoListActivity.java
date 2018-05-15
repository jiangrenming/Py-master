package com.yuqi.admin.py.activity;

import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.AbsListView;
import android.widget.ImageView;
import android.widget.ListView;
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
import com.yuqi.admin.py.adapter.ShangPinAdapter;
import com.yuqi.admin.py.adapter.ShangPinzhAdapter;
import com.yuqi.admin.py.adapter.VideoListAdapter;
import com.yuqi.admin.py.bean.APPCommodityBean;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.bean.VideoBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.MultiGridView;

import cn.jzvd.JZVideoPlayer;

public class VideoListActivity extends BaseActivity {
    private ListView listView;
    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener sensorEventListener;
    private VideoBean videoBean;
    private VideoListAdapter videoAdpter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listview_video);
        title = (TextView)findViewById(R.id.title);
        title.setText("企业视频");

        initVideoList();
        listView = (ListView) findViewById(R.id.video_listview);
        listView.setOnScrollListener(new AbsListView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(AbsListView view, int scrollState) {

            }

            @Override
            public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
                JZVideoPlayer.onScrollReleaseAllVideos(view, firstVisibleItem, visibleItemCount, totalItemCount);
            }
        });

        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JZVideoPlayer.JZAutoFullscreenListener();

    }
    @Override
    public void onBackPressed() {
        if (JZVideoPlayer.backPress()) {
            return;
        }
        super.onBackPressed();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListener);
        JZVideoPlayer.releaseAllVideos();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                break;
        }
        return super.onOptionsItemSelected(item);
    }
    private void commodityBean( VideoBean videoBean) {
        videoAdpter = new VideoListAdapter(VideoListActivity.this ,videoBean);
        listView.setAdapter(videoAdpter);
        videoAdpter.notifyDataSetInvalidated();
    }
    private void initVideoList(){
    RequestParams params = new RequestParams();
    HttpUtils http = new HttpUtils();
    http.configCurrentHttpCacheExpiry(1000*10);
    Log.e(CommonData.REQUEST_PARAMETER, "VideoList");
    http.send(HttpRequest.HttpMethod.GET,
            CommonData.URL + "VideoPlayer.action",
            params,
            new RequestCallBack<String>() {
                @Override
                public void onStart() {
                    super.onStart();
                    DialogUtil.start(VideoListActivity.this);
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
                    videoBean = gson.fromJson(result, VideoBean.class);//存视频信息
                    String status = videoBean.getStatus();
                    switch (status) {
                        case "200":
                            commodityBean(videoBean);
                            listView.setVisibility(View.VISIBLE);
                            break;
                        case "210":
                            ToastUtil.show(VideoListActivity.this,"网络错误");
                            break;
                    }

                }

                @Override
                public void onFailure(HttpException error, String msg) {
                    DialogUtil.finish();
                }
            });
}

    @Override
    public String title_text() {
        return null;
    }

    @Override
    public void onClick(View v) {

    }
}
