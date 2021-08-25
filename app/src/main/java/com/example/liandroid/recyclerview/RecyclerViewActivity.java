package com.example.liandroid.recyclerview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;

public class RecyclerViewActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private ContactListAdapter mContactListAdapter;
  private List<Contact> mContacts = new ArrayList<>();

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, RecyclerViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_recycler_view);
    initData();
    initView();
  }

  private void initData() {
    for (int i = 0; i < 20; ++i) {
      mContacts.add(new Contact("hello,hello"));
    }
  }

  private void initView() {
    mRecyclerView = findViewById(R.id.recycle_view);
    mContactListAdapter = new ContactListAdapter(mContacts, RecyclerViewActivity.this);
    mRecyclerView.setAdapter(mContactListAdapter);
  }
}