package com.example.liandroid.customtest.slidemenu;

/**
 * author: LL
 * created on: 2021/9/2 16:30
 * description:监听SlideLayout状态的改变
 */
public interface OnStateChangedListener {
  void onClose(SlideLayout layout);
  void onDown(SlideLayout layout);
  void onOpen(SlideLayout layout);
}
