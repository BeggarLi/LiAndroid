package com.example.liandroid.customtest.autoattibute;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import androidx.annotation.Nullable;

import com.example.liandroid.R;

/**
 * author: LL
 * created on: 2021/8/24 13:55
 * description:自定义属性
 */
public class MyAttributeView extends View {
  private int mAge;
  private String mName;
  private Bitmap mBg;
  public MyAttributeView(Context context,
      @Nullable AttributeSet attrs) {
    super(context, attrs);
//    //1用命名空间获取
    String age = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","my_age");
    String name = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","name");
    String bg = attrs.getAttributeValue("http://schemas.android.com/apk/res-auto","bg");
//    System.out.println("age=" + age + " name =" + name + " bg="+ bg);
//    //2遍历属性集合
//    for(int i = 0; i<attrs.getAttributeCount();i++){
//      System.out.println("age=" + age + " name =" + name + " bg="+ bg);
//    }
    //3使用系统工具获取属性
    TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.MyAttributeView);
    for(int i =0;i < typedArray.getIndexCount();i++){
      int index =typedArray.getIndex(i);
      switch(index){
        case R.styleable.MyAttributeView_my_age:
          mAge = typedArray.getInt(index,0);
          break;
        case R.styleable.MyAttributeView_my_name:
          mName = typedArray.getString(index);
          break;
        case R.styleable.MyAttributeView_my_bg:
          Drawable drawable = typedArray.getDrawable(index);
          BitmapDrawable drawable1 = (BitmapDrawable) drawable;
          mBg = drawable1.getBitmap();
          break;
      }
    }
    typedArray.recycle();
  }

  @Override
  protected void onDraw(Canvas canvas) {
    super.onDraw(canvas);
    Paint paint = new Paint();
    canvas.drawText(mName +"---"+mAge,50,50,paint);
    canvas.drawBitmap(mBg,50,50,paint);
  }
}
