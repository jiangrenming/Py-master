package com.yuqi.admin.py.activity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.lidroid.xutils.BitmapUtils;
import com.lidroid.xutils.HttpUtils;
import com.lidroid.xutils.exception.HttpException;
import com.lidroid.xutils.http.RequestParams;
import com.lidroid.xutils.http.ResponseInfo;
import com.lidroid.xutils.http.callback.RequestCallBack;
import com.lidroid.xutils.http.client.HttpRequest;
import com.loveplusplus.update.UpdateChecker;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.yuqi.admin.py.BaseActivity;
import com.yuqi.admin.py.ObserverManager;
import com.yuqi.admin.py.R;
import com.yuqi.admin.py.adapter.ShangPinAdapter;
import com.yuqi.admin.py.adapter.ShangPinzhAdapter;
import com.yuqi.admin.py.bean.APPHomePageBean;
import com.yuqi.admin.py.bean.APPCommodityBean;
import com.yuqi.admin.py.bean.ShoppingCarBean;
import com.yuqi.admin.py.data.CommonData;
import com.yuqi.admin.py.interfaces.ObserverListener;
import com.yuqi.admin.py.utils.ClickUtils;
import com.yuqi.admin.py.utils.DialogUtil;
import com.yuqi.admin.py.utils.ToastUtil;
import com.yuqi.admin.py.view.MultiGridView;
import com.yuqi.admin.py.view.MyScrollView;
import com.yuqi.admin.py.view.ResizableImageView;
import com.yuqi.admin.py.view.lib.CycleViewPager;
import com.yuqi.admin.py.view.lib.utils.ViewFactory;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2017/11/23.
 *      首页
 */
public class ShouyActivity extends BaseActivity implements View.OnClickListener,ObserverListener{
    private ImageView bt_shouye,back;
    private TextView title,cg_xinxi,cg_shijian,cg_nicheng,bt_shouye1;
    private LinearLayout huodongshangpin;
    private LinearLayout videoshow_sy;
    private Context mContext;
    Intent intent;

    /**轮播图片*/
    private List<ImageView> views = new ArrayList<ImageView>();
    private List<APPHomePageBean.ObjectBean.PicturesBean> infos = new ArrayList<APPHomePageBean.ObjectBean.PicturesBean>();
    private CycleViewPager cycleViewPager;

    /**综合、手机、洗护、箱包、食品、家用、视频*/
    private TextView sy_zh2,sy_dq2,sy_sj2,sy_xh2,sy_xb2,sy_sp2,sy_jy2,famliy2;

    private ShangPinAdapter spAdapter;
    private ShangPinzhAdapter spZhAdapter;
    private MultiGridView sjLv;
    private String commodity = "综合";

    private int user_id;
    private LinearLayout ll_main_top;
    private LinearLayout ll_main_bottom;
    APPHomePageBean homePage;//第一次首页返回数据存储
    APPCommodityBean commodityBean;//首页不同的类型返回数据存储
    @Override
    protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layou_shouye);
            mContext = this;
            init();
        Log.e("ShouyActivity","onCreate");
            //首页第一次请求
            APPHomePageHttp();
//        if (UPDATECHECK = false) {

//            UPDATECHECK = true;
//        }
//            /**初始化购物车数据 */
//            ObserverManager manager = ObserverManager.getInstance();
//            /** 请求到购物车数据病转化成为ShoppingCarBean类*/
//            ShoppingCarBean bean = new ShoppingCarBean();
//            manager.setEntity(bean,this);
        UpdateChecker.checkForDialog(this, CommonData.APP_UPDATE_SERVER_URL);
//        Intent intent = getIntent();
//        Bundle bundle = intent.getExtras();
//        user_id = bundle.getInt("user_id");
//        intent.getExtras();

            user_id = CommonData.user_id;

    }

    @Override
    protected void onStart() {
        super.onStart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onPause() {
        super.onPause();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onStop() {
        super.onStop();
        overridePendingTransition(0,0);
    }

    @Override
    protected void onResume() {
        super.onResume();
        overridePendingTransition(0,0);
    }

    private ImageView zhongzi,famliy_categry,dazuihou_categry,life_categry;
    private MyScrollView scrollView;
    private  LinearLayout products;
    /**控件初始化区域*/
    private void init() {
        bt_shouye1 = (TextView) this.findViewById(R.id.bt_shouye1);
        bt_shouye = (ImageView) this.findViewById(R.id.bt_shouye);
        back = (ImageView) findViewById(R.id.back);
        title = (TextView)this.findViewById(R.id.title);
        sjLv = (MultiGridView) findViewById(R.id.list);
        famliy2 = (TextView) findViewById(R.id.famliy2);
        zhongzi = (ImageView) findViewById(R.id.zhongzi);
        famliy_categry = (ImageView) findViewById(R.id.famliy_categry);
        dazuihou_categry = (ImageView) findViewById(R.id.dazuihou_categry);
        life_categry = (ImageView) findViewById(R.id.life_categry);
        zhongzi.setOnClickListener(this);
        famliy_categry.setOnClickListener(this);
        dazuihou_categry.setOnClickListener(this);
        life_categry.setOnClickListener(this);
        products = (LinearLayout)findViewById(R.id.products);
        scrollView = (MyScrollView)findViewById(R.id.videoScrollView);
        huodongshangpin = (LinearLayout)findViewById(R.id.huodongshangpin);

        sy_zh2 = (TextView)findViewById(R.id.sy_zh2);
        sy_dq2 = (TextView)findViewById(R.id.sy_dq2);
        sy_sj2 = (TextView)findViewById(R.id.sy_sj2);
        sy_xh2 = (TextView)findViewById(R.id.sy_xh2);
        sy_xb2 = (TextView)findViewById(R.id.sy_xb2);
        sy_sp2 = (TextView)findViewById(R.id.sy_sp2);
        sy_jy2 = (TextView)findViewById(R.id.sy_jy2);
        videoshow_sy = (LinearLayout) findViewById(R.id.videoshow_sy);
        title.setText("彭友聚汇");
        back.setVisibility(View.INVISIBLE);
        bt_shouye.setImageResource(R.mipmap.b1);
        bt_shouye1.setTextColor(Color.parseColor("#f4a100"));

        ll_main_top = (LinearLayout) findViewById(R.id.ll_main_top);
        ll_main_bottom = (LinearLayout) findViewById(R.id.ll_main_bottom);
        ll_main_bottom.setVisibility(View.INVISIBLE);
    }

    /**首页第一次请求*/
    private void APPHomePageHttp() {
        RequestParams params = new RequestParams();
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000*10);

        http.send(HttpRequest.HttpMethod.POST,
                CommonData.URL+"APPHomePage.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(ShouyActivity.this);
                    }
                    @Override
                    public void onLoading(long total, long current, boolean isUploading) {
                        super.onLoading(total, current, isUploading);
                        DialogUtil.finish();
                    }
                    @Override
                    public void onSuccess(ResponseInfo<String> responseInfo) {
                        DialogUtil.finish();
                        Log.e(CommonData.REQUEST_SUCCESS+"-----------", responseInfo.result);
                        String  result =  responseInfo.result;
                        Gson gson = new Gson();//初始化
                        homePage = gson.fromJson(result, APPHomePageBean.class);
                        List<APPHomePageBean.ObjectBean.CommoditytypesBean> commoditytypes = homePage.getObject().getCommoditytypes();
                        String state = homePage.getState();
                        switch (state){
                            case "200":
                                //采购需求
                                demands(homePage);
                                //活动商品
                                initImag(homePage);
                                //轮播图片设置
                                configImageLoader();
                                initialize(homePage);
                                //商品展示
                                homePage(homePage);
                                ll_main_top.setVisibility(View.VISIBLE);
                                ll_main_bottom.setVisibility(View.VISIBLE);
                                break;
                            case "210":

                                break;
                        }


                    }
                    @Override
                    public void onFailure(HttpException error, String msg) {
                        DialogUtil.finish();
                        ToastUtil.show(ShouyActivity.this,"网络异常");
                    }
                });
    }

    //首页活动商品
    private void initImag( APPHomePageBean homePage) {
        List<APPHomePageBean.ObjectBean.ActivitycommoditiesBean> bean = homePage.getObject().getActivitycommodities();

        TextView hd_neirong,hd_jiage;
        for(APPHomePageBean.ObjectBean.ActivitycommoditiesBean bean1 : bean) {
            View v = View.inflate(mContext, R.layout.item_shangpinhuodong, null);
            LinearLayout hd_ll = (LinearLayout)v.findViewById(R.id.hd_ll);
            hd_neirong = (TextView) v.findViewById(R.id.hd_neirong);
            ResizableImageView hd_tupian = (ResizableImageView) v.findViewById(R.id.hd_tupian);
            hd_jiage = (TextView) v.findViewById(R.id.hd_jiage);
            hd_neirong.setText(bean1.getCommodityName());
            hd_jiage.setText("￥"+bean1.getCommodityPrice());
            final String type = bean1.getId()+"";

            BitmapUtils bitmapUtils = new BitmapUtils(mContext);
            bitmapUtils.display(hd_tupian,bean1.getPicture());
            huodongshangpin.addView(v);

            hd_ll.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, SCommodityDetailsActivity.class);
                    Bundle bundle = new Bundle();
                    intent.putExtra("Commodity_id", type);
                    intent.putExtra("user_id", user_id+"");
                    intent.putExtras(bundle);
                    mContext.startActivity(intent);
                }
            });
        }


    }
    //首页综合展示商品
    private void homePage( APPHomePageBean homePage) {
        spAdapter = new ShangPinAdapter(ShouyActivity.this ,homePage);
        sjLv.setAdapter(spAdapter);
        spAdapter.notifyDataSetInvalidated();
        //list转换为json
        Gson gson = new Gson();
        List<APPHomePageBean.ObjectBean.CommoditiesBean> bean = new ArrayList<APPHomePageBean.ObjectBean.CommoditiesBean>();
        bean.addAll(homePage.getObject().getCommodities());
    }
    //展示商品
    private void commodityBean( APPCommodityBean commodityBean) {
        spZhAdapter = new ShangPinzhAdapter(ShouyActivity.this ,commodityBean);
        sjLv.setAdapter(spZhAdapter);
        spAdapter.notifyDataSetInvalidated();
    }

    @SuppressLint("NewApi")
    private void initialize( APPHomePageBean homePage) {
        cycleViewPager = (CycleViewPager) getFragmentManager()
                .findFragmentById(R.id.fragment_cycle_viewpager_content);
        for(int i = 0; i <  homePage.getObject().getPictures().size(); i ++) {
            APPHomePageBean.ObjectBean.PicturesBean info = new APPHomePageBean.ObjectBean.PicturesBean();
            info.setPicture_url(homePage.getObject().getPictures().get(i).getPicture_url());
//            switch (i) {
//                case 0:
//                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php");
//                    break;
//                case 1:
//                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php/Home/Index/cpy_des/sort/1.html");
//                    break;
//                case 2:
//                    info.setPicture_url("http://www.yuqibest.com/yuqibest/index.php/Home/Index/blueprint/sort/3.html");
//                    break;
//            }
            infos.add(info);
        }


        // 将最后一个ImageView添加进来
        views.add(ViewFactory.getImageView(ShouyActivity.this, infos.get(infos.size()-1).getPicture_url()));
        for (int i = 0; i < infos.size(); i++) {
            views.add(ViewFactory.getImageView(ShouyActivity.this, infos.get(i).getPicture_url()));
        }
        // 将第一个ImageView添加进来
        views.add(ViewFactory.getImageView(ShouyActivity.this, infos.get(0).getPicture_url()));
        // 设置循环，在调用setData方法前调用
        cycleViewPager.setCycle(true);
        // 在加载数据前设置是否循环
        cycleViewPager.setData(views, infos, mAdCycleViewListener);
        //设置轮播
        cycleViewPager.setWheel(true);
        // 设置轮播时间，默认5000ms
        cycleViewPager.setTime(2000);
        //设置圆点指示图标组居中显示，默认靠右
        cycleViewPager.setIndicatorCenter();
    }
    private CycleViewPager.ImageCycleViewListener mAdCycleViewListener = new CycleViewPager.ImageCycleViewListener() {

        @Override
        public void onImageClick(APPHomePageBean.ObjectBean.PicturesBean info, int position, View imageView) {
            if (cycleViewPager.isCycle()) {
                position = position - 1;
//                Intent it = new Intent(ShouyActivity.this,WebActivity.class);
//                it.putExtra("url",info.getFirst());
//                startActivity(it);
            }
        }
    };

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

        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(ShouyActivity.this).defaultDisplayImageOptions(options)
                .threadPriority(Thread.NORM_PRIORITY - 2).denyCacheImageMultipleSizesInMemory()
                .discCacheFileNameGenerator(new Md5FileNameGenerator()).tasksProcessingOrder(QueueProcessingType.LIFO).build();
        ImageLoader.getInstance().init(config);
    }
    // 界面标题设置
    @Override
    public String title_text() {
        return null;
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
    }
   // 控件点击事件
    @Override
    public void onClick(View v) {
        if (ClickUtils.isContinuClick())return;
        Intent intent = null;
        switch (v.getId()){
            case R.id.bt_shouye11:
//                intent = new Intent(ShouyActivity.this, WomActivity.class);
//                startActivity(intent);
                break;
            case R.id.bt_yuqi11:
                intent = new Intent(ShouyActivity.this, YuqActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
            case R.id.bt_anli11:
                intent = new Intent(ShouyActivity.this, AnliActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
            case R.id.bt_women11:
                intent = new Intent(ShouyActivity.this, WomActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_REORDER_TO_FRONT);
                overridePendingTransition(0, 0);
                startActivity(intent);
                break;
            case R.id.grzx:
                intent = new Intent(ShouyActivity.this, SPersonalCenterActivity.class);
                startActivity(intent);
                break;
            case R.id.wycg:
                intent = new Intent(ShouyActivity.this, SPurchaseActivity.class);
                startActivity(intent);
                break;
            case R.id.gwc:
                intent = new Intent(ShouyActivity.this, SShoppingCartActivity.class);
                startActivity(intent);
                break;
            case R.id.ll_ss:
                intent = new Intent(ShouyActivity.this, SSearchActivity.class);
                startActivity(intent);
                break;
            case R.id.jfsc:
                intent = new Intent(ShouyActivity.this, SIntegralMallActivity.class);
                startActivity(intent);
                break;
            case R.id.wycz:
                intent = new Intent(ShouyActivity.this, SRechargeActivity.class);
                startActivity(intent);
                break;

            case R.id.videoshow_sy:
                intent = new Intent(ShouyActivity.this, VideoListActivity.class);
                startActivity(intent);
                break;

            case R.id.share_sy:
                intent = new Intent(ShouyActivity.this, ToShareActivity.class);
                Bundle bundle = new Bundle();
                intent.putExtra("user_id",user_id);
                intent.putExtras(bundle);
                startActivity(intent);
                break;


            case R.id.sy_zh1://商品类型
                commodity = "综合";
                sy_zh2.setVisibility(View.VISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.sy_dq1:  //端午
                commodity = homePage.getObject().getCommoditytypes().get(1).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.VISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);

                break;
            case R.id.sy_sj1:  //家纺

                commodity = homePage.getObject().getCommoditytypes().get(3).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.VISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.sy_xh1: //大嘴猴
                commodity =homePage.getObject().getCommoditytypes().get(2).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.VISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.sy_xb1: //艺术
                commodity = homePage.getObject().getCommoditytypes().get(6).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.VISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.sy_sp1:  //食品
                commodity = homePage.getObject().getCommoditytypes().get(4).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.VISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.sy_jy1: //企业

                commodity = homePage.getObject().getCommoditytypes().get(5).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.VISIBLE);
                famliy2.setVisibility(View.INVISIBLE);
                Commodity(commodity);
                break;
            case R.id.famliy1: //家电
                commodity = homePage.getObject().getCommoditytypes().get(0).getTypeName();
                sy_zh2.setVisibility(View.INVISIBLE);
                sy_dq2.setVisibility(View.INVISIBLE);
                sy_sj2.setVisibility(View.INVISIBLE);
                sy_xh2.setVisibility(View.INVISIBLE);
                sy_xb2.setVisibility(View.INVISIBLE);
                sy_sp2.setVisibility(View.INVISIBLE);
                sy_jy2.setVisibility(View.INVISIBLE);
                famliy2.setVisibility(View.VISIBLE);
                Commodity(commodity);
                break;
            case R.id.zhongzi:  //端午
                intent = new Intent(ShouyActivity.this,ProjectCategryActivity.class);
                intent.putExtra("type",3);
                startActivity(intent);
                break;
            case R.id.famliy_categry:  //家纺
                intent = new Intent(ShouyActivity.this,ProjectCategryActivity.class);
                intent.putExtra("type",8);
                startActivity(intent);
                break;
            case  R.id.dazuihou_categry: //大嘴猴
                intent = new Intent(ShouyActivity.this,ProjectCategryActivity.class);
                intent.putExtra("type",6);
                startActivity(intent);
                break;
            case  R.id.life_categry:  //艺术生活
                intent = new Intent(ShouyActivity.this,ProjectCategryActivity.class);
                intent.putExtra("type",9);
                startActivity(intent);
                break;
            default:
                break;
        }
    }

    private void scrollView(){
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.smoothScrollTo(0, y);
            }
        });
    }

    private int y ;
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (hasFocus){
            y = products.getTop();  //顶部距离父容器的Y轴距离
            Log.i("距离2",y+"");
        }
    }

    //采购需求
    private void demands(APPHomePageBean homePage) {
        cg_xinxi = (TextView)findViewById(R.id.cg_xinxi);
        cg_shijian = (TextView)findViewById(R.id.cg_shijian);
        cg_nicheng = (TextView)findViewById(R.id.cg_nicheng);
        cg_xinxi.setText(homePage.getObject().getDemands().get(0).getDemandContext());
        cg_shijian.setText(homePage.getObject().getDemands().get(0).getCreateTime());
        cg_nicheng.setText(homePage.getObject().getDemands().get(0).getName());
    }
    /**商品*/
    private void Commodity(String commodity) {
        RequestParams params = new RequestParams();
        params.addQueryStringParameter("Commodity_Type", commodity);
        HttpUtils http = new HttpUtils();
        http.configCurrentHttpCacheExpiry(1000 * 10);
        Log.e(CommonData.REQUEST_PARAMETER, commodity);

        http.send(HttpRequest.HttpMethod.GET,
                CommonData.URL + "APPqueryCommodityByType.action",
                params,
                new RequestCallBack<String>() {
                    @Override
                    public void onStart() {
                        super.onStart();
                        DialogUtil.start(ShouyActivity.this);
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
                        Log.i("分类item",result);
                        Gson gson = new Gson();//初始化
                        commodityBean = gson.fromJson(result, APPCommodityBean.class);//存商品数据
                        String state = commodityBean.getState();
                        switch (state) {
                            case "200":
//                                sjLv.setVisibility(View.VISIBLE);
                                homePage.getObject().getCommodities().clear();
                                commodityBean(commodityBean);
                                break;
                            case "210":
//                                Tv_wu.setVisibility(View.VISIBLE);
//                                sjLv.setVisibility(View.GONE);
//                                Tv_wu.setText("敬请期待！补货中···");
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
    public void changeGoods() {
        /**
         * 一旦购物车的数据有变化，这个方法将会被调用
         */


    }

    /**处理两次点击手机返回键退出*/
    long backtime = 0;
    @Override
    public void onBackPressed() {
        long clicktime = System.currentTimeMillis();
        if (backtime == 0 || clicktime - backtime > 1500) {
            backtime = clicktime;
            ToastUtil.show(this, "再次点击退出程序");
        } else {
            Intent backHome = new Intent(Intent.ACTION_MAIN);
            backHome.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            backHome.addCategory(Intent.CATEGORY_HOME);
            startActivity(backHome);
        }
    }


}
