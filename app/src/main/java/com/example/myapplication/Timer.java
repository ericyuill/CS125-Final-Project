package com.example.myapplication;

public class Timer {
    static Thread thread = new Thread();
    public static void Counter(String args[]) throws InterruptedException {
        for (int i = 5; i >= 0; i--) {
            thread.sleep(1000);
            System.out.println(i);
        }
    }
}
