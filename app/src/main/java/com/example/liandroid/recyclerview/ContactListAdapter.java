package com.example.liandroid.recyclerview;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;

/**
 * author: LL
 * created on: 2021/8/12 20:59
 * description:
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactViewHolder> {
  private List<Contact> mContactList;
  private Context mContext;

  public ContactListAdapter(List<Contact> contactList, Context context) {
    mContactList = contactList;
    mContext = context;
  }

  @NonNull
  @Override
  public ContactViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    return new ContactViewHolder(View.inflate(mContext, R.layout.contact_item_layout, parent));
  }

  @Override
  public void onBindViewHolder(@NonNull ContactViewHolder holder,
      int position) {
    Contact contact = mContactList.get(position);
    holder.binData(contact);
  }

  @Override
  public int getItemCount() {
    return mContactList == null ? 0 : mContactList.size();
  }
}
