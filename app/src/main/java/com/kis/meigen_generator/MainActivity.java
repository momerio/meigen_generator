package com.kis.meigen_generator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTheme(R.style.Theme_Meigen_generator);
        setContentView(R.layout.activity_main);
        setTitle("名言ジェネレーター");
    }
}