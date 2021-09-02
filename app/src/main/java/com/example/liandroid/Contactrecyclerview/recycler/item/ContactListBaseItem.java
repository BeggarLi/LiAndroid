package com.example.liandroid.Contactrecyclerview.recycler.item;

import com.example.liandroid.Contactrecyclerview.recycler.adapter.ContactListAdapter;

/**
 * author: 13482
 * created on: 2021/8/31 18:27
 * description:
 */
public interface ContactListBaseItem {

  // 该item的类型
  @ContactListAdapter.ItemType
  int getType();

}
