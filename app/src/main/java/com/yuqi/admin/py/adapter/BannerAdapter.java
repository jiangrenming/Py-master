//package com.yuqi.admin.py.adapter;
//
///**
// * Created by Administrator on 2018/4/9/009.
// */
//
//import android.content.Context;
//import android.media.MediaPlayer;
//import android.net.Uri;
//import android.support.v4.view.PagerAdapter;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.ImageView;
//import android.widget.VideoView;
//
//import com.bumptech.glide.Glide;
//
//
//import java.util.List;
//
///**
// * 轮播适配器
// */
//
//public class BannerAdapter extends PagerAdapter {
//    private Context mContext;
//    private List<BannerResult> imgUrls;
//    private BannerListener bannerListener;
//
//    public BannerAdapter(Context context) {
//        this.mContext = context;
//    }
//
//    public void update(List<BannerResult> resIds) {
//        if (this.imgUrls != null)
//            this.imgUrls.clear();
//        if (resIds != null)
//            this.imgUrls = resIds;
//    }
//
//    @Override
//    public int getCount() {
//        return imgUrls == null ? 0 : Integer.MAX_VALUE;
//    }
//
//    @Override
//    public boolean isViewFromObject(View view, Object object) {
//        return view == object;
//    }
//
//    @Override
//    public void destroyItem(ViewGroup container, int position, Object object) {
//        container.removeView((View) object);
//    }
//
//    @Override
//    public Object instantiateItem(ViewGroup container, final int position) {
//        BannerResult url = imgUrls.get(position % imgUrls.size());
//        if (url.getType().equals("1")) {
//            ImageView imageView = new ImageView(mContext);
//            imageView.setScaleType(ImageView.ScaleType.FIT_XY);
//            Glide.with(mContext).load(url.getUrl()).into(imageView);
//            container.addView(imageView);
//            imageView.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    bannerListener.bannerClick(position % imgUrls.size());
//                }
//            });
//            return imageView;
//        } else {
//            final VideoView videoView = new VideoView(mContext);
//            Uri uri = Uri.parse(url.getUrl());
//            videoView.setVideoURI(uri);
//            videoView.start();
//            container.addView(videoView);
//            videoView.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                @Override
//                public void onCompletion(MediaPlayer mp) {
//                    bannerListener.playEnd();
//                }
//            });
//            return videoView;
//        }
//    }
//
//    public void setBannerListener(BannerListener bannerListener) {
//        this.bannerListener = bannerListener;
//    }
//
//}