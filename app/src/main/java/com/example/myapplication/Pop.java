package com.example.myapplication;
import android.app.Application;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Window;
import android.view.WindowManager;

public class Pop extends Application {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate();
        setContentView(R.layout.falsepopup);

        DisplayMetrics dm = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(dm);

        int width = dm.widthPixels;
        int height = dm.heightPixels;

        getWindow().setLayout(width, height);
    }

    private WindowManager getWindowManager() {
        return null;
    }

    private Window getWindow() {
        return null;
    }

    private void setContentView(int falsepopup) { }
}
