package com.task.threadPool;

import android.os.Process;

import java.util.concurrent.ThreadFactory;

/**
 * Created by ahmedeltaher on 3/4/17.
 */

public class PriorityThreadFactory  implements ThreadFactory {

    private final int threadPriority;

    public PriorityThreadFactory(int threadPriority) {
        this.threadPriority = threadPriority;
    }

    @Override
    public Thread newThread(final Runnable runnable) {
        Runnable wrapperRunnable = () -> {
            try {
                Process.setThreadPriority(threadPriority);
            } catch (Throwable t) {

            }
            runnable.run();
        };
        return new Thread(wrapperRunnable);
    }

}