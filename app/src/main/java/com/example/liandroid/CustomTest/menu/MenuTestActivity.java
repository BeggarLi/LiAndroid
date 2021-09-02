package com.example.liandroid.customtest.menu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.appcompat.app.AppCompatActivity;

import com.example.liandroid.R;

public class MenuTestActivity extends AppCompatActivity {
  private ImageView mHomeView;
  private ImageView mMenuView;
  private RelativeLayout mLevel1View;
  private RelativeLayout mLevel2View;
  private RelativeLayout mLevel3View;
  private MyOnClickListener mMyOnClickListener;

  /**
   * 是否显示第一圈圆环
   * 显示：true
   * 隐藏：false
   */
  public boolean mIsShowLevel1 = true;

  /**
   * 是否显示第二圈圆环
   * 显示：true
   * 隐藏：false
   */
  public boolean mIsShowLevel2 = true;

  /**
   * 是否显示第三圈圆环
   * 显示：true
   * 隐藏：false
   */
  public boolean isShowLevel3 = true;

  public static void start(Activity activity) {
    Intent intent = new Intent(activity,MenuTestActivity.class);
    activity.startActivity(intent);
  }


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_menu_test);
    mHomeView = findViewById(R.id.icon_home);
    mMenuView = findViewById(R.id.icon_menu);
    mLevel1View = findViewById(R.id.level1);
    mLevel2View = findViewById(R.id.level2);
    mLevel3View = findViewById(R.id.level3);
    mMyOnClickListener = new MyOnClickListener();

    mHomeView.setOnClickListener(mMyOnClickListener);
    mMenuView.setOnClickListener(mMyOnClickListener);

  }

  class MyOnClickListener implements View.OnClickListener{

    @Override
    public void onClick(View v) {
      switch (v.getId()){
        case R.id.icon_home:
          //如果三级菜单和二级菜单是显示，都设置隐藏
          if(mIsShowLevel2){
            //隐藏二级菜单
            mIsShowLevel2 = false;
            Tools.hideView(mLevel2View);
            if(isShowLevel3){
              //隐藏三级菜单
              isShowLevel3 = false;
              Tools.hideView(mLevel3View,200);
            }
          }else {
            //显示二级菜单
            mIsShowLevel2 = true;
            Tools.showView(mLevel2View);
          }
          break;
        case R.id.icon_menu:
          if(isShowLevel3){
            //隐藏
            isShowLevel3 = false;
            Tools.hideView(mLevel3View);
          }else{
            //显示
            isShowLevel3 = true;
            Tools.showView(mLevel3View);
          }
          break;
      }
    }
  }
}
