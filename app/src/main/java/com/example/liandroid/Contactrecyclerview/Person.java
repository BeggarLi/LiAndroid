package com.example.liandroid.Contactrecyclerview;

/**
 * author: 13482
 * created on: 2021/8/31 18:28
 * description:
 */
public class Person {
  private String mName;
  private String mPinyin;

  public Person(String name, String pinyin) {
    mName = name;
    mPinyin = pinyin;
  }

  public Person(String name) {
    mName = name;
  }

  public String getName() {
    return mName;
  }

  public void setName(String name) {
    mName = name;
  }

  public String getPinyin() {
    return mPinyin;
  }

  public void setPinyin(String pinyin) {
    mPinyin = pinyin;
  }

}
