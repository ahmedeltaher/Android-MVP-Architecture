package com.task.di;


import android.os.Handler;
import android.os.Looper;

import com.task.data.local.LocalRepository;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.task.threadPool.DefaultExecutorSupplier;

import java.util.concurrent.ThreadPoolExecutor;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.task.threadPool.DefaultExecutorSupplier.getDefaultExecutorSupplier;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

@Module
public class MainModule {
    @Provides
    @Singleton
    public LocalRepository provideLocalRepository() {
        return new LocalRepository();
    }

    @Provides
    @Singleton
    public Gson provideGson() {
        Gson gson = new GsonBuilder().create();
        return gson;
    }

    @Provides
    @Singleton
    public Handler provideHandler() {
        return new Handler();
    }

    @Provides
    @Singleton
    public ThreadPoolExecutor provideThreadPoolExecutor() {
        return getDefaultExecutorSupplier().getThreadPool();
    }
}
