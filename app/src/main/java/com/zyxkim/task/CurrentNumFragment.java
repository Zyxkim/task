package com.zyxkim.task;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class CurrentNumFragment extends Fragment {

    private final String number;

    public CurrentNumFragment(String number) {
        this.number = number;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        setRetainInstance(true);
       View view = inflater.inflate(R.layout.fragment_current_num, container, false);
        TextView mTextView = view.findViewById(R.id.current_number);
        mTextView.setText(number);
        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        View text = getView();
        if (text != null) {
            TextView textView = text.findViewById(R.id.current_number);
            textView.setText(number);
            if (Integer.parseInt(number) % 2 == 0) {
                textView.setTextColor(Color.RED);
            } else {
                textView.setTextColor(Color.BLUE);
            }
        }
    }
}
