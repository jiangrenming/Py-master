package com.yuqi.admin.py.view;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.LinearInterpolator;
import android.widget.AbsListView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;

import com.yuqi.admin.py.data.CommonData;


/**
 * Created by Administrator on 2017/5/11.
 *         下拉刷新 上拉加载
 */

public class MyListView extends ListView implements AbsListView.OnScrollListener {
    int headWidth,headHeight,firstItem=0,footHeight=0;
    boolean isRefesh,onRefresh,isToFoot,isLoad=true;
    TextView top,bottom;
    LinearLayout head,foot;
    ListListener l;
    public MyListView(Context context) {
        super(context);
        init(context);
    }
    public MyListView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyListView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }
    public void setOnListListener(ListListener l){
        this.l = l;
    }
    private void init(Context context){
        head= new LinearLayout(context);
        top = new TextView(context);
        top.setWidth(CommonData.windowWidth);
        top.setGravity(Gravity.CENTER);
        top.setPadding(0 ,50,0,50);
        head.addView(top);
        measureView(head);
        headWidth = head.getMeasuredWidth();
        headHeight = head.getMeasuredHeight();
        head.setPadding(0, -headHeight, 0, 0);
        addHeaderView(head, null, false);
        foot = new LinearLayout(context);
        bottom = new TextView(context);
        bottom.setWidth(CommonData.windowWidth);
        bottom.setGravity(Gravity.CENTER);
        bottom.setPadding(0 ,50,0,50);
        foot.addView(bottom);
        measureView(foot);
        footHeight = foot.getMeasuredHeight();
        bottom.setText(CommonData.LISTVIEW_LOADING);
        addFooterView(foot, null, false);
        setOnScrollListener(this);
        foot.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CommonData.LISTVIEW_LOADING_FAILD.equals(bottom.getText()+""))
                    footListener.footDo();
            }
        });
    }
    private void measureView(View child) {
        ViewGroup.LayoutParams p = child.getLayoutParams();
        if (p == null) {
            p = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);
        }
        int childWidthSpec = ViewGroup.getChildMeasureSpec(0, 0 + 0, p.width);
        int lpHeight = p.height;
        int childHeightSpec;
        if (lpHeight > 0) {
            childHeightSpec = MeasureSpec.makeMeasureSpec(lpHeight,
                    MeasureSpec.EXACTLY);
        } else {
            childHeightSpec = MeasureSpec.makeMeasureSpec(0,
                    MeasureSpec.UNSPECIFIED);
        }
        child.measure(childWidthSpec, childHeightSpec);
    }
    int startY,temp;
    boolean isHeadStartY,isToTop;
    int firstItemTop=0;
    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        if (isRefesh) return false;
        switch (ev.getAction()){
            case MotionEvent.ACTION_UP:
                ValueAnimator animator;
                if (onRefresh&&firstItemTop>headHeight) {
                    animator = ValueAnimator.ofInt(temp,0);
                    head.setPadding(0, 0, 0, 0);
                    isRefesh = true;
                }else{
                    animator = ValueAnimator.ofInt(temp,-headHeight);
                    head.setPadding(0, -headHeight, 0, 0);
                }
                if (firstItemTop>headHeight) {
                    top.setText(CommonData.LISTVIEW_REFRESHING);
                    animator.addUpdateListener(listener);
                    animator.addListener(al);
                    animator.setDuration(500);
                    animator.setInterpolator(new LinearInterpolator());
                    animator.start();
                }
                isHeadStartY = false;
                break;
            case MotionEvent.ACTION_MOVE:
                if (firstItem == 0&&!isRefesh){
                    if (!isHeadStartY){startY = (int) ev.getY();isHeadStartY=true;}
                    int tempY= (int) (ev.getY()-startY);
                    if (firstItemTop > headHeight){
                        top.setText(CommonData.LISTVIEW_REFRESHING_TOUCH_HALF);
                        onRefresh = true;
                    }else {
                        onRefresh = false;
                        top.setText(CommonData.LISTVIEW_REFRESHING_TOUCH_DOWN);
                    }
                    temp =-headHeight+tempY;
                    head.setPadding(0,temp,0,0);
                    View viewTop = getChildAt(1);
                    if (viewTop!=null)
                    firstItemTop =viewTop.getTop();
                }
                break;
        }
        return super.onTouchEvent(ev);
    }
    Animator.AnimatorListener al = new Animator.AnimatorListener() {
        @Override
        public void onAnimationStart(Animator animation) {

        }

        @Override
        public void onAnimationEnd(Animator animation) {
            if(firstItemTop>headHeight&&l!=null)l.onRefresh();
        }

        @Override
        public void onAnimationCancel(Animator animation) {

        }

        @Override
        public void onAnimationRepeat(Animator animation) {

        }
    };
    ValueAnimator.AnimatorUpdateListener listener = new ValueAnimator.AnimatorUpdateListener() {
        @Override
        public void onAnimationUpdate(ValueAnimator animation) {
            Log.e("height----",animation.getAnimatedValue()+"");
            head.setPadding(0, (Integer) animation.getAnimatedValue(), 0, 0);
        }
    };
    public void stopRefresh(){
        isRefesh=false;
        head.setPadding(0,-headHeight,0,0);
    }
    public void setIsLoad(boolean isLoad){
        this.isLoad = isLoad;
        foot.setPadding(0,0,0,isLoad ? 0 :-footHeight);
    }
    FootListener footListener;
    public void setOnFootListener(FootListener footListener){
        this.footListener = footListener;
    }



    public interface FootListener{
        void footDo();
    }
    public interface ListListener{
        public void onRefresh();
        public void onLoadMore();
    }
    public void setFootViewText(String str){
        bottom.setText(str);
    }
    @Override
    public void onScrollStateChanged(AbsListView view, int scrollState) {
        if (isToFoot && scrollState == SCROLL_STATE_IDLE && !onRefresh){
            if (l != null&&isLoad)l.onLoadMore();
        }
    }

    @Override
    public void onScroll(AbsListView view, int firstVisibleItem, int visibleItemCount, int totalItemCount) {
        firstItem = firstVisibleItem;
        isToFoot = totalItemCount==(firstVisibleItem+visibleItemCount);
    }
}
