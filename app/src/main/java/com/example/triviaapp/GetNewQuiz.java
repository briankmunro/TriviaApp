package com.example.triviaapp;

import android.os.AsyncTask;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class GetNewQuiz extends AsyncTask<String, Integer, String> {

    private String rawJSON;
    private String urlString;
    private OnQuestionsImport listener;

    public interface OnQuestionsImport{
        void completedQuestions(TriviaQuestion[] questions);
    }

    public GetNewQuiz(String url){
        this.urlString = url;
    }

    public void setOnQuestionsImportListener(OnQuestionsImport listenerFromQuiz){
        listener = listenerFromQuiz;
    }

    @Override
    protected String doInBackground(String... strings) {
        try{
            URL url = new URL(urlString);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.connect();

            int status = connection.getResponseCode();
            switch (status){
                case 200:

                    BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                    rawJSON = bufferedReader.readLine();
                    Log.d("GetNewQuiz", "doInBackground" + rawJSON.toString());
                    break;
            }
        }catch(Exception e){
            Log.d("GetNewQuiz", "doInBackground" + e.toString());
        }
        return null;
    }

    @Override
    protected void onPostExecute(String s) {

        TriviaQuestion[] questions;
        try{
            questions = parseJSON();
            listener.completedQuestions(questions);
        }catch(Exception e){
            Log.d("GetNewQuiz","onPostExecute" + e.toString());
        }
        super.onPostExecute(s);
    }

    private TriviaQuestion[] parseJSON(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        Gson gson = gsonBuilder.create();

        TriviaQuestion[] questions = null;
        try{
            JsonObject jsonObject = new Gson().fromJson(rawJSON, JsonObject.class);
            questions = gson.fromJson(jsonObject.get("results"), TriviaQuestion[].class);

        }catch(Exception e){
            Log.d("GetNewQuiz","parseJSON" + e.toString());
        }
        return questions;
    }
}
