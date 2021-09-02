package com.example.liandroid.Contactrecyclerview.recycler.viewholder;

import android.view.View;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListBaseItem;

/**
 * author: 13482
 * created on: 2021/8/30 23:25
 * description:
 */
public abstract class ContactListBaseViewHolder<DATA extends ContactListBaseItem>
    extends RecyclerView.ViewHolder {

  // viewHolder要绑定的数据
  private DATA mData;

  public ContactListBaseViewHolder(@NonNull View itemView) {
    super(itemView);
  }

  // 绑定
  public abstract void bind(@NonNull DATA data);
}
