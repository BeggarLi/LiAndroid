package com.example.liandroid.CustomTest.PopupWindow;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.icu.text.Transliterator;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

public class PopupWindowActivity extends AppCompatActivity {

  private EditText mEditText_input;
  private ImageView mImageView_down_arrow;
  private PopupWindow mPopupWindow;
  private ListView mListView;
  private ArrayList<String> mMessages;
  private MyAdapter mMyAdapter;

  public static void start(Activity activity){
    Intent intent = new Intent(activity,PopupWindowActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_popup_window);
    mEditText_input = findViewById(R.id.et_input);
    mImageView_down_arrow = findViewById(R.id.iv_down_arrow);


    mEditText_input.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View view) {
        if(mPopupWindow == null){
          mPopupWindow = new PopupWindow(PopupWindowActivity.this);
          mPopupWindow.setWidth(mEditText_input.getWidth());
          mPopupWindow.setContentView(mListView);
          mPopupWindow.setFocusable(true);//设置焦点
        }
        mPopupWindow.showAsDropDown(mEditText_input,0,0);
      }
    });

    mListView = new ListView(this);

    mListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
        String messages = mMessages.get(position);
        mEditText_input.setText(messages);
        if(mPopupWindow != null && mPopupWindow.isShowing()){
            mPopupWindow.dismiss();
            mPopupWindow = null;
        }
      }
    });
    //设置适配器准备数据
    mMessages = new ArrayList<>();
    for(int i = 0; i < 10; i++){
      mMessages.add(i+"--aaaaaaaaaa--"+i);
    }
    mMyAdapter = new MyAdapter();
    mListView.setAdapter(mMyAdapter);
  }

  class MyAdapter extends BaseAdapter{

    @Override
    public int getCount() {
      return mMessages.size();
    }

    @Override
    public Object getItem(int i) {
      return null;
    }

    @Override
    public long getItemId(int i) {
      return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {
      ViewHolder mViewHolder;
      if(view == null){
        view = View.inflate(PopupWindowActivity.this,R.layout.popup_window_item,null);
        mViewHolder = new ViewHolder();
        mViewHolder.mImageView_delete = view.findViewById(R.id.iv_delete);
        mViewHolder.mTextViewMessage = view.findViewById(R.id.tv_message);
        view.setTag(mViewHolder);
      }else {
        mViewHolder = (ViewHolder) view.getTag();
      }

      final String message = mMessages.get(position);
      mViewHolder.mTextViewMessage.setText(message);

      mViewHolder.mImageView_delete.setOnClickListener(v -> {
        //删除
        mMessages.remove(message);
        //刷新
        mMyAdapter.notifyDataSetChanged();
      });
      return view;
    }
  }

  static  class ViewHolder{
    TextView mTextViewMessage;
    ImageView mImageView_delete;
  }
}