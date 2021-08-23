package com.example.liandroid.handler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Looper;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;


public class HandlerTestActivity extends AppCompatActivity {

  private TextView mTextView;
  Handler mHandler;

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, HandlerTestActivity.class);
    activity.startActivity(intent);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_handler_test);
    mTextView = findViewById(R.id.handler_test_page_textview);
    mHandler = new Handler();

    new Thread() {
      @Override
      public void run() {

        Looper.prepare();

        mHandler.post(new Runnable() {
          @Override
          public void run() {
            CountDownTimer timer = new CountDownTimer(60000, 1000) {
              @Override
              public void onTick(long millisUntilFinished) {
                mTextView.setText("倒计时" + millisUntilFinished / 1000 + "秒");
              }

              @Override
              public void onFinish() {
                mTextView.setText("over");
              }
            }.start();
          }
        });
        Looper.loop();
      }
    }.start();
  }
}