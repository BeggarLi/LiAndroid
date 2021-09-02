package com.example.liandroid.Contactrecyclerview.recycler.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.liandroid.Contactrecyclerview.ContactListCallBack;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListContactItem;
import com.example.liandroid.R;

/**
 * author: 13482
 * created on: 2021/8/30 23:33
 * description: 联系人viewHolder
 */
public class ContactListContactItemViewHolder
    extends ContactListBaseViewHolder<ContactListContactItem> {

  private TextView mNameView;

  public ContactListContactItemViewHolder(
      @NonNull View itemView,
      @NonNull ContactListCallBack callBack) {
    super(itemView);
    itemView.setOnClickListener(view -> callBack.onClickContactItem());

    mNameView = itemView.findViewById(R.id.quick_index_item_contact_name_view);
  }

  @Override
  public void bind(@NonNull ContactListContactItem item) {
    mNameView.setText(item.getPerson().getName());
  }
}
