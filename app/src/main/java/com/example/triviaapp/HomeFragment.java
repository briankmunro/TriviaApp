package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class HomeFragment extends Fragment {

    Button btnNewGame, btnSeeStats;
    View view;
    private ButtonClicked listener;

    public interface ButtonClicked{
        void BtnNewQuizClicked();
        void BtnSeeStatsClicked();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof ButtonClicked){
            listener = (ButtonClicked) context;
        }else{
            throw new ClassCastException(context.toString() + " Must implement ButtonClicked");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_home, container, false);
        btnNewGame = view.findViewById(R.id.btnNewGame);
        btnSeeStats = view.findViewById(R.id.btnSeeStats);

        btnNewGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.BtnNewQuizClicked();
            }
        });
        btnSeeStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.BtnSeeStatsClicked();
            }
        });
        return view;
    }




}