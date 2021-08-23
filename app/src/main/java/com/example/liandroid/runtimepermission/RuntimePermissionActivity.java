package com.example.liandroid.runtimepermission;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.liandroid.R;

public class RuntimePermissionActivity extends AppCompatActivity {

  private Button mButton;

  public static void start(@NonNull Activity activity) {
    Intent intent = new Intent(activity, RuntimePermissionActivity.class);
    activity.startActivity(intent);
  }

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_runtime_permission);
    mButton = findViewById(R.id.runtime_permission_page_button);
    mButton.setOnClickListener(v -> {
      if (ContextCompat.checkSelfPermission(
          RuntimePermissionActivity.this,
          Manifest.permission.CALL_PHONE)
          != PackageManager.PERMISSION_GRANTED) {
        ActivityCompat.requestPermissions(
            RuntimePermissionActivity.this,
            new String[]{Manifest.permission.CALL_PHONE},
            1);
      } else {
        call();
      }
    });
  }

  private void call() {
    try {
      Intent intent = new Intent(Intent.ACTION_CALL);
      intent.setData(Uri.parse("tel:10086"));
      startActivity(intent);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public void onRequestPermissionsResult(int requestCode,
      @NonNull String[] permissions,
      @NonNull int[] grantResults) {
    super.onRequestPermissionsResult(requestCode, permissions, grantResults);

    switch (requestCode) {
      case 1:
        if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
          call();
        } else {
          Toast.makeText(RuntimePermissionActivity.this, "You denied the permission",
              Toast.LENGTH_SHORT).show();
        }
        break;
      default:
    }
  }
}