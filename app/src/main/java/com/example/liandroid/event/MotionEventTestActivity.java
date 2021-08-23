package com.example.liandroid.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

/**
 * 测试MotionEvent的界面
 */
public class MotionEventTestActivity extends AppCompatActivity {
  private ImageView mImageview_touch_test;
  private LinearLayout parentView;
  private int lastX;
  private int lastY;
  private int maxRight;
  private int maxBottom;

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, MotionEventTestActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_motion_event_test);
    mImageview_touch_test = findViewById(R.id.imageview_touch_test);

    parentView = (LinearLayout)mImageview_touch_test.getParent();

    mImageview_touch_test.setOnTouchListener(new View.OnTouchListener() {
      @Override
      public boolean onTouch(View v, MotionEvent event) {
        //得到事件的坐标
        int eventX = (int) event.getRawX();
        int eventY = (int) event.getRawY();

        switch (event.getAction()){
          case MotionEvent.ACTION_DOWN:

            //得到父视图的right，bottom
            if(maxRight == 0){//保证只赋一次值
              maxRight = parentView.getRight();
              maxBottom = parentView.getBottom();
            }


            //第一次记录lastX,lastY
            lastX = eventX;
            lastY = eventY;
            break;
          case MotionEvent.ACTION_MOVE:
            //计算事件的偏移
            int dx = eventX - lastX;
            int dy = eventY - lastY;
            //根据事件的便偏移移动imageview
            int left = mImageview_touch_test.getLeft() + dx;
            int top = mImageview_touch_test.getTop() +dy;
            int right = mImageview_touch_test.getRight() + dx;
            int bottom = mImageview_touch_test.getBottom() +dy;

            //限制left >= 0
            if(left < 0){
              right += -left;
              left = 0;
            }
            //限制top
            if (top<0){
              bottom += -top;
              top = 0;
            }
            //限制right
            if(right>maxRight){
              left -= right - maxRight;
              right = maxRight;
            }
            //限制bottom
            if(bottom>maxBottom){
              top -= bottom - maxBottom;
              bottom = maxBottom;
            }

            mImageview_touch_test.layout(left,top,right,bottom);
            //再次记录lastX，lastY
            lastX = eventX;
            lastY = eventY;
            break;
          default:
            break;
        }
        return true;
      }
    });

  }

 }