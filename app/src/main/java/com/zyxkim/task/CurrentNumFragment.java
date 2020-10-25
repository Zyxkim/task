package com.zyxkim.task;

import android.graphics.Color;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CurrentNumFragment extends Fragment {

    private final String EXTRA = "EXTRA";

    private TextView mTextView;

    public CurrentNumFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_current_num, container, false);
        mTextView = view.findViewById(R.id.current_number);
        Bundle bundle = getArguments();

        int number = -1;
        if (bundle != null) {
            number = bundle.getInt(EXTRA);
            mTextView.setText(String.valueOf(number));
        }

        if (number % 2 == 0) {
            mTextView.setTextColor(Color.RED);
        } else {
            mTextView.setTextColor(Color.BLUE);
        }

        return view;
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(EXTRA, (String) mTextView.getText());
    }
}
