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
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

public class Summary extends AppCompatActivity {

    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String KEY_HIGHSCORE = "keyHighScore";

    private TextView congratsText;
    private TextView scoreText;
    private Button playAgainButton;
    private ImageView smallPoint;
    private ImageView bigPoint;

    private int highScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_summary);

        scoreText = findViewById(R.id.score);
        congratsText = findViewById(R.id.congratsText);
        playAgainButton = findViewById(R.id.againButton);
        smallPoint = findViewById(R.id.smallPoint);
        bigPoint = findViewById(R.id.bigPoint);

        Intent intent = getIntent();
        int score = intent.getIntExtra(Quiz.SCORE, 0);
        updateMessage(score);
        loadHighScore();
        updateHighScore(score);

        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(scoreText);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(congratsText);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(playAgainButton);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(smallPoint);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(bigPoint);

        playAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                Intent intent = new Intent(getApplicationContext(), Topics.class);
                startActivity(intent);
            }
        });
    }

    private void updateMessage(int score){
        Log.d("score", String.valueOf(score));
        congratsText.setText("Congratulations!!! You've earnd " + score + " points");
    }

    public void loadHighScore(){
        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        highScore = prefs.getInt(KEY_HIGHSCORE, 0);
    }

    private void updateHighScore(int highScoreNew){
        highScore += highScoreNew;
        scoreText.setText("Score: " + highScore);

        SharedPreferences prefs = getSharedPreferences(SHARED_PREFS, MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt(KEY_HIGHSCORE, highScore);
        editor.apply();
    }

}