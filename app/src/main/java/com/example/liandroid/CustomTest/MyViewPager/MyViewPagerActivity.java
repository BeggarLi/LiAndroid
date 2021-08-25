package com.example.liandroid.CustomTest.MyViewPager;

import androidx.annotation.NonNull;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.liandroid.R;

public class MyViewPagerActivity extends Activity {
  private MyViewPager mMyViewPager;
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

    for(int i = 0; i < mIds.length; i++){
      ImageView imageView = new ImageView(this);
      imageView.setBackgroundResource(mIds[i]);
      mMyViewPager.addView(imageView);
    }
  }
}