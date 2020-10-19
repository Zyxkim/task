package com.zyxkim.task;

import android.content.res.Configuration;
import android.graphics.Color;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class NumsFragment extends Fragment {

    private int counter = 100;
    private final ArrayList<DataSource> listData = new ArrayList<>();
    private final MyAdapter adapter = new MyAdapter(listData);

    public NumsFragment() {
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(savedInstanceState != null)
            counter = savedInstanceState.getInt("data");

        for (int i = 0; i <= counter-1; i++)
            listData.add(new DataSource(Integer.toString(i+1)));
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
        list.setAdapter(adapter);

        int columns;
        if (getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            columns = 4;
        } else {
            columns = 3;
        }
        list.setLayoutManager(new GridLayoutManager(view.getContext(), columns));

        butOpen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                counter++;
                listData.add(new DataSource(Integer.toString(counter)));
                adapter.notifyItemInserted(counter - 1);
            }
        });
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("data", counter);
    }

    class MyAdapter extends RecyclerView.Adapter<MyHolder> {
        private final ArrayList<DataSource> listDataAdapter;

        public MyAdapter(ArrayList<DataSource> listDataAdapter) {
            this.listDataAdapter = listDataAdapter;
        }

        @NonNull
        @Override
        public MyHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.current_num, parent, false);
            return new MyHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull MyHolder holder, int position) {
            holder.textView.setText(listDataAdapter.get(position).number);
            if ((position + 1) % 2 == 0) {
                holder.textView.setTextColor(Color.RED);
            } else {
                holder.textView.setTextColor(Color.BLUE);
            }
        }

        @Override
        public int getItemCount() {
            return listDataAdapter.size();
        }
    }

    class MyHolder extends RecyclerView.ViewHolder {
        final TextView textView;

        public MyHolder(@NonNull View itemView) {
            super(itemView);

            textView = itemView.findViewById(R.id.number);

            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Fragment fragment = new CurrentNumFragment();
                    ((CurrentNumFragment) fragment).setNumber(textView.getText().toString());
                    FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();
                    fragmentTransaction.replace(R.id.placeholder, fragment);
                    fragmentTransaction.addToBackStack(null);
                    fragmentTransaction.commit();
                }
            });
        }
    }
}
