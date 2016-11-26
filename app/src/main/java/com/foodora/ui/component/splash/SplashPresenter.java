package com.foodora.ui.component.splash;

import android.os.Bundle;

import com.foodora.ui.base.Presenter;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class SplashPresenter extends Presenter<SplashView> {

    @Inject
    public SplashPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        getView().NavigateToMainScreen();
    }
}
