package com.example.liandroid.customtest.menu;

import android.view.View;
import android.view.animation.RotateAnimation;

/**
 * author: LL
 * created on: 2021/8/18 20:48
 * description:显示和隐藏指定的控件
 */
public class Tools {
  public static void hideView(View view) {
    hideView(view,0);
  }

  public static void showView(View view) {
    RotateAnimation rotateAnimation = new RotateAnimation(
        180,
        360,
        view.getWidth()/2,
        view.getHeight());
    rotateAnimation.setDuration(500);//设置动画播放持续的时间
    rotateAnimation.setFillAfter(true);//动画停留在播放完成的状态
    view.startAnimation(rotateAnimation);
  }

  public static void hideView(View view, int startOffset) {
    RotateAnimation rotateAnimation = new RotateAnimation(
        0,
        180,
        view.getWidth()/2,
        view.getHeight());
    rotateAnimation.setDuration(500);//设置动画播放持续的时间
    rotateAnimation.setFillAfter(true);//动画停留在播放完成的状态
    rotateAnimation.setStartOffset(startOffset);//延迟多久后播放动画
    view.startAnimation(rotateAnimation);
  }
}
