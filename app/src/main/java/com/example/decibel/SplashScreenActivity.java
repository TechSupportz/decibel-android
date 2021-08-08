package com.example.decibel;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class SplashScreenActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //app goes to mainactivity after splash screen finishes
        Intent intent = new Intent(this, MainActivity.class);
        this.startActivity(intent);
        //destroys activity
        finish();
    }
}
