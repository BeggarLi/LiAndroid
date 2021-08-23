package com.example.liandroid.listview;

/**
 * author: LL
 * created on: 2021/8/12 22:14
 * description:水果类
 */
public class Fruit {
  private String name;
  private int imageId;

  public Fruit(String name, int imageId) {
    this.name = name;
    this.imageId = imageId;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public int getImageId() {
    return imageId;
  }

  public void setImageId(int imageId) {
    this.imageId = imageId;
  }
}
