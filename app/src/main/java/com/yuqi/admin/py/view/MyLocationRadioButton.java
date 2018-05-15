package com.yuqi.admin.py.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.RadioButton;

import com.yuqi.admin.py.R;


/**
 * Created by Admin on 2017/3/14.
 *      选择卡 底部下划线
 */

public class MyLocationRadioButton extends RadioButton {
    int width,height;
    int lineColor;
    Paint paint  = new Paint();
    public MyLocationRadioButton(Context context) {
        super(context);
    }

    public MyLocationRadioButton(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public MyLocationRadioButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    float textWidth;
    int textLeft,textRight;
    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = Math.abs(left-right);
        height = Math.abs(top-bottom);
        lineColor = getResources().getColor(R.color.white1);
        paint.setColor(lineColor);
        paint.setStrokeWidth(3);
        paint.setTextSize(dip2px(this.getContext(), 14f));
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        Paint.FontMetrics fm = paint.getFontMetrics();
        textWidth= paint.measureText(getText()+"");
//        textWidth = (float) (Math.ceil(fm.descent - fm.top)*getText().length());
        paint.setColor(lineColor);
        canvas.drawRect((width- textWidth)/2,height-6,(width+textWidth)/2,height,paint);
//        canvas.drawRect((width - textWidth)/2,height/4,(width + textWidth),height,paint);

    }

    @Override
    public void setChecked(boolean checked) {
        super.setChecked(checked);
        if (checked){
            lineColor = getResources().getColor(R.color.yellow1);
            setTextColor(getResources().getColor(R.color.yellow1));
        }else {
            lineColor = getResources().getColor(R.color.white1);
            setTextColor(getResources().getColor(R.color.text_color));
        }
        invalidate();
    }

    /**
     * 根据手机的分辨率从 dip 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

}
