package com.foodora.ui.component.splash;

import android.content.Intent;
import android.os.Handler;

import com.foodora.App;
import com.foodora.R;
import com.foodora.ui.base.BaseActivity;
import com.foodora.ui.component.Home.HomeActivity;

import javax.inject.Inject;

import static com.foodora.utils.Constants.SPLASH_DELAY;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class SplashActivity extends BaseActivity implements SplashView {

    @Inject
    SplashPresenter splashPresenter;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(SplashActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = splashPresenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.splash_layout;
    }

    @Override
    public void openSettingsView() {

    }

    @Override
    public void openHomeView() {

    }

    @Override
    public void NavigateToMainScreen() {
        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, HomeActivity.class);
            startActivity(intent);
            finish();
        }, SPLASH_DELAY);
    }
}
