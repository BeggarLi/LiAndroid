package com.example.liandroid.customtest.slidemenu;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Scroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: LL
 * created on: 2021/8/31 20:48
 * description:侧滑菜单item
 */
public class SlideLayout extends FrameLayout {

  private View mContentView;
  private View mMenuView;
  private int mContentWidth;
  private int mMenuWidth;
  private int mHeight;
  private float mLastX;
  private float mDownX;
  private float mDownY;
  private Scroller mScroller;

  //设置slideLayout的状态的监听
  public void setOnStateChangedListener(
      OnStateChangedListener onStateChangedListener) {
    mOnStateChangedListener = onStateChangedListener;
  }

  private OnStateChangedListener mOnStateChangedListener;

  public SlideLayout(@NonNull Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    mScroller = new Scroller(context);
  }

  /**
   * 布局文件加载完成的时候回调这个方法
   */
  @Override
  protected void onFinishInflate() {
    super.onFinishInflate();
    mContentView = getChildAt(0);
    mMenuView = getChildAt(1);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mContentWidth = mContentView.getMeasuredWidth();
    mMenuWidth = mMenuView.getMeasuredWidth();
    mHeight = getMeasuredHeight();
  }

  @Override
  protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
    super.onLayout(changed, left, top, right, bottom);
    //指定菜单的位置
    mMenuView.layout(mContentWidth, 0, mContentWidth + mMenuWidth, mHeight);
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mDownX = mLastX = event.getX();
        mDownY = event.getY();
        if(mOnStateChangedListener != null){
          mOnStateChangedListener.onDown(this);
        }
        break;
      case MotionEvent.ACTION_MOVE:
        float endX = event.getX();
        float endY = event.getY();
        float distanceX = endX - mLastX;
        int toScrollX = (int) (getScrollX() - distanceX);
        if (toScrollX < 0) {
          toScrollX = 0;
        } else if (toScrollX > mMenuWidth) {
          toScrollX = mMenuWidth;
        }
        // scrollBy((int) -distanceX,0);
        scrollTo(toScrollX, getScrollY());
        mLastX = event.getX();
        //在x轴和Y轴滑动的距离
        float DX = Math.abs(endX - mDownX);
        float DY = Math.abs(endY - mDownY);
        if (DX > DY && DX > 8) {
          //水平滑动
          //相应侧滑
          //反拦截
          getParent().requestDisallowInterceptTouchEvent(true);
        }
        break;
      case MotionEvent.ACTION_UP:
        int totalScrollX = getScrollX();
        if (totalScrollX <= mMenuWidth / 2) {
          closeMenu();
        } else {
          openMenu();
        }
        break;
    }
    return true;
  }

  @Override
  public boolean onInterceptTouchEvent(MotionEvent event) {
    boolean intercept = false;
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mDownX = mLastX = event.getX();
        break;
      case MotionEvent.ACTION_MOVE:
        float endX = event.getX();
        float endY = event.getY();
        float distanceX = endX - mLastX;
        mLastX = event.getX();
        //在x轴和Y轴滑动的距离
        float DX = Math.abs(endX - mDownX);
        if (DX > 8) {
          //水平滑动
          //相应侧滑
          //反拦截
          intercept = true;
        }
        break;
      case MotionEvent.ACTION_UP:
        break;
    }

    return intercept;
  }

  public void openMenu() {
    int distanceX = mMenuWidth - getScrollX();
    mScroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
    invalidate();
    if(mOnStateChangedListener != null){
      mOnStateChangedListener.onOpen(this);
    }
  }

  public void closeMenu() {
    int distanceX = 0 - getScrollX();
    mScroller.startScroll(getScrollX(), getScrollY(), distanceX, getScrollY());
    invalidate();
    if(mOnStateChangedListener != null){
      mOnStateChangedListener.onClose(this);
    }
  }

  @Override
  public void computeScroll() {
    super.computeScroll();
    if (mScroller.computeScrollOffset()) {
      scrollTo(mScroller.getCurrX(), mScroller.getCurrY());
      invalidate();
    }
  }
}
