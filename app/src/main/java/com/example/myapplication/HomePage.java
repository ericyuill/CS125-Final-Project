package com.example.myapplication;

import android.content.SharedPreferences;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;
import android.widget.TextView;

import java.util.Locale;
import java.util.Random;


public class HomePage extends AppCompatActivity {

    //variables for the countdown timer
    private static final long START_TIME_IN_MILLIS = 5000;
    private TextView mTextViewCountDown;

    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;

    private long mTimeLeftInMIllis = START_TIME_IN_MILLIS;

    //Questions Library
    Random r;
    private String mAnswer;
    private QuestionsLib mQuestions = new QuestionsLib();
    private int mQuestionsLength = mQuestions.mQuestions.length;

    //variables for counting the keeping score
    public static final String final_Score = "com.example.myapplication.final_Score";
    private TextView scorer;
    private TextView question;
    private Button trueButton;
    private Button falseButton;
    private int keepScore = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        r = new Random();

        //If the player clicks the True button
        final Button trueButton = findViewById(R.id.True);
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
            public void onClick(View view) {
                if (trueButton.getText() == mAnswer) {
                    updateScore(keepScore);
                    resetTimer();
                    startTimer();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lastScore", keepScore);
                    editor.apply();
                    gameOver();
                }
            }
        });

        falseButton = findViewById(R.id.False);
        falseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (falseButton.getText() == mAnswer) {
                    updateScore(keepScore);
                    resetTimer();
                    startTimer();
                    updateQuestion(r.nextInt(mQuestionsLength));
                } else {
                    SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putInt("lastScore", keepScore);
                    editor.apply();
                    gameOver();
                }
            }
        });

        // set text for countdown
        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        updateCountDownText();
    }

    private void updateScore(int currentScore) {
        keepScore++;
        scorer.setText("" + keepScore);
    }
    private void gameOver() {
        int endScore = Integer.parseInt(scorer.getText().toString());
        Intent intent = new Intent(this, GameOver.class);
        intent.putExtra(final_Score, endScore);
        startActivity(intent);
    }
    private void resetTimer() {
        mTimeLeftInMIllis = START_TIME_IN_MILLIS;
        updateCountDownText();
    }
    private void updateCountDownText() {
        int minutes = (int) (mTimeLeftInMIllis / 1000) / 60;
        int seconds = (int) (mTimeLeftInMIllis / 1000) % 60;

        String timeLeftFormatted = String.format(Locale.getDefault(),"%02d:%02d", minutes, seconds);
        mTextViewCountDown.setText(timeLeftFormatted);
    }
    private void startTimer() {
        mCountDownTimer = new CountDownTimer(mTimeLeftInMIllis, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                mTimeLeftInMIllis = millisUntilFinished;
                updateCountDownText();
            }

            @Override
            public void onFinish() {
                mTimerRunning = false;
            }
        }.start();
        mTimerRunning = true;
    }
    private void updateQuestion(int num) {
        question.setText(mQuestions.getQuestion(num));
        trueButton.setText(mQuestions.getChoice1(num));
        falseButton.setText(mQuestions.getChoice2(num));

        mAnswer = mQuestions.getCorrectAnswer(num);
    }
}