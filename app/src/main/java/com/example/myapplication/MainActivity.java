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


public class MainActivity extends AppCompatActivity {

    //variables for the countdown timer
    private static final long START_TIME_IN_MILLIS = 40000;
    private TextView mTextViewCountDown;
    private Button mButtonStartPause;
    private Button mButtonReset;
    private CountDownTimer mCountDownTimer;
    private boolean mTimerRunning;
    private long mTimeLeftInMIllis = START_TIME_IN_MILLIS;

    //variables for counting the keeping score
    public static final String final_Score = "com.example.myapplication.final_Score";
    private TextView scorer;
    private TextView question;
    private Button trueButton;
    private Button falseButton;
    private int keepScore = 0;

    //variables for High Score rankings
    //use scorer for tv_score
    //use trueButton for b_add; falseButton for b_end

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
                SharedPreferences preferences = getSharedPreferences("PREFS", 0);
                SharedPreferences.Editor editor = preferences.edit();
                editor.putInt("lastScore", keepScore);
                editor.apply();
                gameOver();
            }
        });

        mTextViewCountDown = findViewById(R.id.text_view_countdown);
        mButtonStartPause = findViewById(R.id.button_start_pause);
        mButtonReset = findViewById(R.id.button_reset);

        mButtonStartPause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mTimerRunning) {
                    pauseTimer();
                } else {
                    startTimer();
                }
            }
        });

        mButtonReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetTimer();
            }
        });

        updateCountDownText();

        mTimerRunning = true;
        mButtonStartPause.setText("pause");
        mButtonReset.setVisibility(View.INVISIBLE);
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

    private void pauseTimer() {
        mCountDownTimer.cancel();
        mTimerRunning = false;
        mButtonStartPause.setText("start");
        mButtonReset.setVisibility(View.VISIBLE);
    }
    private void resetTimer() {
        mTimeLeftInMIllis = START_TIME_IN_MILLIS;
        updateCountDownText();
        mButtonReset.setVisibility(View.INVISIBLE);
        mButtonStartPause.setVisibility(View.VISIBLE);

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
                mButtonStartPause.setText("Start");
                mButtonStartPause.setVisibility(View.INVISIBLE);
                mButtonReset.setVisibility(View.VISIBLE);

            }
        }.start();
    }
}
