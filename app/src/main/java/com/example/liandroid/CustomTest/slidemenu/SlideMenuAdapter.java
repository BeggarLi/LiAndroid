package com.example.liandroid.customtest.slidemenu;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.liandroid.R;

/**
 * author: LL
 * created on: 2021/9/1 16:19
 * description:适配器
 */
public class SlideMenuAdapter extends RecyclerView.Adapter<MyViewHolder> {

//  private final Set<>

  private List<MyBean> mMyBeans;
  private SlideMenuCallBack mListener;
  private OnStateChangedListener mStateChangedListener;

  public SlideMenuAdapter(List<MyBean> myBeans,
      SlideMenuCallBack listener,
      OnStateChangedListener stateChangedListener) {
    mMyBeans = myBeans;
    mListener = listener;
    mStateChangedListener = stateChangedListener;
  }

  @Override
  public int getItemCount() {
    return mMyBeans == null ? 0 : mMyBeans.size();
  }

  @NonNull
  @Override
  public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view = View.inflate(parent.getContext(), R.layout.slid_menu_item_layout, null);
    SlideLayout slideLayout = (SlideLayout) view;
    slideLayout.setOnStateChangedListener(mStateChangedListener);
    MyViewHolder myViewHolder = new MyViewHolder(view, mListener);
    return myViewHolder;
  }

  @Override
  public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
    MyBean myBean = mMyBeans.get(position);
    holder.bind(myBean, position);
  }

  @Override
  public void onViewRecycled(@NonNull MyViewHolder holder) {
    super.onViewRecycled(holder);
  }
}
