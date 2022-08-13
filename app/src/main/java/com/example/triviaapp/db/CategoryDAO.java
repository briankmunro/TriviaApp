package com.example.triviaapp.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface CategoryDAO {

    @Query("select * from Category")
    LiveData<List<Category>> getAll();

    @Query("select * from Category where name =:name")
    List<Category> getByName(String name);

    @Update
    void update(Category category);

    @Delete
    void delete(Category category);

    @Insert
    void insert(Category... categories);

    @Query("DELETE FROM Category")
    void clearAllTables();
}
