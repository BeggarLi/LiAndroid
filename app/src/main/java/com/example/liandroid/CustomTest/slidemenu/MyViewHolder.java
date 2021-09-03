package com.example.liandroid.customtest.slidemenu;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;

/**
 * author: 13482
 * created on: 2021/9/1 17:06
 * description:
 */
public class MyViewHolder extends RecyclerView.ViewHolder {
  private TextView mContentItem;
  private TextView mMenuItem;

  private int mPosition;

  public MyViewHolder(
      @NonNull View itemView,
      SlideMenuCallBack listener) {
    super(itemView);
    mContentItem = itemView.findViewById(R.id.slid_menu_item_content);
    mMenuItem = itemView.findViewById(R.id.slid_menu_item_menu);
    mContentItem.setOnClickListener(view -> listener.onClickContentItem());
    mMenuItem.setOnClickListener(view -> listener.onClickMenuItem(mPosition));
  }

  public void bind(MyBean myBean,int position) {
    mPosition = position;
    mContentItem.setText(myBean.getName()+"");
  }
}
