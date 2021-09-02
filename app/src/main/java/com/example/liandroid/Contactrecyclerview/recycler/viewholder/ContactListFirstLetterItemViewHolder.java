package com.example.liandroid.Contactrecyclerview.recycler.viewholder;

import android.view.View;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.liandroid.Contactrecyclerview.ContactListCallBack;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListFirstLetterItem;
import com.example.liandroid.R;

/**
 * author: 13482
 * created on: 2021/8/30 23:24
 * description:
 */
public class ContactListFirstLetterItemViewHolder
    extends ContactListBaseViewHolder<ContactListFirstLetterItem> {

  private TextView mFirstLetterView;

  // 点击回调
  @NonNull
  private ContactListCallBack mListListener;

  public ContactListFirstLetterItemViewHolder(
      @NonNull View itemView,
      @NonNull ContactListCallBack listListener) {
    super(itemView);
    mListListener = listListener;
    itemView.setOnClickListener(view -> mListListener.onClickFirstLetterItem());

    mFirstLetterView = itemView.findViewById(R.id.quick_index_item_tv);
  }

  @Override
  public void bind(@NonNull ContactListFirstLetterItem item) {
    mFirstLetterView.setText(item.getFirstLetter() + "");
  }


}
