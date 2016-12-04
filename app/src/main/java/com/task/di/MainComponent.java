package com.task.di;


import com.task.ui.component.Home.HomeActivity;
import com.task.ui.component.ScooterLocation.ScooterLocatorActivity;
import com.task.ui.component.splash.SplashActivity;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by AhmedEltaher on 25/11/2016
 */
@Singleton
@Component(modules = MainModule.class)
public interface MainComponent {
    void inject(SplashActivity activity);

    void inject(HomeActivity activity);

    void inject(ScooterLocatorActivity activity);
}
