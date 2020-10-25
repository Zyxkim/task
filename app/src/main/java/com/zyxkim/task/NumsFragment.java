package com.zyxkim.task;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumsFragment extends Fragment {

    private static final int START_LIST = 100;
    private static final int HORIZONTAL_COLUMNS = 4;
    private static final int VERTICAL_COLUMNS = 3;
    private final String EXTRA = "EXTRA";

    private int mCounter = START_LIST;
    private final ArrayList<DataSource> LIST_DATA = new ArrayList<>();
    private final MyAdapter ADAPTER = new MyAdapter(LIST_DATA);

    public NumsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
            mCounter = savedInstanceState.getInt(EXTRA);

        for (int i = 0; i <= mCounter-1; i++)
            LIST_DATA.add(new DataSource(Integer.toString(i+1)));
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_nums, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        Button butOpen = view.findViewById(R.id.addBtn);
        RecyclerView list = view.findViewById(R.id.recyclerView);
        list.setAdapter(ADAPTER);

        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            list.setLayoutManager(new GridLayoutManager(view.getContext(), HORIZONTAL_COLUMNS));
        } else {
            list.setLayoutManager(new GridLayoutManager(view.getContext(), VERTICAL_COLUMNS));
        }

        butOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mCounter++;
                LIST_DATA.add(new DataSource(Integer.toString(mCounter)));
                ADAPTER.notifyItemInserted(mCounter - 1);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(EXTRA, mCounter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private final ArrayList<DataSource> LIST_DATA_ADAPTER;

        public MyAdapter(ArrayList<DataSource> listDataAdapter) {
            this.LIST_DATA_ADAPTER = listDataAdapter;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_num, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, final int position) {
            holder.TEXT_VIEW.setText(LIST_DATA_ADAPTER.get(position).NUMBER);
            if ((position + 1) % 2 == 0) {
                holder.TEXT_VIEW.setTextColor(Color.RED);
            } else {
                holder.TEXT_VIEW.setTextColor(Color.BLUE);
            }

            holder.TEXT_VIEW.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (getActivity() instanceof MainActivity) {
                        ((MainActivity) getActivity()).numberClickListener(position + 1);
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return LIST_DATA_ADAPTER.size();
        }
    }

    public static class MyHolder extends RecyclerView.ViewHolder {
        final TextView TEXT_VIEW;

        public MyHolder(@NonNull View itemView) {
            super(itemView);
            TEXT_VIEW = itemView.findViewById(R.id.number);
        }
    }
}
