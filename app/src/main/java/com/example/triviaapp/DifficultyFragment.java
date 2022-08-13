package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class DifficultyFragment extends Fragment {

    View view;
    DifficultyButtonClicked listener;
    Button btnAnyDifficulty, btnEasy, btnMedium, btnHard;

    public interface DifficultyButtonClicked{
        void DifficultySelected(String difficulty);
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof DifficultyButtonClicked){
            listener = (DifficultyButtonClicked) context;
        }else{
            throw new ClassCastException(context.toString() + " Must implement DifficultyButtonClicked");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view =  inflater.inflate(R.layout.fragment_difficulty, container, false);
        btnAnyDifficulty = view.findViewById(R.id.btnAnyDifficulty);
        btnEasy = view.findViewById(R.id.btnEasy);
        btnMedium = view.findViewById(R.id.btnMedium);
        btnHard = view.findViewById(R.id.btnHard);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        btnAnyDifficulty.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DifficultySelected("");
            }
        });
        btnEasy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DifficultySelected("&difficulty=easy");
            }
        });
        btnMedium.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DifficultySelected("&difficulty=medium");
            }
        });
        btnHard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.DifficultySelected("&difficulty=hard");
            }
        });
    }
}