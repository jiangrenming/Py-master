package com.yuqi.admin.py.view.lib;

import android.content.Context;
import android.support.v4.view.ViewPager;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * 自定义高度的viewpapger
 */
public class BaseViewPager1 extends ViewPager {
	private boolean scrollable = true;

	public BaseViewPager1(Context context) {
		super(context);
	}

	public BaseViewPager1(Context context, AttributeSet attrs) {
		super(context, attrs);
	}

	/**
	 * 设置viewpager是否可以滚动
	 * 
	 * @param enable
	 */
	public void setScrollable(boolean enable) {
		scrollable = enable;
	}

	@Override
	public boolean onInterceptTouchEvent(MotionEvent event) {
		if (scrollable) {
			return super.onInterceptTouchEvent(event);
		} else {
			return false;
		}
	}

	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		//获取到宽度的模式
		int width_mode=MeasureSpec.getMode(widthMeasureSpec);
		//获取到屏幕的宽度
		int width=MeasureSpec.getSize(widthMeasureSpec);
		//高度的大小
		int height=0;
		//说明是填充父窗体
		double scale=203.0/381.00;
		if(width_mode==MeasureSpec.EXACTLY){
			height= width;
		}
		widthMeasureSpec=MeasureSpec.makeMeasureSpec(width,MeasureSpec.EXACTLY);
		heightMeasureSpec=MeasureSpec.makeMeasureSpec(height,MeasureSpec.EXACTLY);
		super.onMeasure(widthMeasureSpec,heightMeasureSpec);
	}





}