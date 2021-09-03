package com.example.liandroid.customtest.slidemenu;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.liandroid.R;

public class SlideMenuActivity extends Activity {
  private RecyclerView mRecyclerView;
  private List<MyBean>
      mMyBeans;
  private SlideMenuAdapter mAdapter;
  private SlideLayout mSlideLayout;
  private SlideMenuCallBack mCallBack = new SlideMenuCallBack() {
    @Override
    public void onClickContentItem() {
      Toast.makeText(SlideMenuActivity.this, "你点击了我", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickMenuItem(int position) {
      removeData(position);
      mAdapter.notifyDataSetChanged();
    }
  };
  private OnStateChangedListener mOnStateChangedListener = new OnStateChangedListener() {
    @Override
    public void onClose(SlideLayout layout) {
      if (mSlideLayout == layout) {
        mSlideLayout = null;
      }
    }

    @Override
    public void onDown(SlideLayout layout) {
      if(mSlideLayout != null && mSlideLayout != layout){
        mSlideLayout.closeMenu();
      }
    }

    @Override
    public void onOpen(SlideLayout layout) {
      mSlideLayout = layout;
    }
  };

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, SlideMenuActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_slid_menu);
    mRecyclerView = findViewById(R.id.slid_menu_recycler_view);
    initData();
    mAdapter = new SlideMenuAdapter(mMyBeans, mCallBack,mOnStateChangedListener);
    LinearLayoutManager manager = new LinearLayoutManager(SlideMenuActivity.this);
    manager.setOrientation(RecyclerView.VERTICAL);
    mRecyclerView.setLayoutManager(new LinearLayoutManager(SlideMenuActivity.this));
    mRecyclerView.setAdapter(mAdapter);
  }

  public void initData() {
    mMyBeans = new ArrayList<>();
    for (int i = 0; i < 100; i++) {
      mMyBeans.add(new MyBean("content" + i));
    }
  }

  public void removeData(int position) {
      mMyBeans.remove(position);
  }
}