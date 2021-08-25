package com.example.liandroid.CustomTest.MyViewPager;

import android.os.SystemClock;

/**
 * author: LL
 * created on: 2021/8/24 21:37
 * description:
 */
public class MyScroller {

  //起始坐标
  private float mStartX;
  private float mStartY;
  //移动的距离
  private int mDistanceX;
  private int mDistanceY;
  //开始的时间
  private long mStartTime;

  private long mTotalTime = 500;
  private boolean mIsFinished;
  private float mCurrentX;

  public float getCurrentX() {
    return mCurrentX;
  }

  public void startScroll(float startX, float startY, int distanceX, int distanceY) {
    this.mStartX = startX;
    this.mStartY = startY;
    this.mDistanceX = distanceX;
    this.mDistanceY = distanceY;
    this.mStartTime = SystemClock.uptimeMillis();//系统开机时间
    this.mIsFinished = false;
  }

  /**
   * 速度
   * 求移动一小段的距离
   * 求移动一小段对应的坐标
   * 求移动一小段对应的时间
   * true 正在移动
   * false 移动结束
   *
   * @return
   */
  public boolean computeScrollOffset() {
    if (mIsFinished) {
      return false;
    }
    long endTime = SystemClock.uptimeMillis();
    long passTime = endTime - mStartTime;
    if (passTime < mTotalTime) {
      //还没有结束
      //计算平均速度
      //float voleCity = distanceX/totalTime;
      //移动这个一小段对应的距离
      float distanceSmallX = passTime * mDistanceX / mTotalTime;
      //移动这一小段对应的x坐标
      mCurrentX = mStartX + distanceSmallX;
    } else {
      //结束
      mIsFinished = true;
      mCurrentX = mStartX + mDistanceX;
    }
    return true;
  }
}
