package com.example.triviaapp;

import android.content.Context;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.example.triviaapp.db.AppDatabase;
import com.example.triviaapp.db.Category;

import java.util.List;

public class StatsViewModel extends ViewModel {
    private LiveData<List<Category>> categoryList;

    public LiveData<List<Category>> getCategoryList(Context context){
        if (categoryList != null){
            return categoryList;
        }else{
            return categoryList = AppDatabase.getInstance(context).categoryDAO().getAll();
        }
    }
}
