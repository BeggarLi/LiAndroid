package com.example.liandroid.customtest.myviewpager;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.example.liandroid.R;

public class MyViewPagerActivity extends Activity {

  private MyViewPager mMyViewPager;
  private RadioGroup mRadioGroup;

  private int[] mIds ={
      R.drawable.a1,
      R.drawable.a2,
      R.drawable.a3,
      R.drawable.a4,
      R.drawable.a5,
      R.drawable.a6,
  };

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity,MyViewPagerActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    requestWindowFeature(Window.FEATURE_NO_TITLE);
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_my_view_pager);
    mMyViewPager = findViewById(R.id.my_view_pager);
    mRadioGroup =findViewById(R.id.my_view_pager_radio_group);

    for(int i = 0; i < mIds.length; i++){
      ImageView imageView = new ImageView(this);
      imageView.setBackgroundResource(mIds[i]);
      mMyViewPager.addView(imageView);
    }

    View view = View.inflate(this,R.layout.my_view_pager_test,null);
    mMyViewPager.addView(view,2);
    for (int i = 0; i < mMyViewPager.getChildCount(); i++){
      RadioButton button = new RadioButton(this);
      button.setId(i);
      if (i == 0){
        button.setChecked(true);
      }
      mRadioGroup.addView(button);
    }

    //设置RadioGroup选中状态的变化
    mRadioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
      @Override
      public void onCheckedChanged(RadioGroup radioGroup, int checkedID) {
        mMyViewPager.scrollToPager(checkedID);
      }
    });
    //设置监听页面的改变
    mMyViewPager.setOnPagerChangeListener(new MyViewPager.OnPagerChangeListener() {
      @Override
      public void onScrollToPager(int position) {
        mRadioGroup.check(position);
      }
    });
  }

}