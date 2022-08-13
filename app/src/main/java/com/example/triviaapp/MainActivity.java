package com.example.triviaapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.FragmentManager;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import com.example.triviaapp.db.AppDatabase;
import com.example.triviaapp.db.Category;

import java.util.List;

public class MainActivity extends AppCompatActivity implements
        HomeFragment.ButtonClicked,
        CategoryFragment.CategoryButtonClicked,
        DifficultyFragment.DifficultyButtonClicked,
        QuizFragment.QuizEnded,
        QuizEndedFragment.QEButtonClicked,
        ClearAllDialogFragment.ClearAllConfirmed {

    FragmentManager fm;
    Toolbar toolbar;
    int category;
    String url;
    QuizFragment quizFragment;
    String currentCategory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        fm = getSupportFragmentManager();
        fm.beginTransaction()
                .add(R.id.fragmentContainer, new HomeFragment(), "HomeFragment")
                .commit();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()){
            case R.id.menuHome:
                Home();
                return true;
            case R.id.menuNewQuiz:
                NewQuiz();
                return true;
            case R.id.menuSeeStats:
                SeeStats();
                return true;
            case R.id.menuClearStats:
                fm.beginTransaction()
                        .add(new ClearAllDialogFragment(), "ClearAllDialogFragment")
                        .commit();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }



    private void Home() {
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, new HomeFragment())
                .commit();
    }

    private void NewQuiz(){
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, new CategoryFragment())
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void BtnNewQuizClicked() {
        NewQuiz();
    }

    @Override
    public void BtnSeeStatsClicked() {
        SeeStats();
    }

    private void SeeStats() {
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, new StatsFragment())
                .addToBackStack(null)
                .commit();
    }

    @Override
    public void CategorySelected(int cat, String categoryString) {
        category = cat;
        currentCategory = categoryString;
        Log.d("Category Selected", String.valueOf(category));
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, new DifficultyFragment())
                .addToBackStack(null)
                .commit();

    }

    @Override
    public void DifficultySelected(String difficulty) {
        url = "https://opentdb.com/api.php?amount=10&category=" + String.valueOf(category) +
                difficulty + "&encode=url3986";
        Log.d("URL:", url);
        Bundle bundle = new Bundle();
        bundle.putString("url", url);
        bundle.putString("category", currentCategory);
        quizFragment = new QuizFragment();
        quizFragment.setArguments(bundle);
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, quizFragment)
                .addToBackStack(null)
                .commit();
    }


    @Override
    public void QuizCompleted(int totalCorrect) {
        Bundle bundle = new Bundle();
        bundle.putInt("totalCorrect", totalCorrect);
        QuizEndedFragment qeFragment = new QuizEndedFragment();
        qeFragment.setArguments(bundle);
        fm.beginTransaction()
                .replace(R.id.fragmentContainer, qeFragment)
                .commit();

    }

    @Override
    public void QEButtonClickedSeeStats() {
        SeeStats();
    }

    @Override
    public void QEButtonClickedHome() {
        Home();
    }

    @Override
    public void ClearStats() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                AppDatabase.getInstance(getApplicationContext())
                        .categoryDAO()
                        .clearAllTables();
            }
        }).start();
    }
}