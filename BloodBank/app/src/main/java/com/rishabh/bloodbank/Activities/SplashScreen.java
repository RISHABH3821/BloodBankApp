package com.rishabh.bloodbank.Activities;

import android.content.Intent;
import android.os.Handler;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import com.rishabh.bloodbank.R;

public class SplashScreen extends AppCompatActivity {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_splash_screen);
    Handler handler = new Handler();
    handler.postDelayed(new Runnable() {
      @Override
      public void run() {
        startActivity(new Intent(SplashScreen.this, LoginActivity.class));
      }
    }, 1500);
  }
}
