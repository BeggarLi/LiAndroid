package com.example.liandroid.CustomTest.MyViewPager;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Scroller;

/**
 * author: LL
 * created on: 2021/8/24 16:39
 * description:仿ViewPager
 */
public class MyViewPager extends ViewGroup {

  private float mStartX;
  private float mLastX;
  private int mCurrentIndex;//当前页面下标位置
  private MyScroller mMyScroller;
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

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    float currentX = event.getX();
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mStartX = event.getX();
        mLastX = event.getX();
        break;
      case MotionEvent.ACTION_MOVE:
        scrollBy((int) (mLastX - currentX), 0);
        break;
      case MotionEvent.ACTION_UP:
        float endX = event.getX();
        int tempIndex = mCurrentIndex;
        if ((mStartX - endX) > getWidth() / 2) {
          tempIndex++;
        } else if ((endX - mStartX) > getWidth() / 2) {
          tempIndex--;
        }
        scrollToPager(tempIndex);
        break;
    }
    mLastX = currentX;
    return true;
  }

  //屏蔽非法值 根据位置移动到指定页面
  private void scrollToPager(int tempIndex) {
    if (tempIndex < 0) {
      tempIndex = 0;
    }
    if (tempIndex > getChildCount() - 1) {
      tempIndex = getChildCount() - 1;
    }
    mCurrentIndex = tempIndex;//当前页面下标位置

    int distanceX = mCurrentIndex * getWidth() - getScrollX();//总距离
//
//    scrollTo(currentIndex*getWidth(),getScrollY());//移动到指定位置
//    mScroller.startScroll(getScrollX(),getScrollY(),distanceX,0);
    mMyScroller.startScroll(getScrollX(), getScrollY(), distanceX, 0);
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
}
