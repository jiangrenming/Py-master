package com.yuqi.admin.py.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.GridView;

/**
 * Created by Administrator on 2017/12/13.
 */
public class MultiGridView extends GridView {
    public MultiGridView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MultiGridView(Context context) {
        super(context);
    }

    public MultiGridView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int expandSpec = View.MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2, MeasureSpec.AT_MOST);
        super.onMeasure(widthMeasureSpec, expandSpec);
    }

}