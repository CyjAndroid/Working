package com.now.working.thread;


/**
 * 捕捉未知异常
 */
public class SafeExceptionHandler implements Thread.UncaughtExceptionHandler {
    private static final String TAG = SafeExceptionHandler.class.getSimpleName();

    @Override
    public void uncaughtException(Thread t, Throwable e) {
        e.printStackTrace();

        ErrorCollector.collectErrorInfo(e);
    }
}
