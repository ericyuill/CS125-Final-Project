package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.content.Intent;
import android.widget.TextView;

public class GameOver extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_over);

        Intent intent = getIntent();
        int score = intent.getIntExtra(MainActivity.final_Score, 0);
        TextView finalScore = findViewById(R.id.finalScore);
        finalScore.setText("" + score);
    }
}
