package com.example.liandroid.CustomTest;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.example.liandroid.R;

public class ViewpageActivity extends AppCompatActivity {

  private ViewPager mViewPager;
  private TextView mTitleTextView;
  private LinearLayout mPointGroup;

  private Boolean mIsDragging = false;//是否已经滚动

  private ArrayList<ImageView> mImageViews;

  private final int[] mImageIds = {
      R.drawable.a,
      R.drawable.b,
      R.drawable.c,
      R.drawable.d,
      R.drawable.e
  };

  /**
   * 上一次高亮显示的位置
   */
  private int mPrePosition = 0;

  private final String[] mImageDescription = {
      "春天的第一束光，带来了希望",
      "世间山河",
      "星火璀璨",
      "是光亦是风",
      "秋意散尽，凛冬已至",
  };

  private final Handler mHandler = new Handler() {
    @Override
    public void handleMessage(@NonNull Message msg) {
      super.handleMessage(msg);
      int item = mViewPager.getCurrentItem() + 1;
      mViewPager.setCurrentItem(item);
      mHandler.sendEmptyMessageDelayed(0, 2000);
    }
  };

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, ViewpageActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_viewpage);
    mViewPager = findViewById(R.id.viewpage);
    mTitleTextView = findViewById(R.id.textview_title);
    mPointGroup = findViewById(R.id.ll_point_group);
    mImageViews = new ArrayList<>();
    //3.准备数据
    for (int i = 0; i < mImageIds.length; i++) {
      ImageView imageView = new ImageView(this);
      imageView.setBackgroundResource(mImageIds[i]);
      //添加到集合中
      mImageViews.add(imageView);

      ImageView point = new ImageView(this);
      point.setBackgroundResource(R.drawable.point_selector);
      LinearLayout.LayoutParams parmas = new LinearLayout.LayoutParams(26, 26);
      if (i == 0) {
        point.setEnabled(true);//显示红色
      } else {
        point.setEnabled(false);//其他都显示灰色
        parmas.leftMargin = 8;
      }

      point.setLayoutParams(parmas);
      mPointGroup.addView(point);

    }
    //4.设置适配器（PagerAdapter)-item布局-绑定数据
    mViewPager.setAdapter(new MyPagerAdapter());
    //设置监听viewpager页面的改变
    mViewPager.addOnPageChangeListener(new MyOnPageChangeListener());

    //设置中间位置
    int item =
        Integer.MAX_VALUE / 2 - Integer.MAX_VALUE / 2 % mImageViews.size();//保证是mImageViews的整数倍
    mViewPager.setCurrentItem(item);

    mTitleTextView.setText(mImageDescription[mPrePosition]);
    //发消息
    mHandler.sendEmptyMessageDelayed(0, 2000);
  }

  class MyOnPageChangeListener implements ViewPager.OnPageChangeListener {

    /**
     * 当页面滚动了的时候回调这个方法
     *
     * @param position             当前页面的位置
     * @param positionOffset       滑动页面的百分比
     * @param positionOffsetPixels 在屏幕上滑动的像素
     */
    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    /**
     * 当某个页面被选中了的时候会回调
     *
     * @param position 被选中的页面的位置
     */
    @Override
    public void onPageSelected(int position) {
      int realPosition = position % mImageViews.size();

      //设置对应页面的文本信息
      mTitleTextView.setText(mImageDescription[realPosition]);
      //把上一个高亮的设置成默认
      mPointGroup.getChildAt(mPrePosition).setEnabled(false);
      //当前的设置为高亮
      mPointGroup.getChildAt(realPosition).setEnabled(true);

      mPrePosition = realPosition;

    }

    /**
     * 当页面滚动状态的变化时候回调这个方法
     * 静止—>到滑动
     * 滑动->到静止
     * 静止->到拖拽
     *
     * @param state
     */
    @Override
    public void onPageScrollStateChanged(int state) {
      if (state == ViewPager.SCROLL_STATE_DRAGGING) {
        mIsDragging = true;
        mHandler.removeCallbacksAndMessages(null);
      } else if (state == ViewPager.SCROLL_STATE_SETTLING) {

      } else if (state == ViewPager.SCROLL_STATE_IDLE && mIsDragging) {
        mIsDragging = false;
        mHandler.removeCallbacksAndMessages(null);
        mHandler.sendEmptyMessageDelayed(0, 2000);
      }

    }
  }

  class MyPagerAdapter extends PagerAdapter {

    /**
     * 得到图片的总数
     *
     * @return
     */
    @Override
    public int getCount() {
      return Integer.MAX_VALUE;
    }

    /**
     * 相当于getView方法
     *
     * @param container viewpager自身
     * @param position  当前实例化页面的位置
     * @return
     */
    @NonNull
    @Override
    public Object instantiateItem(
        @NonNull ViewGroup container,
        int position) {
      int realPosition = position % mImageViews.size();
      ImageView imageView = mImageViews.get(realPosition);
      container.addView(imageView);//添加到viewpager中

      imageView.setOnTouchListener(new View.OnTouchListener() {
        @Override
        public boolean onTouch(View v, MotionEvent event) {
          switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN://按下
              mHandler.removeCallbacksAndMessages(null);
              break;
            case MotionEvent.ACTION_MOVE://手指移动
              break;
            case MotionEvent.ACTION_UP://手指离开\
              mHandler.sendEmptyMessageDelayed(0, 2000);
              break;
          }
          return false;
        }
      });

      imageView.setTag(position);
      imageView.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {
          int position = (int) v.getTag() % mImageViews.size();
          String text = mImageDescription[position];
          Toast.makeText(ViewpageActivity.this, " " + text, Toast.LENGTH_SHORT).show();

        }
      });
      return imageView;
    }

    /**
     * 比较view和object是否是一个实例
     *
     * @param view   页面
     * @param object 上一个方法返回的参数
     * @return
     */
    @Override
    public boolean isViewFromObject(
        @NonNull View view,
        @NonNull Object object) {
      if (view == object) {
        return true;
      } else {
        return false;
      }
    }

    /**
     * 释放资源
     *
     * @param container viewpager
     * @param position  要释放的位置
     * @param object    要释放的页面
     */
    @Override
    public void destroyItem(
        @NonNull ViewGroup container,
        int position,
        @NonNull Object object) {
      container.removeView((View) object);
    }
  }

}