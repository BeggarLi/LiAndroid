package com.example.liandroid.event;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

public class EventTestActivity extends AppCompatActivity {
  private Button mMotionEventButton;
  private Button mKeyEventButton;

  public static void start(@NonNull Activity activity){
    Intent intent = new Intent(activity,EventTestActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_event_test);

    mMotionEventButton = findViewById(R.id.motion_event_button);
    mKeyEventButton = findViewById(R.id.key_event_button);

    mMotionEventButton.setOnClickListener(v -> {
      MotionEventTestActivity.start(EventTestActivity.this);
    });

    mKeyEventButton.setOnLongClickListener(new View.OnLongClickListener() {
      @Override
      public boolean onLongClick(View v) {
        KeyEventTestActivity.start(EventTestActivity.this);
        return true;
      }
    });
  }

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    //监听back键
    if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
      new AlertDialog.Builder(this)
          .setMessage("你确定退出码")
          .setPositiveButton("退出", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
              finish();
            }
          })
          .setNegativeButton("再看看",null)
          .show();
      return true;
    }
    return super.onKeyUp(keyCode, event);
  }
}