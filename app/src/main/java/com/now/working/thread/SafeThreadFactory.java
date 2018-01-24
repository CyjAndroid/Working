package com.now.working.thread;

import java.util.concurrent.ThreadFactory;

/**
 * 对线程池中新创建的线程作保护
 */
public class SafeThreadFactory implements ThreadFactory {
    private String mThreadName;

    private Thread.UncaughtExceptionHandler mExceptionHandler;

    public SafeThreadFactory() {
    }

    public SafeThreadFactory(Thread.UncaughtExceptionHandler exceptionHandler) {
        mExceptionHandler = exceptionHandler;
    }

    @Override
    public Thread newThread(Runnable runnable) {
        Thread thread = new Thread(runnable);
        if(mExceptionHandler == null) {
            mExceptionHandler = new SafeExceptionHandler();
        }
        thread.setUncaughtExceptionHandler(mExceptionHandler);
        return thread;
    }
}
