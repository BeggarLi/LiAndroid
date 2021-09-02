package com.example.liandroid.Contactrecyclerview.recycler.item;

import com.example.liandroid.Contactrecyclerview.recycler.adapter.ContactListAdapter;

/**
 * author: 13482
 * created on: 2021/8/30 23:19
 * description: 列表首字母
 */
public class ContactListFirstLetterItem implements ContactListBaseItem {

  // 首字母
  private char mFirstLetter;

  public ContactListFirstLetterItem(char firstLetter) {
    mFirstLetter = firstLetter;
  }

  @Override
  public int getType() {
    return ContactListAdapter.ItemType.FIRST_LETTER;
  }

  public char getFirstLetter() {
    return mFirstLetter;
  }

  public void setFirstLetter(char firstLetter) {
    mFirstLetter = firstLetter;
  }
}
