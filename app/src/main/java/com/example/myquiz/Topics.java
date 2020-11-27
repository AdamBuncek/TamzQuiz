package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Topics extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView textViewScore;
    private TextView headingText;
    private int highScore;
    private Button history;
    private Button geography;
    private Button sport;
    private ImageView point;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        textViewScore = findViewById(R.id.score);
        headingText = findViewById(R.id.headingText);
        history = findViewById(R.id.historyButton);
        geography = findViewById(R.id.geoButton);
        sport = findViewById(R.id.sportButton);
        point = findViewById(R.id.point);

        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(headingText);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewScore);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(history);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(geography);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(sport);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(point);

        loadHighScore();

        history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("category","1");
                startActivity(intent);
                finish();
            }
        });

        geography.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("category","2");
                startActivity(intent);
                finish();
            }
        });

        sport.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("category","3");
                startActivity(intent);
                finish();
            }
        });

    }

    public void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
        textViewScore.setText("Score: " + highScore);
    }

}