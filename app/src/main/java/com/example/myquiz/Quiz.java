package com.example.myquiz;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.SoundPool;
import android.os.Build;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.PersistableBundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.daimajia.androidanimations.library.Techniques;
import com.daimajia.androidanimations.library.YoYo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Locale;
import java.util.Random;

public class Quiz extends AppCompatActivity {

    private SoundPool soundPool;
    private int soundFail;
    private int soundSuccess;
    private int sound3;
    private int soundCounter;

    public static final String SCORE = "score";
    private static final long COUNTDOWN_IN_MILIS = 21000;

    private static final String KEY_SCORE = "keyScore";
    private static final String KEY_QUESTION_COUNT = "keyQuestionCount";
    private static final String KEY_MILIS_LEFT = "keyMilisLeft";
    private static final String KEY_ANSWERED = "keyAnswered";
    private static final String KEY_QUESTION_LIST = "keyQuestionList";

    private TextView textViewQuestion;
    private TextView textViewScore;
    private TextView correctAnswer;
    private RadioGroup rbGroup;
    private RadioButton button1;
    private RadioButton button2;
    private RadioButton button3;
    private RadioButton button4;
    private TextView textViewTimer;
    private TextView textViewQCount;
    private Button buttonConfirm;
    private ImageView point;

    private ColorStateList textColorDefaultRb;
    private ColorStateList textColorDefaultTimer;

    private CountDownTimer timer;
    private long timeLeft;

    private ArrayList<Question> questionList;
    private int questionCounter;
    private int questionCountTotal;
    private Question currentQuestion;

    private int score;
    private boolean answered;

    private long backPressedTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz);

        if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP){
            AudioAttributes audioAttributes = new AudioAttributes.Builder()
                    .setUsage(AudioAttributes.USAGE_ASSISTANCE_SONIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                    .build();

            soundPool = new SoundPool.Builder()
                    .setMaxStreams(2)
                    .setAudioAttributes(audioAttributes)
                    .build();
        }
        else {
            soundPool = new SoundPool(2, AudioManager.STREAM_MUSIC, 0);
        }

        soundFail = soundPool.load(this, R.raw.fail1,1);
        soundSuccess = soundPool.load(this, R.raw.success1,1);
        sound3 = soundPool.load(this, R.raw.begin,1);
        soundCounter = soundPool.load(this, R.raw.counter,1);

        Intent intent = getIntent();
        int cat = Integer.parseInt(intent.getStringExtra("category"));

        textViewQuestion = findViewById(R.id.question);
        textViewScore =  findViewById(R.id.score);
        textViewTimer = findViewById(R.id.timer);
        textViewQCount = findViewById(R.id.questionCount);
        rbGroup = findViewById(R.id.radioGroup);
        button1 = findViewById(R.id.radioButton1);
        button2 = findViewById(R.id.radioButton2);
        button3 = findViewById(R.id.radioButton3);
        button4 = findViewById(R.id.radioButton4);
        buttonConfirm = findViewById(R.id.confirmButton);
        correctAnswer = findViewById(R.id.correctAnswer);
        point = findViewById(R.id.point);

        ShowAnimation();

        textColorDefaultRb = button1.getTextColors();
        textColorDefaultTimer = textViewTimer.getTextColors();

      //  if(savedInstanceState == null){
            DBHelper dbHelper = new DBHelper(this);
            questionList = dbHelper.getQuestionsByCategory(cat);
            questionCountTotal = 3;
            Collections.shuffle(questionList);

            showNextQuestion();
      //  }
      /*  else{
            questionList = savedInstanceState.getParcelableArrayList(KEY_QUESTION_LIST);
            if(questionList == null){
                this.finish();
            }
            questionCountTotal = 3;
            questionCounter = savedInstanceState.getInt(KEY_QUESTION_COUNT);
            currentQuestion = questionList.get(questionCounter - 1);
            timeLeft = savedInstanceState.getLong(KEY_MILIS_LEFT);
            score = savedInstanceState.getInt(KEY_SCORE);
            answered = savedInstanceState.getBoolean(KEY_ANSWERED);

            if(!answered){
                startCountDown();
            }
            else{
                updateTimerTextView();
                showSolution();
            }
        }*/

        buttonConfirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!answered){
                    if(button1.isChecked() || button2.isChecked() || button3.isChecked() || button4.isChecked()){
                        checkAnswer();
                    }
                    else{
                        Toast.makeText(Quiz.this, "Choose option", Toast.LENGTH_SHORT).show();
                        soundPool.play(sound3,1,1,0,0,1);
                    }
                }
                else {
                    showNextQuestion();
                }
            }
        });
    }

    private void showNextQuestion(){
        button1.setTextColor(textColorDefaultRb);
        button2.setTextColor(textColorDefaultRb);
        button3.setTextColor(textColorDefaultRb);
        button4.setTextColor(textColorDefaultRb);
        rbGroup.clearCheck();
        correctAnswer.setText("");
        ShowAnimation();

       if(questionCounter < questionCountTotal){
            currentQuestion = questionList.get(questionCounter);

            textViewQuestion.setText(currentQuestion.getQuestion());
            button1.setText(currentQuestion.getOption1());
            button2.setText(currentQuestion.getOption2());
            button3.setText(currentQuestion.getOption3());
            button4.setText(currentQuestion.getOption4());

            questionCounter++;
            textViewQCount.setText("Question: " + questionCounter + "/" + questionCountTotal);
            answered = false;
            buttonConfirm.setText("Confirm");

            timeLeft = COUNTDOWN_IN_MILIS;
            startCountDown();
       }
       else {
           finishQuiz();
       }
    }

    private void startCountDown(){
        timer = new CountDownTimer(timeLeft, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished;
                updateTimerTextView();
            }

            @Override
            public void onFinish() {
                timeLeft = 0;
                updateTimerTextView();
                checkAnswer();
            }
        }.start();
    }

    private void updateTimerTextView(){
        int minutes = (int) (timeLeft / 1000) / 60;
        int seconds = (int) (timeLeft / 1000) % 60;

        String timeFormated = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);

        textViewTimer.setText(timeFormated);

        if(timeLeft < 10000){
            textViewTimer.setTextColor(Color.RED);
            YoYo.with(Techniques.Pulse).duration(500).repeat(0).playOn(textViewTimer);
            //soundPool.play(soundCounter,1,1,0,0,1);
        }
        else {
            textViewTimer.setTextColor(textColorDefaultTimer);
        }
    }

    private void checkAnswer(){
        answered = true;

        timer.cancel();

        RadioButton rbSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerNr = rbGroup.indexOfChild(rbSelected) + 1;

        if(answerNr == currentQuestion.getAnswerNr()){
            score += 5;
            textViewScore.setText("Score: " + score);
            soundPool.play(soundSuccess,1,1,0,0,1);
        }
        else {
            soundPool.play(soundFail,1,1,0,0,1);
        }

        showSolution();
    }

    private void showSolution(){
        button1.setTextColor(Color.RED);
        button2.setTextColor(Color.RED);
        button3.setTextColor(Color.RED);
        button4.setTextColor(Color.RED);

        switch (currentQuestion.getAnswerNr()){
            case 1:
                button1.setTextColor(Color.GREEN);
                correctAnswer.setText(currentQuestion.getOption1() + " is correct");
                break;

            case 2:
                button2.setTextColor(Color.GREEN);
                correctAnswer.setText(currentQuestion.getOption2() + " is correct");
                break;

            case 3:
                button3.setTextColor(Color.GREEN);
                correctAnswer.setText(currentQuestion.getOption3() + " is correct");
                break;

            case 4:
                button4.setTextColor(Color.GREEN);
                correctAnswer.setText(currentQuestion.getOption4() + " is correct");
                break;
        }

        if(questionCounter < questionCountTotal){
            buttonConfirm.setText("Next Question");
        }
        else{
            buttonConfirm.setText("Finish");
        }
    }

    private void finishQuiz(){
        Intent intent = new Intent(getApplicationContext(), Summary.class);
        intent.putExtra(SCORE,score);
        startActivity(intent);

        finish();
        soundPool.play(sound3,1,1,0,0,1);
    }

    private void ShowAnimation(){
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewQuestion);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewScore);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewTimer);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(textViewQCount);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(rbGroup);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(buttonConfirm);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(correctAnswer);
        YoYo.with(Techniques.FadeIn).duration(1000).repeat(0).playOn(point);
    }

    @Override
    public void onBackPressed() {
        if(backPressedTime + 2000 > System.currentTimeMillis()){
            Intent intent = new Intent(getApplicationContext(), Topics.class);
            startActivity(intent);
            finish();
        }
        else{
            Toast.makeText(this, "One more click to close.", Toast.LENGTH_SHORT).show();
        }

        backPressedTime = System.currentTimeMillis();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(timer != null){
            timer.cancel();
        }
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState, @NonNull PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        outState.putInt(KEY_SCORE,score);
        outState.putInt(KEY_QUESTION_COUNT,questionCounter);
        outState.putLong(KEY_MILIS_LEFT,timeLeft);
        outState.putBoolean(KEY_ANSWERED,answered);
        outState.putParcelableArrayList(KEY_QUESTION_LIST, questionList);
    }
}