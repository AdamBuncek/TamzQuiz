package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewHighScore;
    private TextView scoreText;
    private TextView textHeading;
    private Button start;
    private ImageView point;
    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewHighScore = findViewById(R.id.scoreView);
        scoreText = findViewById(R.id.scoreText);
        textHeading = findViewById(R.id.textHeading);
        start = findViewById(R.id.startButton);
        point = findViewById(R.id.point);
        loadHighScore();

        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewHighScore);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(scoreText);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textHeading);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(start);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(point);

        start.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                startQuiz();
            }
        });

    }

    private void startQuiz(){
        Intent intent = new Intent(getApplicationContext(), Topics.class);
        startActivity(intent);
    }

    public void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewHighScore.setText(String.valueOf(highScore));
    }

}