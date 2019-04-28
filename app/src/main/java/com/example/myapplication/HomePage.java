package com.example.myapplication;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class HomePage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        //Start Game button
        Button startGame = findViewById(R.id.startGame);
        startGame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startGame();
            }
        });

        //Leader Board button
        Button leaderBoard = findViewById(R.id.leaderboard);
        leaderBoard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openLeaderBoard();
            }
        });

        //How to play button
        Button howToPlay = findViewById(R.id.howToPlay);
        howToPlay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openHowToPlay();
            }
        });

        //About page button
        Button about = findViewById(R.id.aboutPage);
        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openAboutPage();
            }
        });
    }
    public void startGame() {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
    public void openLeaderBoard() {
        Intent intent = new Intent(this, LeaderBoard.class);
        startActivity(intent);
    }
    public void openHowToPlay() {
        Intent intent = new Intent(this, HowToPlay.class);
        startActivity(intent);
    }
    public void openAboutPage() {
        Intent intent = new Intent(this, AboutPage.class);
        startActivity(intent);
    }
}
