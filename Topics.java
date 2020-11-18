package com.example.myquiz;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Topics extends AppCompatActivity {

    Button history;
    Button geography;
    Button it;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_topics);

        history = findViewById(R.id.historyButton);
        geography = findViewById(R.id.geoButton);
        //it = findViewById(R.id.itButton);

        history.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("category","1");
                startActivity(intent);
            }
        });

        geography.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), Quiz.class);
                intent.putExtra("category","2");
                startActivity(intent);
            }
        });

    }
}