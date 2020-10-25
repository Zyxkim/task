package com.zyxkim.task;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    private final String EXTRA = "EXTRA";

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

    public void numberClickListener(int number) {
        CurrentNumFragment fragment = new CurrentNumFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(EXTRA, number);
        fragment.setArguments(bundle);
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.placeholder, fragment)
                .addToBackStack(null)
                .commit();
    }
}
