package com.example.liandroid.CustomTest.ToggleButton;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

import com.example.liandroid.R;

/**
 * author: LL
 * created on: 2021/8/23 17:47
 * description:自定义button
 */
public class MyToggleButton extends View implements View.OnClickListener {
  private Bitmap mBackGroundBitmap;
  private Bitmap mSlidingBitmap;
  private int mSlidLeftMax;
  private int mSlidLeft;
  private Paint mPaint;

  private float mStartX;
  private float mLastX;

  private boolean isOpen = false;
  private boolean isEnableClick =true;//默认点击事件生效

  public MyToggleButton(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
  }

  private void initView() {
    mPaint = new Paint();
    mPaint.setAntiAlias(true);
    mBackGroundBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.switch_background);
    mSlidingBitmap =BitmapFactory.decodeResource(getResources(),R.drawable.slide_button);
    mSlidLeftMax = mBackGroundBitmap.getWidth()-mSlidingBitmap.getWidth();

    setOnClickListener(this);
  }

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    setMeasuredDimension(mBackGroundBitmap.getWidth(),mBackGroundBitmap.getHeight());
  }

  @Override
  protected void onDraw(Canvas canvas) {
    canvas.drawBitmap(mBackGroundBitmap,0,0,mPaint);
    canvas.drawBitmap(mSlidingBitmap,mSlidLeft,0,mPaint);
  }

  @Override
  public void onClick(View view) {
    if(isEnableClick){
      isOpen = !isOpen;
      flushView();
    }
  }

  private void flushView() {
    if(isOpen){
      mSlidLeft = mSlidLeftMax;
    }else {
      mSlidLeft = 0;
    }
    invalidate();//导致onDraw()执行
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    super.onTouchEvent(event);
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
        mLastX = mStartX = event.getX();//1记录按下的坐标
        isEnableClick = true;
        break;

      case MotionEvent.ACTION_MOVE:
        float endX = event.getX();//2计算结束值
        float distanceX = endX - mStartX;//3计算偏移量
        mSlidLeft += distanceX;
        if(mSlidLeft < 0){
          mSlidLeft = 0;
        }else if(mSlidLeft >mSlidLeftMax){
          mSlidLeft = mSlidLeftMax;
        }
        invalidate();//5刷新
        mStartX = getX();//6数据还原
        if(Math.abs(endX - mLastX) > 5){
          isEnableClick = false;
        }
        break;
      case MotionEvent.ACTION_UP:
        if (!isEnableClick){
          if(mSlidLeft > mSlidLeftMax/2){
            isOpen = true;
          }else{
            isOpen = false;
          }
          flushView();
        }
        break;
    }
    return true;
  }
}
