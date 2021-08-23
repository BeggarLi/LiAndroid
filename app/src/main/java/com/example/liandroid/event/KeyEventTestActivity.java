package com.example.liandroid.event;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

public class KeyEventTestActivity extends AppCompatActivity {
  private boolean exit = false;//标识是否可以退出

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, KeyEventTestActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_key_event_test);
  }

  private Handler mHandler = new Handler(){
    @Override
    public void handleMessage(@NonNull Message msg) {
      if(
          msg.what == 1){
        exit = false;
      }
    }
  };

  @Override
  public boolean onKeyUp(int keyCode, KeyEvent event) {
    if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
      if(!exit){
        exit = true;
        Toast.makeText(this, "再按一次退出应用", Toast.LENGTH_SHORT).show();
        mHandler.sendEmptyMessageDelayed(1,2000);
        return true;
      }
    }
    return super.onKeyUp(keyCode, event);
  }
}