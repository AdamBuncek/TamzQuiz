package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private TextView question;
    private TextView textViewScore;
    private Button button1;
    private Button button2;
    private Button button3;
    private Button button4;
    private TextView countDown;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        Intent intent = getIntent();
        int cat = Integer.parseInt(intent.getStringExtra("category"));

        question = findViewById(R.id.question);
        textViewScore =  findViewById(R.id.score);
        button1 = findViewById(R.id.button);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        button4 = findViewById(R.id.button4);

        DBHelper dbHelper = new DBHelper(this);
        questionList = dbHelper.getAllQuestions();
        questionCountTotal = 3;

        showNextQuestion();
    }

    private void showNextQuestion(){

        Random rand = new Random();
        int index =  rand.nextInt(questionList.size());
       /* if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(index);
        }*/
    }
}