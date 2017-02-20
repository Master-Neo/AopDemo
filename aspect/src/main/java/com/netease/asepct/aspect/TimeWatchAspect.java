package com.netease.asepct.aspect;

import android.util.Log;

import com.netease.asepct.internal.TimeWatcher;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;


/**
 * Created by neo on 2017/2/9.
 * Copyright 2016 NetEase. All rights reserved.
 */

@Aspect
public class TimeWatchAspect {
    private static final String POINTCUT_TIME_WATCH =
            "execution(* com.netease.aopdemo.MainActivity.onCreate(..))";

    @Pointcut(POINTCUT_TIME_WATCH)
    public void timeWatch() {}

    @Around("timeWatch()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature ms = (MethodSignature) joinPoint.getSignature();
        String methodName = ms.getName();
        String className = ms.getDeclaringType().getSimpleName();

        TimeWatcher watcher = new TimeWatcher();
        watcher.start();
        Object result = joinPoint.proceed();
        watcher.stop();

        Log.i(className, methodName + " execute with " + watcher.getTotalTime() + "ms");

        return result;
    }
}
