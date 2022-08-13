package com.example.triviaapp.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Category.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase instance;

    public static AppDatabase getInstance(Context context){
        if(instance != null) {
            return instance;
        }
        else{
            instance = Room.databaseBuilder(context, AppDatabase.class, "user-database")
                    .build();
            return instance;
        }
    }
    public abstract CategoryDAO categoryDAO();
}
