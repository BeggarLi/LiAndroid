package com.example.liandroid.customtest.myviewpager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

/**
 * author: LL
 * created on: 2021/8/24 16:39
 * description:仿ViewPager
 */
public class MyViewPager extends ViewGroup {

  private float mLastX;
  private float mDownX;
  private float mDownY;
  private int mCurrentIndex;//当前页面下标位置
  private MyScroller mMyScroller;
  private OnPagerChangeListener mOnPagerChangeListener;
//  Scroller mScroller;

  public MyViewPager(Context context, AttributeSet attrs) {
    super(context, attrs);
    initView(context);
  }

  private void initView(Context context) {
    mMyScroller = new MyScroller();
//    mScroller = new Scroller(context);
  }

  @Override
  protected void onLayout(boolean changed, int l, int t, int r, int b) {
    //遍历每个孩子，给每个孩子指定在屏幕的坐标位置
    for (int i = 0; i < getChildCount(); i++) {
      View childView = getChildAt(i);
      childView.layout(i * getWidth(), 0, (i + 1) * getWidth(), getHeight());
    }
  }

  /**
   * 如果当前方法返回true，拦截事件，将会触发当前的onTouchEvent()
   * false 不拦截事件，事件继续传递给孩子
   *
   * @param ev
   * @return
   */
  @Override
  public boolean onInterceptTouchEvent(MotionEvent ev) {
    boolean result = false;//默认传递给孩子
    switch (ev.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mDownX = ev.getX();
        mDownY = ev.getY();
        mLastX = ev.getX();
        break;
      case MotionEvent.ACTION_MOVE:
        float endX = ev.getX();
        float endY = ev.getY();
        float distanceX = Math.abs(endX-mDownX);
        float distanceY = Math.abs(endY-mDownY);

        if (distanceX > distanceY && distanceX >5){
          result = true;
        }

        break;
      case MotionEvent.ACTION_UP:
        break;
    }
    return result;
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    float currentX = event.getX();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        break;
      case MotionEvent.ACTION_MOVE:
        scrollBy((int) (mLastX - currentX), 0);
        break;
      case MotionEvent.ACTION_UP:
        float endX = event.getX();
        int tempIndex = mCurrentIndex;
        if ((mDownX - endX) > getWidth() / 2) {
          tempIndex++;
        } else if ((endX - mDownX) > getWidth() / 2) {
          tempIndex--;
        }
        scrollToPager(tempIndex);
        break;
    }
    mLastX = currentX;
    return true;
  }

  //屏蔽非法值 根据位置移动到指定页面
  public void scrollToPager(int tempIndex) {
    if (tempIndex < 0) {
      tempIndex = 0;
    }
    if (tempIndex > getChildCount() - 1) {
      tempIndex = getChildCount() - 1;
    }
    mCurrentIndex = tempIndex;//当前页面下标位置

    if (mOnPagerChangeListener != null) {
      mOnPagerChangeListener.onScrollToPager(mCurrentIndex);
    }
    int distanceX = mCurrentIndex * getWidth() - getScrollX();//总距离
//
//    scrollTo(currentIndex*getWidth(),getScrollY());//移动到指定位置
//    mScroller.startScroll(getScrollX(),getScrollY(),distanceX,0);
//    mMyScroller.startScroll(getScrollX(), getScrollY(), distanceX, 0);
    mMyScroller.startScroll(getScrollX(), getScrollY(), distanceX, Math.abs(distanceX));
    invalidate();//onDraw() computeScroll()
  }

  @Override
  public void computeScroll() {
    if (mMyScroller.computeScrollOffset()) {
      float currx = mMyScroller.getCurrentX();
      // scrollBy((int) mMyScroller.distanceX,0);
      scrollTo((int) currx, 0);
      invalidate();
    }
    super.computeScroll();
  }


  /**
   * 监听页面的改变
   */
  public interface OnPagerChangeListener {
    void onScrollToPager(int position);//当页面改变时回调此方法
  }

  public void setOnPagerChangeListener(OnPagerChangeListener onPagerChangeListener) {
    mOnPagerChangeListener = onPagerChangeListener;
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    for (int i = 0; i < getChildCount(); i++) {
      View child = getChildAt(i);
      child.measure(widthMeasureSpec, heightMeasureSpec);
    }
  }
}
