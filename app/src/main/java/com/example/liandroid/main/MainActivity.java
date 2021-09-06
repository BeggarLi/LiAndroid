package com.example.liandroid.main;

import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.Contactrecyclerview.ContactListActivity;
import com.example.liandroid.customtest.CustomViewActivity;
import com.example.liandroid.R;
import com.example.liandroid.event.EventTestActivity;
import com.example.liandroid.handler.HandlerTestActivity;
import com.example.liandroid.listview.ListViewActivity;
import com.example.liandroid.runtimepermission.RuntimePermissionActivity;

public class MainActivity extends AppCompatActivity {
  private Button mButton1;
  private Button mButton2;
  private Button mButton3;
  private Button mButton4;
  private Button mButton5;
  private Button mButton6;
  private Button mButton7;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);
    mButton1 = findViewById(R.id.main_page_handler_test_button);
    mButton2 = findViewById(R.id.main_page_runtime_permission_test_button);
    mButton3 = findViewById(R.id.main_page_recyclerview_test_button);
    mButton4 = findViewById(R.id.main_page_Listview_test_button);
    mButton5 = findViewById(R.id.main_page_event_test_button);
    mButton6 = findViewById(R.id.main_page_custom_test_button);
    mButton7 =findViewById(R.id.main_page_custom_event_bus_button);
    mButton1.setOnClickListener(v -> {
      HandlerTestActivity.start(MainActivity.this);
    });

    mButton2.setOnClickListener(v -> {
      RuntimePermissionActivity.start(MainActivity.this);
    });

    mButton3.setOnClickListener(v -> {
      ContactListActivity.start(MainActivity.this);
    });

    mButton4.setOnClickListener(v -> {
      ListViewActivity.start(MainActivity.this);
    });

    mButton5.setOnClickListener(v -> {
      EventTestActivity.start(MainActivity.this);
    });

    mButton6.setOnClickListener(v -> {
      CustomViewActivity.start(MainActivity.this);
    });

    mButton7.setOnClickListener(view -> {

    });
  }
}