package com.example.liandroid.customtest.wava;

import java.util.logging.LogRecord;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/**
 * author: LL
 * created on: 2021/9/2 21:27
 * description:
 */
public class WaveView extends View {
  private Paint mPaint;
  private float mDownX;
  private float mDownY;
  private int mRadius = 15;
  private Handler mHandler = new Handler() {
    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
      mRadius += 15;
      int alpha = mPaint.getAlpha();
      alpha -= 15;
      if (alpha < 0) {
        alpha = 0;
      }
      mPaint.setAlpha(alpha);
      mPaint.setStrokeWidth(mRadius / 3);
      invalidate();
    }
  };


  public WaveView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
    initView();
  }

  private void initView() {
    int mRadius = 15;
    mPaint = new Paint();
    mPaint.setColor(Color.RED);
    mPaint.setAntiAlias(true);
    mPaint.setStyle(Paint.Style.STROKE);
    mPaint.setStrokeWidth(mRadius / 3);
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    if (mPaint.getAlpha() > 0 && mDownY > 0 && mDownX > 0) {
      canvas.drawCircle(mDownX, mDownY, mRadius, mPaint);
      mHandler.sendEmptyMessageDelayed(0, 50);
    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()) {
      case MotionEvent.ACTION_DOWN:
        mDownX = event.getX();
        mDownY = event.getY();
        initView();
        invalidate();
        break;
    }
    return super.onTouchEvent(event);
  }
}
