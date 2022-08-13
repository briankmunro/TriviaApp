package com.example.triviaapp;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.triviaapp.db.Category;

import java.util.List;

public class CategoryRecyclerViewAdapter extends RecyclerView.Adapter<CategoryRecyclerViewAdapter.ViewHolder> {

    public final List<Category> categories;

    public CategoryRecyclerViewAdapter(List<Category> categories) {
        this.categories = categories;
    }

    public void addItems(List<Category> categories) {
        this.categories.clear();
        this.categories.addAll(categories);
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        public View view;
        public Category category;
        public TextView txtCatname, txtRatio, txtPercentage;

        public ViewHolder(@NonNull View itemView){
            super(itemView);
            view = itemView;
            txtCatname = view.findViewById(R.id.riTextCatName);
            txtRatio = view.findViewById(R.id.riTextRatio);
            txtPercentage = view.findViewById(R.id.riTextPercentage);
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_item, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        final Category category = categories.get(position);
        if(category != null){
            double percentage = 100 * ((double)category.getCorrectQuestions() / (double) category.getTotalQuestions());
            holder.txtCatname.setText(category.getName());
            holder.txtRatio.setText(String.valueOf(category.getCorrectQuestions()) + "/" +
                    String.valueOf(category.getTotalQuestions()));
            holder.txtPercentage.setText(String.format("%.1f", percentage) + "%");
        }

    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
}
