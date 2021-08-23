package com.example.liandroid.listview;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;

import com.example.liandroid.R;

public class FruitAdapter extends ArrayAdapter<Fruit> {
  private int resourceId;

  public FruitAdapter(@NonNull Context context,  int textViewResourceId, @NonNull List<Fruit> objects) {
    super(context, textViewResourceId, objects);
    this.resourceId = textViewResourceId;
  }
  public View getView(int position, View coverView, ViewGroup parent){
    if(position == 0){
      return LayoutInflater.from(getContext()).inflate(R.layout.list_head_view_layout,parent,false);
    }else if(position == getCount()-1){
      return LayoutInflater.from(getContext()).inflate(R.layout.list_foot_view_layout,parent,false);
    } else{
      Fruit fruit = getItem(position);
      View view = LayoutInflater.from(getContext()).inflate(resourceId,parent,false);
      ImageView fruitImage = (ImageView)view.findViewById(R.id.fruit_image);
      TextView fruitName = (TextView)view.findViewById(R.id.fruit_name);
      fruitImage.setImageResource(fruit.getImageId());
      fruitName.setText(fruit.getName());
      return view;
    }
  }
}

