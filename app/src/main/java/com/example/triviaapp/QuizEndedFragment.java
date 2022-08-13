package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class QuizEndedFragment extends Fragment {

    View view;
    Button btnHome, btnSeeStats;
    TextView textScore;
    private QEButtonClicked listener;
    private String totalCorrect;

    public interface QEButtonClicked{
        void QEButtonClickedSeeStats();
        void QEButtonClickedHome();
    }

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if (context instanceof QEButtonClicked){
            listener = (QEButtonClicked) context;
        }else{
            throw new ClassCastException(context.toString() + "Must implement QEButtonClicked");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quiz_ended, container, false);
        btnHome = view.findViewById(R.id.btnHome);
        btnSeeStats = view.findViewById(R.id.btnQEStats);
        textScore = view.findViewById(R.id.txtScore);
        Bundle bundle = this.getArguments();
        if(bundle != null) totalCorrect = String.valueOf(bundle.getInt("totalCorrect")) + "/10";
        textScore.setText(totalCorrect);
        btnSeeStats.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.QEButtonClickedSeeStats();
            }
        });
        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.QEButtonClickedHome();
            }
        });
        return view;
    }
}