package com.example.liandroid.CustomTest.ToggleButton;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.liandroid.CustomTest.CustomViewActivity;
import com.example.liandroid.R;

public class ToggleButtonActivity extends AppCompatActivity {

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity,ToggleButtonActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_toggle_button);
  }
}