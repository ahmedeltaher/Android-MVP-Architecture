package com.task;

import android.content.Context;
import android.support.annotation.VisibleForTesting;
import android.support.multidex.MultiDexApplication;

import com.task.di.DaggerMainComponent;
import com.task.di.MainComponent;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class App extends MultiDexApplication {
    private MainComponent mainComponent;
    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        mainComponent = DaggerMainComponent.create();
        context = getApplicationContext();
    }

    public MainComponent getMainComponent() {
        return mainComponent;
    }

    public static Context getContext() {
        return context;
    }

    @VisibleForTesting
    public void setComponent(MainComponent mainComponent) {
        this.mainComponent = mainComponent;
    }
}
