package com.example.liandroid.Contactrecyclerview;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;
import com.example.liandroid.Contactrecyclerview.recycler.adapter.ContactListAdapter;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListBaseItem;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListContactItem;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListFirstLetterItem;


public class ContactListActivity extends AppCompatActivity {

  private RecyclerView mRecyclerView;
  private TextView mLetterTextView;
  private LettersView mLettersView;
  private Handler mHandler = new Handler();
  private ArrayList<Person> mPersonArrayList;
  private ContactListAdapter mAdapter;
  LinearLayoutManager mManager;

  // 列表点击事件
  @NonNull
  private final ContactListCallBack mQuickIndexListCallBack = new ContactListCallBack() {
    @Override
    public void onClickContactItem() {
      Toast.makeText(ContactListActivity.this, "点击了contact", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClickFirstLetterItem() {
      Toast.makeText(ContactListActivity.this, "点击了首字母", Toast.LENGTH_SHORT).show();
    }
  };

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, ContactListActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_contact_recycler_view);
    mRecyclerView = findViewById(R.id.recycler_view);
    mLettersView = findViewById(R.id.iv_letters);
    mLetterTextView = findViewById(R.id.tv_letter);
    mLettersView.setOnIndexChangeListener(new LettersView.OnIndexChangeListener() {
      @Override
      public void onIndexChange(String word) {
        updateWord(word);
        updateRecyclerView(word);
      }

    });
    initData();
    mAdapter = new ContactListAdapter(ContactListActivity.this, mQuickIndexListCallBack);
    mRecyclerView.setAdapter(mAdapter);
    mManager = new LinearLayoutManager(ContactListActivity.this);
    mManager.setOrientation(RecyclerView.VERTICAL);
    mRecyclerView.setLayoutManager(mManager);
    mAdapter.setItemList(buildListData());
    mAdapter.setItemList(buildListData());
  }

  /**
   * @param word 点击letterView中的字母跳转到recycleView对用的字母
   */
  private void updateRecyclerView(String word) {
    for (int i = 0; i < mPersonArrayList.size(); i++) {
      String recyclerWord = mPersonArrayList.get(i).getPinyin().substring(0, 1);
      if (word.equals(recyclerWord)) {
        MoveToPosition(mManager,i);
        return;
      }
    }
  }
  public static void MoveToPosition(LinearLayoutManager manager,int position){
    manager.scrollToPositionWithOffset(position,0);
    manager.setStackFromEnd(true);
  }

  private void updateWord(String word) {
    mLetterTextView.setVisibility(View.VISIBLE);
    mLetterTextView.setText(word);
    mHandler.postDelayed(new Runnable() {
      @Override
      public void run() {
        mLetterTextView.setVisibility(View.GONE);
      }
    }, 3000);
  }

  // 构建列表的UI数据
  private List<ContactListBaseItem> buildListData() {
    List<ContactListBaseItem> itemList = new ArrayList<>();
    // 上一个首字母
    char lastFistLetter = 0;
    // 遍历所有的person
    for (Person person : mPersonArrayList) {
      char currentFirstLetter = person.getPinyin().charAt(0);
      // 过滤相同首字母的item
      if (currentFirstLetter != lastFistLetter) {
        // 添加首字母
        itemList.add(new ContactListFirstLetterItem(currentFirstLetter));
        // 首字母变化的时候更新下
        lastFistLetter = currentFirstLetter;
      }

      // 联系人item
      itemList.add(new ContactListContactItem(person));
    }
    return itemList;
  }

  private void initData() {
    mPersonArrayList = new ArrayList<>();
    mPersonArrayList.add(new Person("张晓飞"));
    mPersonArrayList.add(new Person("杨光福"));
    mPersonArrayList.add(new Person("胡继群"));
    mPersonArrayList.add(new Person("刘畅"));

    mPersonArrayList.add(new Person("钟泽兴"));
    mPersonArrayList.add(new Person("尹革新"));
    mPersonArrayList.add(new Person("安传鑫"));
    mPersonArrayList.add(new Person("张骞壬"));

    mPersonArrayList.add(new Person("温松"));
    mPersonArrayList.add(new Person("李凤秋"));
    mPersonArrayList.add(new Person("刘甫"));
    mPersonArrayList.add(new Person("娄全超"));
    mPersonArrayList.add(new Person("张猛"));

    mPersonArrayList.add(new Person("王英杰"));
    mPersonArrayList.add(new Person("李振南"));
    mPersonArrayList.add(new Person("孙仁政"));
    mPersonArrayList.add(new Person("唐春雷"));
    mPersonArrayList.add(new Person("牛鹏伟"));
    mPersonArrayList.add(new Person("姜宇航"));

    mPersonArrayList.add(new Person("刘挺"));
    mPersonArrayList.add(new Person("张洪瑞"));
    mPersonArrayList.add(new Person("张建忠"));
    mPersonArrayList.add(new Person("侯亚帅"));
    mPersonArrayList.add(new Person("刘帅"));

    mPersonArrayList.add(new Person("乔竞飞"));
    mPersonArrayList.add(new Person("徐雨健"));
    mPersonArrayList.add(new Person("吴亮"));
    mPersonArrayList.add(new Person("王兆霖"));

    mPersonArrayList.add(new Person("阿三"));
    mPersonArrayList.add(new Person("李博俊"));
    mPersonArrayList.add(new Person("潘金莲"));
    mPersonArrayList.add(new Person("冯程程"));
    mPersonArrayList.add(new Person("龚自珍"));
    mPersonArrayList.add(new Person("孔令辉"));
    mPersonArrayList.add(new Person("马蓉"));
    mPersonArrayList.add(new Person("彭德怀"));
    mPersonArrayList.add(new Person("任盈盈"));
    mPersonArrayList.add(new Person("孙悟空"));

    for (Person person : mPersonArrayList) {
      person.setPinyin(PinYinUtils.getPinYin(person.getName()));
    }

    Collections.sort(mPersonArrayList, new Comparator<Person>() {
      @Override
      public int compare(Person person, Person t1) {
        return person.getPinyin().compareTo(t1.getPinyin());
      }
    });
  }
}