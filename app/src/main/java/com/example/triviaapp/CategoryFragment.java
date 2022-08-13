package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class CategoryFragment extends Fragment {

    View view;
    CategoryButtonClicked listener;
    Button btnGeneralKnowledge, btnBooks, btnFilm, btnMusic, btnVideoGames, btnTelevision,
        btnSports, btnMathematics, btnGeography, btnMythology, btnHistory, btnScience,
        btnArt, btnPolitics, btnComputers, btnAnimals, btnCelebrities;


    public interface CategoryButtonClicked{
        void CategorySelected(int category, String catString);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof CategoryButtonClicked){
            listener = (CategoryButtonClicked) context;
        }else{
            throw new ClassCastException(context.toString() + " Must implement CategoryButtonClicked");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_category, container, false);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnGeneralKnowledge = (Button) view.findViewById(R.id.btnGeneralKnowledge);
        btnGeneralKnowledge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(9, btnGeneralKnowledge.getText().toString());
            }
        });
        btnBooks = (Button) view.findViewById(R.id.btnBooks);
        btnBooks.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(10, btnBooks.getText().toString());
            }
        });
        btnFilm = (Button) view.findViewById(R.id.btnFilm);
        btnFilm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(11, btnFilm.getText().toString());
            }
        });
        btnMusic = (Button) view.findViewById(R.id.btnMusic);
        btnMusic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(12, btnMusic.getText().toString());
            }
        });
        btnVideoGames = (Button) view.findViewById(R.id.btnVideoGames);
        btnVideoGames.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(15, btnVideoGames.getText().toString());
            }
        });
        btnTelevision = (Button) view.findViewById(R.id.btnTelevision);
        btnTelevision.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(14, btnTelevision.getText().toString());
            }
        });
        btnSports = (Button) view.findViewById(R.id.btnSports);
        btnSports.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(21, btnSports.getText().toString());
            }
        });
        btnMathematics = (Button) view.findViewById(R.id.btnMathematics);
        btnMathematics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(19,btnMathematics.getText().toString());
            }
        });
        btnGeography = (Button) view.findViewById(R.id.btnGeography);
        btnGeography.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(22, btnGeography.getText().toString());
            }
        });
        btnMythology = (Button) view.findViewById(R.id.btnMythology);
        btnMythology.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(20, btnMythology.getText().toString());
            }
        });
        btnHistory = (Button) view.findViewById(R.id.btnHistory);
        btnHistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(23, btnHistory.getText().toString());
            }
        });
        btnScience = (Button) view.findViewById(R.id.btnScienceNature);
        btnScience.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(17, btnScience.getText().toString());
            }
        });
        btnArt = (Button) view.findViewById(R.id.btnArt);
        btnArt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(25, btnArt.getText().toString());
            }
        });
        btnPolitics = (Button) view.findViewById(R.id.btnPolitics);
        btnPolitics.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(24, btnPolitics.getText().toString());
            }
        });
        btnComputers = (Button) view.findViewById(R.id.btnComputers);
        btnComputers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(18, btnComputers.getText().toString());
            }
        });
        btnAnimals = (Button) view.findViewById(R.id.btnAnimals);
        btnAnimals.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(27, btnAnimals.getText().toString());
            }
        });
        btnCelebrities = (Button) view.findViewById(R.id.btnCelebrities);
        btnCelebrities.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.CategorySelected(26, btnCelebrities.getText().toString());
            }
        });
    }
}