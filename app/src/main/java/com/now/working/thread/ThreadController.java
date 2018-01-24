package com.now.working.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Cyj on 18/1/24.
 */

public class ThreadController {
    private static final String DEFAULT_THREAD_NAME = "default_thread_name";
    private static ThreadPoolExecutor poolExecutor;

    {
        poolExecutor = new ThreadPoolExecutor(6, 200, 0L, TimeUnit.MINUTES,
                new LinkedBlockingDeque<Runnable>(1024), new SafeThreadFactory(), new ThreadPoolExecutor.AbortPolicy());
    }

    public static void asyncExacutor(Runnable runnable){
        poolExecutor.execute(runnable);
    }

    public <Result> Result syncExacutor(final Callable<Result> callable, long timeout) {
        Future<Result> future = poolExecutor.submit(callable);
        Result result = null;

        try {
            result = future.get(timeout, TimeUnit.MILLISECONDS);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            e.printStackTrace();
            ErrorCollector.collectErrorInfo(e);
        }

        return result;
    }


}
