package com.example.liandroid.recyclerview;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;

/**
 * author: LL
 * created on: 2021/8/12 21:00
 * description:UI单元
 */
public class ContactViewHolder extends RecyclerView.ViewHolder {
  private TextView mNameView;

  public ContactViewHolder(@NonNull View itemView) {
    super(itemView);
    mNameView = itemView.findViewById(R.id.contact_item_name_view);
  }

  public void binData(Contact contact) {
    mNameView.setText(contact.mName);
  }
}
