package com.yuqi.admin.py.utils;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiscCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.SimpleImageLoadingListener;
import com.yuqi.admin.py.R;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import uk.co.senab.photoview.PhotoView;
import uk.co.senab.photoview.PhotoViewAttacher;


/**
 * Created by Admin on 2017/3/13.
 * <p>
 * 图片工具类
 */

public class ImageUtil {

    //图片加载
    public static void loadImg(ImageView imageView, String url) {
        try {
            if (StringUtil.isEmpty(url))
                return;
            if (url.startsWith("http")) {
                ImageLoader.getInstance().displayImage(url, imageView);
            } else {
                imageView.setBackgroundResource(R.mipmap.ic_launcher);
            }
        } catch (Exception e) {
            imageView.setBackgroundResource(R.mipmap.ic_launcher);
        }
    }
}
