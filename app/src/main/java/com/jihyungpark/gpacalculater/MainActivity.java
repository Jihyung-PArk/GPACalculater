package com.jihyungpark.gpacalculater;

import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;


public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //after 5 sec, menu activity open
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent intent = new Intent(MainActivity.this, MenuPageActivity.class);
            startActivity(intent);
            finish();
        }, 5000);
    }

}