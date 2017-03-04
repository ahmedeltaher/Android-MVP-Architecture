package com.task.threadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import static android.os.Process.THREAD_PRIORITY_BACKGROUND;

/**
 * Created by ahmedeltaher on 3/4/17.
 */

public class DefaultExecutorSupplier {

    private static DefaultExecutorSupplier instance;
    // Sets the amount of time an idle thread will wait for a task before terminating
    private final int KEEP_ALIVE_TIME = 60;

    // Sets the Time Unit to seconds
    private final TimeUnit KEEP_ALIVE_TIME_UNIT = TimeUnit.SECONDS;

    // A queue of Runnables for the image download pool
    private final BlockingQueue<Runnable> threadWorkLoadQueue = new LinkedBlockingQueue<Runnable>();

    ThreadFactory backgroundPriorityThreadFactory;

    /**
     * NOTE: This is the number of total available cores. On current versions of
     * Android, with devices that use plug-and-play cores, this will return less
     * than the total number of cores. The total number of cores is not
     * available in current Android implementations.
     */
    private final int NUMBER_OF_CORES = Runtime.getRuntime().availableProcessors();

    // A managed pool of background threads
    private ThreadPoolExecutor threadPool;

    MainThreadExecutor mainThreadExecutor;
    // PriorityThreadPoolExecutor forLightWeightBackgroundTasks
    private DefaultExecutorSupplier() {
        // setting the thread factory
        backgroundPriorityThreadFactory = new PriorityThreadFactory(THREAD_PRIORITY_BACKGROUND);

        threadPool = new ThreadPoolExecutor(NUMBER_OF_CORES, NUMBER_OF_CORES,
                KEEP_ALIVE_TIME, KEEP_ALIVE_TIME_UNIT, threadWorkLoadQueue, backgroundPriorityThreadFactory);

        // setting the thread pool executor for mForLightWeightBackgroundTasks;
        // forLightWeightBackgroundTasks = new PriorityThreadPoolExecutor(NUMBER_OF_CORES,NUMBER_OF_CORES,KEEP_ALIVE_TIME,KEEP_ALIVE_TIME_UNIT,backgroundPriorityThreadFactory);

        // setting the thread pool executor for mMainThreadExecutor;
        // mainThreadExecutor = new MainThreadExecutor();
    }

    public static DefaultExecutorSupplier getDefaultExecutorSupplier() {

        if (instance == null) {
            synchronized (DefaultExecutorSupplier.class) {
                instance = new DefaultExecutorSupplier();
            }
        }
        return instance;
    }

    public BlockingQueue<Runnable> getThreadWorkLoadQueue() {
        return threadWorkLoadQueue;
    }

    public ThreadPoolExecutor getThreadPool() {
        return threadPool;
    }

    public boolean cancelTask(Runnable runnable) {
        boolean isRemoved = false;
        if (!threadWorkLoadQueue.isEmpty() && threadWorkLoadQueue.contains(runnable)) {
            threadWorkLoadQueue.remove(runnable);
            isRemoved = true;
        }

        return isRemoved;
    }
}
