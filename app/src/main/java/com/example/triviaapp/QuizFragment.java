package com.example.triviaapp;

import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.triviaapp.db.AppDatabase;
import com.example.triviaapp.db.Category;

import java.net.URLDecoder;
import java.util.List;
import java.util.Random;


public class QuizFragment extends Fragment {

    protected View view;
    private String url;
    private String categoryString;
    private TextView textView;
    private Button btn0, btn1, btn2, btn3;
    private Button[] buttons;
    private TriviaQuestion[] triviaQuestions;
    private int currentQuestion = 0;
    private int correctAnswer;
    protected int numberCorrect = 0;
    private boolean trueFalse = false;
    protected boolean gameOver = true;
    private GetNewQuiz task;
    private URLDecoder decoder = new URLDecoder();
    private QuizEnded listener;
    public interface QuizEnded{
        void QuizCompleted (int totalCorrect);
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof QuizEnded){
            listener = (QuizEnded) context;
        }else{
            throw new ClassCastException(context.toString() + " Must implement QuizEnded");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view = inflater.inflate(R.layout.fragment_quiz, container, false);
        textView = view.findViewById(R.id.textQuestion);
        btn0 = view.findViewById(R.id.btn0);
        btn1 = view.findViewById(R.id.btn1);
        btn2 = view.findViewById(R.id.btn2);
        btn3 = view.findViewById(R.id.btn3);
        buttons = new Button[]{btn0, btn1, btn2, btn3};

        Bundle bundle = this.getArguments();
        if(bundle != null) {
            url = bundle.getString("url");
            categoryString = bundle.getString("category");
        }

        task = new GetNewQuiz(url);
        task.setOnQuestionsImportListener(new GetNewQuiz.OnQuestionsImport() {
            @Override
            public void completedQuestions(TriviaQuestion[] questions) {
                triviaQuestions = questions;
                NextQuestion();
                gameOver = false;
            }
        });
        task.execute("");

        btn0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) CheckAnswer(0);
            }
        });
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) CheckAnswer(1);
            }
        });
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) CheckAnswer(2);
            }
        });
        btn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!gameOver) CheckAnswer(3);
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();

    }

    private void NextQuestion(){
        TriviaQuestion q = triviaQuestions[currentQuestion];
        textView.setText(decoder.decode(q.getQuestion()));
        if (q.getType().toString().equals("boolean")){
            trueFalse = true;
            btn0.setText("True");
            btn1.setText("False");
            btn2.setText("");
            btn3.setText("");

        }else{
            trueFalse = false;
            Random random = new Random();
            correctAnswer = random.nextInt(4);
            int incorrect = 0;
            for (int i = 0; i < 4; i++){
                if (i == correctAnswer){
                    buttons[i].setText(decoder.decode(q.getCorrectAnswer()));
                } else{
                    buttons[i].setText(decoder.decode(q.getWrongAnswers()[incorrect]));
                    incorrect++;
                }
            }

        }
    }

    private void CheckAnswer(int answer){
        String goodAnswer = triviaQuestions[currentQuestion].getCorrectAnswer();
        String message;
        if(trueFalse){
           switch(answer){
               case 0: if (goodAnswer.equals("True")){
                   numberCorrect++;
                   message = "That's right! Good Job.";
               }else{
                   message = "Sorry, this is FALSE.";
               }
               break;
               case 1: if (goodAnswer.equals("False")){
                   numberCorrect++;
                   message = "That's right! Good Job.";
               }else{
                   message = "Sorry, this is TRUE.";
               }
               break;
               default: return;
           }
       } else{
           if (answer == correctAnswer){
               numberCorrect++;
               message = "That's right! Good Job";
           }else{
               message = "Sorry, the correct answer is:\n\n" + decoder.decode(goodAnswer);
           }
       }
        Toast toast = Toast.makeText(getActivity(), message, Toast.LENGTH_LONG);
        toast.show();
        currentQuestion++;
        if (currentQuestion < 10){
            NextQuestion();
        }else{
            gameOver = true;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    Category category;
                    List<Category> categoryList = AppDatabase.getInstance(getContext()).categoryDAO().getByName(categoryString);
                    if (categoryList.isEmpty()){
                        category = new Category(categoryString);
                        category.setCorrectQuestions(numberCorrect);
                        category.setTotalQuestions(10);
                        Log.d("New Category", category.toString());
                        AppDatabase.getInstance(getContext())
                                .categoryDAO()
                                .insert(category);
                    }else{
                        category = categoryList.get(0);
                        category.setCorrectQuestions(category.getCorrectQuestions() + numberCorrect);
                        category.setTotalQuestions(category.getTotalQuestions() + 10);
                        Log.d("Updated Category", category.toString());
                        AppDatabase.getInstance(getContext())
                                .categoryDAO()
                                .update(category);
                    }


                }
            }).start();
            listener.QuizCompleted(numberCorrect);
        }

    }
}