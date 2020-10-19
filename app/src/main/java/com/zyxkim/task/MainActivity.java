package com.zyxkim.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

//динамическое создание активити

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null)
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.placeholder, new NumsFragment())
                    .commit();
    }
}
