package com.example.springbootdemo.time;

import java.util.Timer;
import java.util.TimerTask;

public class TimeDelay {
    public void delayRun() {
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                System.out.println("aa");
            }
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, 1000);
    }
}
