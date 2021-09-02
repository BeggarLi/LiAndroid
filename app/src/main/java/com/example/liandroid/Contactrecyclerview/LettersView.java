package com.example.liandroid.Contactrecyclerview;


import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import androidx.annotation.Nullable;

/**
 * author: LL
 * created on: 2021/8/26 16:37
 * description:
 * 二十六个字母放置
 * 1.把二十六个字母放入数组
 * 2.在onMeasure（）中测量itemHeight和itemWidth
 * 3.在onDraw（）计算WordWidth WordHeight，WordX，WordY
 * 手指按下文字变色
 * 1重写onTouchEvent 返回true，在down/move的过程中计算
 * int touchIndex =Y/itemHeight 强制绘制
 * 2在onDraw（）方法中设置画笔变色
 * 3在up的时候 touchindex = -1 强制绘制
 */
public class LettersView extends View {

  private int mItemWidth;
  private int mItemHeight;
  private Paint mPaint;
  private int mTouchIndex = -1;
  private OnIndexChangeListener mOnIndexChangeListener;

  public LettersView(Context context, @Nullable AttributeSet attrs) {
    super(context, attrs);
    mPaint = new Paint();
    mPaint.setColor(Color.WHITE);
    mPaint.setAntiAlias(true);
    mPaint.setTypeface(Typeface.DEFAULT_BOLD);//设置粗体
  }
  private String[] words ={"A","B","C","D","E","F","G","H","I",
      "J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z",
  };

  @Override
  protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
    super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    mItemWidth = getMeasuredWidth();
    mItemHeight =getMeasuredHeight()/words.length;
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    for(int i = 0; i < words.length; i++){
      if (mTouchIndex == i){
        //设置灰色
        mPaint.setColor(Color.GRAY);
      }else{
        //设置为白色
        mPaint.setColor(Color.WHITE);
      }
      String word = words[i];
      Rect rect = new Rect();
      mPaint.getTextBounds(word,0,1,rect);//画笔

      int wordWidth = rect.width();
      int wordHeight = rect.height();
      int wordX = mItemWidth/2 - wordWidth/2;
      int wordY = mItemHeight/2 + wordHeight/2 + i*mItemHeight;
      canvas.drawText(word,wordX,wordY,mPaint);

    }
  }

  @Override
  public boolean onTouchEvent(MotionEvent event) {
    switch (event.getAction()){
      case MotionEvent.ACTION_DOWN:
      case MotionEvent.ACTION_MOVE:
        float Y = event.getY();
        int index = (int) (Y/mItemHeight);
        if(index != mTouchIndex){
          mTouchIndex = index;
          invalidate();
          if(mOnIndexChangeListener != null && mTouchIndex < words.length){
            mOnIndexChangeListener.onIndexChange(words[mTouchIndex]);
          }
        }
        break;
      case MotionEvent.ACTION_UP:
        mTouchIndex = -1;
        invalidate();
        break;
    }
    return true;
  }

  //监听字母下标的变化
  public interface OnIndexChangeListener{
    void onIndexChange(String word);
  }

  //设置
  public void setOnIndexChangeListener(
      OnIndexChangeListener onIndexChangeListener) {
    mOnIndexChangeListener = onIndexChangeListener;
  }
}
