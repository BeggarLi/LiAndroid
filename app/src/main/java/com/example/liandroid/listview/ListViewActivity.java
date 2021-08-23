package com.example.liandroid.listview;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

public class ListViewActivity extends AppCompatActivity {

  private List<Fruit> fruitList = new ArrayList<>();

  public static void start(@NonNull Activity activity){
    Intent intent = new Intent(activity,ListViewActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_list_view);

    initFruits();
    FruitAdapter adapter = new FruitAdapter(ListViewActivity.this,R.layout.firut_item,fruitList);
    ListView listView = findViewById(R.id.listView);
    listView.setAdapter(adapter);

    listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
      @Override
      public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Fruit fruit = fruitList.get(position);
        Toast.makeText(ListViewActivity.this,fruit.getName(),Toast.LENGTH_SHORT).show();
      }
    });

  }
  private void initFruits(){
    for(int i = 0;i < 2; i++){
      Fruit apple = new Fruit("Apple",R.drawable.apple);
      fruitList.add(apple);

      Fruit banana = new Fruit("Banana",R.drawable.abnana);
      fruitList.add(banana);

      Fruit cherry = new Fruit("Cherry",R.drawable.cherry);
      fruitList.add(cherry);

      Fruit grape = new Fruit("Grape",R.drawable.grape);
      fruitList.add(grape);

      Fruit Mango = new Fruit("Mango", R.drawable.mango);
      fruitList.add(Mango);

      Fruit orange = new Fruit("Orange",R.drawable.orange);
      fruitList.add(orange);

      Fruit pear = new Fruit("Pear",R.drawable.pear);
      fruitList.add(pear);

      Fruit strawberry = new Fruit("Strawberry",R.drawable.strawberry);
      fruitList.add(strawberry);

      Fruit watermelon = new Fruit("Watermelon",R.drawable.watermelon);
      fruitList.add(watermelon);
    }

  }
}
