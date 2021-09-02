package com.example.liandroid.customtest.autoattibute;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.liandroid.R;

public class AutoAttributeActivity extends AppCompatActivity {

  public static void start(@NonNull Activity activity){
    Intent intent = new Intent(activity,AutoAttributeActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_auto_attribute);
  }
}