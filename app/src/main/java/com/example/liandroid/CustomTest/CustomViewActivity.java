package com.example.liandroid.CustomTest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.CustomTest.PopupWindow.PopupWindowActivity;
import com.example.liandroid.CustomTest.ToggleButton.ToggleButtonActivity;
import com.example.liandroid.CustomTest.autoattibute.AutoAttributeActivity;
import com.example.liandroid.CustomTest.MyViewPager.MyViewPagerActivity;
import com.example.liandroid.R;

public class CustomViewActivity extends AppCompatActivity {
  private Button mButton1;
  private Button mButton2;
  private Button mButton3;
  private Button mButton4;
  private Button mButton5;
  private Button mButton6;

  public static void start(@NonNull Activity activity){
    Intent intent = new Intent(activity,CustomViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_custom_view);
    mButton1 = findViewById(R.id.custom_view_menu_test_button);
    mButton2 = findViewById(R.id.custom_view_viewpage_test_button);
    mButton3 = findViewById(R.id.custom_view_popupwindow_button);
    mButton4 = findViewById(R.id.custom_view_toggle_button);
    mButton5 = findViewById(R.id.custom_view_auto_attribute_button);
    mButton6 = findViewById(R.id.custom_view_MyViewPager_button);

    mButton1.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      MenuTestActivity.start(CustomViewActivity.this);
      }
    });

    mButton2.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
      ViewpageActivity.start(CustomViewActivity.this);
      }
    });
    mButton3.setOnClickListener(view -> {
      PopupWindowActivity.start(CustomViewActivity.this);
    });
    mButton4.setOnClickListener(view -> {
      ToggleButtonActivity.start(CustomViewActivity.this);
    });
    mButton5.setOnClickListener(view -> {
      AutoAttributeActivity.start(CustomViewActivity.this);
    });
    mButton6.setOnClickListener(view -> {
      MyViewPagerActivity.start(CustomViewActivity.this);
    });


  }
}