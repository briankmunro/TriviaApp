package com.example.triviaapp.db;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Category {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private int correctQuestions;
    private int totalQuestions;

    public Category(String name) {
        this.name = name;
        this.correctQuestions = 0;
        this.totalQuestions = 0;
    }

    @Override
    public String toString() {
        return "Category{" +
                "name='" + name + '\'' +
                ", correctQuestions=" + correctQuestions +
                ", totalQuestions=" + totalQuestions +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCorrectQuestions() {
        return correctQuestions;
    }

    public void setCorrectQuestions(int correctQuestions) {
        this.correctQuestions = correctQuestions;
    }

    public int getTotalQuestions() {
        return totalQuestions;
    }

    public void setTotalQuestions(int totalQuestions) {
        this.totalQuestions = totalQuestions;
    }
}
