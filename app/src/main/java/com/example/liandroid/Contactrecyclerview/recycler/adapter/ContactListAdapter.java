package com.example.liandroid.Contactrecyclerview.recycler.adapter;

import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.Contactrecyclerview.ContactListCallBack;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListBaseItem;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListContactItem;
import com.example.liandroid.Contactrecyclerview.recycler.item.ContactListFirstLetterItem;
import com.example.liandroid.Contactrecyclerview.recycler.viewholder.ContactListBaseViewHolder;
import com.example.liandroid.Contactrecyclerview.recycler.viewholder.ContactListContactItemViewHolder;
import com.example.liandroid.Contactrecyclerview.recycler.viewholder.ContactListFirstLetterItemViewHolder;
import com.example.liandroid.R;

/**
 * author: 13482
 * created on: 2021/8/30 23:36
 * description:
 */
public class ContactListAdapter extends RecyclerView.Adapter<ContactListBaseViewHolder> {

  // 所有的数据
  @Nullable
  private List<ContactListBaseItem> mItemList;
  @NonNull
  private Context mContext;
  @NonNull
  private ContactListCallBack mListListener;


  public ContactListAdapter(
      @NonNull Context context,
      @NonNull ContactListCallBack listListener) {
    mContext = context;
    mListListener = listListener;
  }

  public void setItemList(
      @NonNull List<ContactListBaseItem> itemList) {
    mItemList = itemList;
    notifyDataSetChanged();
  }

  @Override
  public int getItemViewType(int position) {
    // TODO: 2021/8/31 列表为空
    ContactListBaseItem item = mItemList.get(position);
    if (item != null) {
      return item.getType();
    }
    return super.getItemViewType(position);
  }

  @NonNull
  @Override
  public ContactListBaseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    // 首字母
    if (viewType == ItemType.FIRST_LETTER) {
      return new ContactListFirstLetterItemViewHolder(
          View.inflate(mContext, R.layout.quick_list_first_letter_item_layout, null),
          mListListener);
    }
    // 默认返回联系人类型
    return new ContactListContactItemViewHolder(
        View.inflate(mContext, R.layout.quick_index_list_contact_item_layout, null),
        mListListener);
  }

  @Override
  public void onBindViewHolder(@NonNull ContactListBaseViewHolder holder, int position) {
    if (mItemList == null) {
      return;
    }
    ContactListBaseItem item = mItemList.get(position);
    if (item == null) {
      return;
    }
    switch (item.getType()) {
      case ItemType.FIRST_LETTER:
        ((ContactListFirstLetterItemViewHolder) holder)
            .bind((ContactListFirstLetterItem) item);
        return;
      default:
        ((ContactListContactItemViewHolder) holder)
            .bind((ContactListContactItem) item);
    }
  }

  @Override
  public int getItemCount() {
    return mItemList == null ? 0 : mItemList.size();
  }

  // 类型
  public @interface ItemType {
    int FIRST_LETTER = 0; // 姓名首字母
    int CONTACT = 1; // 联系人
  }

}
