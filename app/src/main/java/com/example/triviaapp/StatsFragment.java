package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.triviaapp.db.Category;

import java.util.ArrayList;
import java.util.List;


public class StatsFragment extends Fragment {

    View view;
    private RecyclerView recyclerView;
    private CategoryRecyclerViewAdapter categoryRecyclerViewAdapter;
    private int columnCount = 1;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_stats, container, false);
        recyclerView = view.findViewById(R.id.recyclerView);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        Context context = getContext();
        categoryRecyclerViewAdapter = new CategoryRecyclerViewAdapter(new ArrayList<Category>());
        if (columnCount <= 1){
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        }else{
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }

        recyclerView.setAdapter(categoryRecyclerViewAdapter);
        recyclerView.setHasFixedSize(false);

        ViewModelProviders.of(this)
                .get(StatsViewModel.class)
                .getCategoryList(context)
                .observe(this, new Observer<List<Category>>() {
                    @Override
                    public void onChanged(List<Category> categories) {
                        if (categories != null){
                            categoryRecyclerViewAdapter.addItems(categories);
                        }
                    }
                });
    }
}