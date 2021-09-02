package com.example.liandroid.Contactrecyclerview.recycler.item;

import com.example.liandroid.Contactrecyclerview.Person;
import com.example.liandroid.Contactrecyclerview.recycler.adapter.ContactListAdapter;

/**
 * author: 13482
 * created on: 2021/8/31 18:30
 * description:
 */
public class ContactListContactItem implements ContactListBaseItem {

  private Person mPerson;

  public Person getPerson() {
    return mPerson;
  }

  public void setPerson(Person person) {
    mPerson = person;
  }

  public ContactListContactItem(Person person) {
    mPerson = person;
  }

  @Override
  public int getType() {
    return ContactListAdapter.ItemType.CONTACT;
  }
}
