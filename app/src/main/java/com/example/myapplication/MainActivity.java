package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;


public class MainActivity extends AppCompatActivity {

    private QuestionsLib myLib = new QuestionsLib();

    private TextView scorer;
    private TextView question;
    private Button trueButton;
    private Button falseButton;

    private String answer;
    private int keepScore = 0;
    private int questionNum = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button trueButton = findViewById(R.id.True);
        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onStart();
            }
        });

        scorer = findViewById(R.id.score);
        question = findViewById(R.id.question);

        trueButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateScore(keepScore);
            }
        });

        falseButton = findViewById(R.id.False);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameOver();
            }
        });
    }

    private void updateScore(int currentScore) {
        keepScore++;
        scorer.setText("" + keepScore);
    }
    private void gameOver() {
        Intent intent = new Intent(this, GameOver.class);
        startActivity(intent);
    }
}
