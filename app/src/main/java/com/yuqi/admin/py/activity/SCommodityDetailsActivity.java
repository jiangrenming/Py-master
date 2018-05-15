package com.yuqi.admin.py.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.VideoView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.bean.APPCommodityDetailBean;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.bean.APPqueryCommodityBean;
import com.yuqi.admin.py.bean.DingDanBean;
import com.yuqi.admin.py.bean.getShoppingtrolleyBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ImageUtil;
import com.yuqi.admin.py.utils.JZVideoPlayerSplash;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.ResizableImageView;
import com.yuqi.admin.py.view.RoundCornerButton;
import com.yuqi.admin.py.view.banner.CustomBanner;
import com.yuqi.admin.py.view.lib.BaseViewPager1;
import com.yuqi.admin.py.view.lib.CycleViewPager1;
import com.yuqi.admin.py.view.lib.utils.ViewFactory;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import cn.jzvd.JZMediaManager;
import cn.jzvd.JZVideoPlayer;
import cn.jzvd.JZVideoPlayerManager;
import cn.jzvd.JZVideoPlayerStandard;

/**
 * Created by Administrator on 2017/12/14.
 * 商品详情
 */
public class SCommodityDetailsActivity extends BaseActivity implements View.OnClickListener,ViewPager.OnPageChangeListener {
    private TextView spxq_yuexiao,spxq_kuaidi,spxq_jiage,spxq_miaoshu,submit;
    private EditText spxq_shuliang;
    Intent intent;
    /**轮播图片*/
    private List<View> views = new ArrayList<View>();
    private List<APPCommodityDetailBean.DataBean.CommoditypicturesBean> infos = new ArrayList<APPCommodityDetailBean.DataBean.CommoditypicturesBean>();
    private CycleViewPager1 cycleViewPager;
    private List<Object> objBean = new ArrayList<Object>();
    private Context mContext;
    private LinearLayout container;
    //商品详情返回数据存储
    APPCommodityDetailBean queryCommodityBean;
    private List<getShoppingtrolleyBean.ObjectBean> dingDan;
    private int orderNumber = 1;

    private SensorManager sensorManager;
    private JZVideoPlayer.JZAutoFullscreenListener sensorEventListener;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.s_spxq_shangpinxiangqing);
        mContext = this;
        init();
        //获取首页传来的图片 商品id
        Intent intent1 = getIntent();
        Bundle bundle = intent1.getExtras();
        String Commodity_id1 = bundle.getString("Commodity_id");
        String userId = bundle.getString("user_id");

        int Commodity_id = 0;
        int user_id = 0;
        try{
            Commodity_id = Integer.parseInt(Commodity_id1);
            user_id = Integer.parseInt(userId);
        }catch (NumberFormatException e) {
            e.printStackTrace();
        }
        sensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
        sensorEventListener = new JZVideoPlayer.JZAutoFullscreenListener();
        //商品详情
        APPqueryCommodityHttp(Commodity_id,user_id);
    }

    private LinearLayout ll_detail_collection;
    private ImageView iv_detail_collection;
    private TextView tv_detail_collection;
    private RelativeLayout rl_detail_top;
    private void init() {
        container = (LinearLayout) findViewById(R.id.ll_showpictrue_container);
        spxq_miaoshu = (TextView)findViewById(R.id.spxq_miaoshu);
        spxq_jiage = (TextView)findViewById(R.id.spxq_jiage);
        spxq_kuaidi = (TextView)findViewById(R.id.spxq_kuaidi);
        spxq_yuexiao = (TextView)findViewById(R.id.spxq_yuexiao);
        spxq_shuliang = (EditText)findViewById(R.id.spxq_shuliang);
        rl_detail_top = (RelativeLayout) findViewById(R.id.rl_detail_top);

        submit= (TextView)findViewById(R.id.submit);
        submit.setVisibility(View.VISIBLE);
        submit.setText("购物车");

        ll_detail_collection = (LinearLayout) findViewById(R.id.ll_detail_collection);
        iv_detail_collection = (ImageView) findViewById(R.id.iv_detail_collection);
        tv_detail_collection = (TextView) findViewById(R.id.tv_detail_collection);

        images = new ArrayList<>();


    }
    private ArrayList<String> images;



    @Override
    public String title_text() {
        return "商品详情";
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.Jrgwc://添加购物车
                int user_id = CommonData.user_id;
                int commodity_id = queryCommodityBean.getData().getCommodity().getId();
                if (spxq_shuliang.getText().toString().trim().equals("")){
                    orderNumber = 1;
                }else {
                    orderNumber = Integer.parseInt(spxq_shuliang.getText().toString().trim());
                }
                addShoppingtrolleyHttp(user_id,commodity_id,orderNumber);
//                ObserverManager manager = ObserverManager.getInstance();
//                manager.addGoods(commodity_id,orderNumber);//添加购物车
                break;
            case R.id.Ljgm://立即购买
                intent = new Intent(SCommodityDetailsActivity.this,SConfirmationSingleActivity.class);
                int commodity_id1 = queryCommodityBean.getData().getCommodity().getId();
                if (spxq_shuliang.getText().toString().trim().equals("")){//购买数量
                    orderNumber = 1;
                }else {
                    orderNumber = Integer.parseInt(spxq_shuliang.getText().toString().trim());
                }
                dingDan = new ArrayList<getShoppingtrolleyBean.ObjectBean>();
                for (int i = 0; i <1; i++) {
                    getShoppingtrolleyBean.ObjectBean info = new getShoppingtrolleyBean.ObjectBean();
                    info.setCommodity_id(commodity_id1);
                    info.setCommodityNumber(orderNumber);
                    info.setPicture(queryCommodityBean.getData().getCommoditypictures().get(0).getPicture());
//                    info.setSales(queryCommodityBean.getObject().getCommodity().getSales());
//                    info.setExpress(queryCommodityBean.getObject().getCommodity().getExpress()+"");
                    info.setCommodityPrice(queryCommodityBean.getData().getCommodity().getCommodityPrice());
                    info.setCommodityName(queryCommodityBean.getData().getCommodity().getCommodityName());
                    dingDan.add(info);
                }

                intent.putExtra("dingDan", (Serializable) dingDan);
                startActivity(intent);
                break;

            case R.id.submit://跳购物车界面
                intent = new Intent(SCommodityDetailsActivity.this,SShoppingCartActivity.class);
                startActivity(intent);
                break;

            case R.id.spxq_jian://商品数据减
                int jian = Integer.parseInt(spxq_shuliang.getText().toString());
                if (jian > 1 ){
                     jian = Integer.parseInt(spxq_shuliang.getText().toString()) - 1;
                    spxq_shuliang.setText(jian+"");
                }else if (spxq_shuliang.getText().toString().length()==0){
                    spxq_shuliang.setText(1+"");
                }
                break;
            case R.id.spxq_jia://商品数据加
                int jia = Integer.parseInt(spxq_shuliang.getText().toString()) + 1;
                if (jia == 9999)return;
                spxq_shuliang.setText(jia+"");
                break;
            case R.id.ll_detail_collection://商品收藏与取消
              if(!ifSign.equals("0")){
                  int userIdDel = CommonData.user_id;
                  int commodityIdDel = queryCommodityBean.getData().getCommodity().getId();
                  deleteCollectionHttp(userIdDel,commodityIdDel);
              }else{
                  int userIdAdd = CommonData.user_id;
                  int commodityIdAdd = queryCommodityBean.getData().getCommodity().getId();
                  addCollectionHttp(userIdAdd,commodityIdAdd);
              }
                break;
            case R.id.banner_tv_video://视频
                banner_btn_pic.setEnabled(true);
                banner_btn_video.setEnabled(false);
                cycleViewPager.getViewPager().setCurrentItem(1);
                Log.i("点击是否有效","是video");
                break;
            case R.id.banner_tv_pic://图片
                banner_btn_pic.setEnabled(false);
                banner_btn_video.setEnabled(true);
                cycleViewPager.getViewPager().setCurrentItem(2);
                if (JZVideoPlayerManager.getCurrentJzvd() != null) {
                    JZVideoPlayer jzvd = JZVideoPlayerManager.getCurrentJzvd();
                    if (jzvd.currentState == JZVideoPlayer.CURRENT_STATE_AUTO_COMPLETE ||
                            jzvd.currentState == JZVideoPlayer.CURRENT_STATE_NORMAL ||
                            jzvd.currentState == JZVideoPlayer.CURRENT_STATE_ERROR) {
//                JZVideoPlayer.releaseAllVideos();
                    } else {
                        jzvd.onStatePause();
                        JZMediaManager.pause();
                    }
                }

                Log.i("点击是否有效","是pic");
                break;
        }
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {

    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void deleteCollectionHttp(int user_id,int commodity_id) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", user_id+"");
        params1.addQueryStringParameter("commodity_id", commodity_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "deleteCommoditycollect.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SCommodityDetailsActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        ToastUtil.show(SCommodityDetailsActivity.this,"收藏取消成功");
                        ifSign = "0";
                        isSign();
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }

    private void addCollectionHttp(int user_id,int commodity_id) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", user_id+"");
        params1.addQueryStringParameter("commodity_id", commodity_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "addCommoditycollect.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SCommodityDetailsActivity.this);
                    }

                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        ToastUtil.show(SCommodityDetailsActivity.this,"收藏成功");
                        Log.e("请求数据=", responseInfo.result);//1,收藏了 0没有收藏
                        ifSign = "1";
                        isSign();
                    }
                    @Override
                    public void onFailure(HttpException e, String s) {

                    }
                });
    }
    //添加购物车接口
    private void addShoppingtrolleyHttp(int user_id, int commodity_id, int commodityNumber) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("user_id", user_id+"");
        params1.addQueryStringParameter("commodity_id", commodity_id+"");
        params1.addQueryStringParameter("commodityNumber", commodityNumber+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        Log.e("请求数据=", user_id+"、"+commodity_id+"、"+commodityNumber+"、");
        http.send(HttpRequest.HttpMethod.GET,
                CommonData.URL + "addShoppingtrolley.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SCommodityDetailsActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e("添加购物车=", responseInfo.result);

                        String state = queryCommodityBean.getStatus();
                        switch (state) {
                            case "200":
                                ToastUtil.show(SCommodityDetailsActivity.this,"添加成功");
                                 break;
                            case "210":
                                ToastUtil.show(SCommodityDetailsActivity.this,"添加失败");
                                break;
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
//                        ToastUtil.show(SCommodityDetailsActivity.this,"网络异常");
                    }
                });
    }

    // 12.22 详细介绍图片设置    底部根据图片张数加载
    private void initImag( APPCommodityDetailBean queryCommodityBean) {
        List<APPCommodityDetailBean.DataBean.CommodityparticularsBean> bean = queryCommodityBean.getData().getCommodityparticulars();
        for(APPCommodityDetailBean.DataBean.CommodityparticularsBean bean1 : bean) {
            ResizableImageView imageView = new ResizableImageView(mContext);
//            BitmapUtils bitmapUtils = new BitmapUtils(mContext);
//            bitmapUtils.display(imageView,bean1.getPicture());
            ImageUtil.loadImg(imageView,bean1.getPicture());
            container.addView(imageView);
        }
    }

    private void isSign(){
        if(ifSign.equals("1")){
            iv_detail_collection.setImageResource(R.drawable.collect_click);
            tv_detail_collection.setText("取消收藏");
            tv_detail_collection.setTextColor(Color.parseColor("#EAB219"));
            ll_detail_collection.setVisibility(View.VISIBLE);

        }else if(ifSign.equals("0")){
            iv_detail_collection.setImageResource(R.drawable.collect_normal);
            tv_detail_collection.setText("收藏");
            tv_detail_collection.setTextColor(Color.parseColor("#C5C5C5"));
            ll_detail_collection.setVisibility(View.VISIBLE);
        }
    }
    private String state;
    private String ifSign;
    //商品详情数据请求
    private void APPqueryCommodityHttp(int Commodity_id ,int user_id) {
        RequestParams params1 = new RequestParams();
        params1.addQueryStringParameter("commodity_id", Commodity_id+"");
        params1.addQueryStringParameter("user_id", user_id+"");
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL + "APPqueryCommodity.action",
                params1,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(SCommodityDetailsActivity.this);
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
                        queryCommodityBean = gson.fromJson(result, APPCommodityDetailBean.class);//存商品数据
                        state = queryCommodityBean.getStatus();
                        ifSign = queryCommodityBean.getIfSign();//1,收藏了 0没有收藏
                        switch (state) {
                            case "200":
                                spxq_yuexiao.setText(queryCommodityBean.getData().getCommodity().getSales()+"");
                                spxq_kuaidi.setText("￥"+queryCommodityBean.getData().getCommodity().getExpress()+"");
                                spxq_jiage.setText("￥"+queryCommodityBean.getData().getCommodity().getCommodityPrice()+"");
                                spxq_miaoshu.setText(queryCommodityBean.getData().getCommodity().getCommodityName()+"");
                                rl_detail_top.setVisibility(View.VISIBLE);
                                isSign();//判断收藏与否更新UI
                                //轮播图片设置
                                configImageLoader();
                                initialize(queryCommodityBean);
                                //详细介绍图片设置
                                initImag(queryCommodityBean);
                                break;
                            case "210":
                                break;
                        }
                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                    }
                });
    }
    //配置ImageLoder
    private void configImageLoader() {
        // 初始化ImageLoader
        @SuppressWarnings("deprecation")
        DisplayImageOptions options = new DisplayImageOptions.Builder().showStubImage(R.mipmap.icon_stub) // 设置图片下载期间显示的图片
                .showImageForEmptyUri(R.mipmap.icon_empty) // 设置图片Uri为空或是错误的时候显示的图片
                .showImageOnFail(R.mipmap.icon_error) // 设置图片加载或解码过程中发生错误显示的图片
                .cacheInMemory(true) // 设置下载的图片是否缓存在内存中
                .cacheOnDisc(true) // 设置下载的图片是否缓存在SD卡中
                // .displayer(new RoundedBitmapDisplayer(20)) // 设置成圆角图片
                .build(); // 创建配置过得DisplayImageOption对象

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(SCommodityDetailsActivity.this).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }

    @SuppressLint("NewApi")
    private void initialize( APPCommodityDetailBean queryCommodityBean) {
        cycleViewPager = (CycleViewPager1) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content1);
        cycleViewPager.getViewPager().setOnPageChangeListener(this);
        APPCommodityDetailBean.DataBean.CommoditypicturesBean info;
        for(int i = 0; i <  queryCommodityBean.getData().getCommoditypictures().size(); i ++) {
            info = new  APPCommodityDetailBean.DataBean.CommoditypicturesBean();
            info.setPicture(queryCommodityBean.getData().getCommoditypictures().get(i).getPicture());
////            switch (i) {
////                case 0:
////                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php");
////                    break;
////                case 1:
////                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php/Home/Index/cpy_des/sort/1.html");
////                    break;
////                case 2:
////                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php/Home/Index/blueprint/sort/3.html");
////                    break;
////            }
//            info.getPicture();
            infos.add(info);

        }

        if(!queryCommodityBean.getData().getMsg().equals("0")){
            images.add(0,queryCommodityBean.getData().getMsg());
        }

        for(APPCommodityDetailBean.DataBean.CommoditypicturesBean bean:infos){
            images.add(bean.getPicture());
        }
        // 将最后一个View添加进来
        views.add(ViewFactory.getImageView(SCommodityDetailsActivity.this, images.get(images.size()-1)));

        for (int i = 0; i < images.size(); i++) {
            if(i==0){
                if(images.get(0).contains("mp4")){
                    views.add(getVideoView(SCommodityDetailsActivity.this, images.get(0)));
                }else{
                        views.add(ViewFactory.getImageView(SCommodityDetailsActivity.this, images.get(0)));
                }
            }else{
                views.add(ViewFactory.getImageView(SCommodityDetailsActivity.this, images.get(i)));
            }
        }

        // 将第一个View添加进来
        if(images.get(0).contains("mp4")){
            views.add(getVideoView(SCommodityDetailsActivity.this, images.get(0)));
        }else{
            views.add(ViewFactory.getImageView(SCommodityDetailsActivity.this, images.get(0)));
        }

        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);
        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, images, mAdCycleViewListener);
        //设置轮播
        if(images.get(0).contains("mp4")){
            cycleViewPager.setWheel(false);
        }else{
            cycleViewPager.setWheel(true);
        }
        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(3000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }

    private CycleViewPager1.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager1.ImageCycleViewListener() {
        @Override
        public void onImageClick(APPHomePageBean.ObjectBean.PicturesBean info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
//                position = position - 1;
//                Intent it = new Intent(SCommodityDetailsActivity.this,WebActivity.class);
//                it.putExtra("url",info.getFirst());
//                startActivity(it);
            }
        }
    };

    private RelativeLayout layoutView;
    private JZVideoPlayerStandard videoView;
    private RoundCornerButton banner_btn_video;
    private RoundCornerButton banner_btn_pic;

    public RelativeLayout getVideoView(Context context, String url) {
        layoutView = (RelativeLayout)LayoutInflater.from(context).inflate(
                R.layout.videoview_banner, null);
        videoView = (JZVideoPlayerStandard) layoutView.findViewById(R.id.video_banner_jz);
        banner_btn_video = (RoundCornerButton) layoutView.findViewById(R.id.banner_tv_video);
        banner_btn_pic = (RoundCornerButton) layoutView.findViewById(R.id.banner_tv_pic);
        banner_btn_video.setOnClickListener(this);
        banner_btn_pic.setOnClickListener(this);
        videoView.setUp(url, JZVideoPlayerStandard.SCREEN_WINDOW_NORMAL, "");
        videoView.thumbImageView.setScaleType(ImageView.ScaleType.FIT_CENTER);
        Glide.with(context).load(images.get(1)).placeholder(R.mipmap.icon_stub).error(R.mipmap.icon_error).into(videoView.thumbImageView);
        return layoutView;
    }

    @Override
    public void onBackPressed() {
        if (JZVideoPlayerStandard.backPress()) {
            return;
        }
        super.onBackPressed();
    }
    @Override
    protected void onPause() {
        super.onPause();
        JZVideoPlayerStandard.releaseAllVideos();
        sensorManager.unregisterListener(sensorEventListener);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Sensor accelerometerSensor = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        sensorManager.registerListener(sensorEventListener, accelerometerSensor, SensorManager.SENSOR_DELAY_NORMAL);
    }

}
