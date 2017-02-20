package com.netease.asepct.internal;

import java.util.concurrent.TimeUnit;

/**
 * Created by neo on 2017/2/9.
 * Copyright 2016 NetEase. All rights reserved.
 */

public class TimeWatcher {
    private static final String TAG = "TimeWatcher";
    private long startTime;
    private long endTime;
    private long totalTime;

    public TimeWatcher() {
    }

    public void reset() {
        startTime = 0;
        endTime = 0;
        totalTime = 0;
    }

    public void start() {
        reset();
        startTime = System.nanoTime();
    }

    public void stop() {
        if (startTime != 0) {
            endTime = System.nanoTime();
            totalTime = endTime - startTime;
        } else {
            reset();
        }
    }

    public long getTotalTime() {
        return (totalTime != 0) ? TimeUnit.NANOSECONDS.toMillis(endTime - startTime) : 0;
    }
}
