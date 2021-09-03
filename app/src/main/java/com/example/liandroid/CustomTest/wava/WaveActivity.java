package com.example.liandroid.customtest.wava;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import com.example.liandroid.R;
import com.example.liandroid.customtest.CustomViewActivity;

public class WaveActivity extends AppCompatActivity {

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity,WaveActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_wava);
  }
}