package com.task.ui.component.ScooterLocation;

import android.os.Bundle;

import com.task.ui.base.Presenter;

import javax.inject.Inject;

import static com.task.utils.Constants.IMAGE_URL_KEY;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class ProductDetailsPresenter extends Presenter<ProductDetailsView> {

    private String imageURL;

    @Inject
    public ProductDetailsPresenter() {
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        imageURL = (String) extras.get(IMAGE_URL_KEY);
        if (isViewAlive.get()) {
            getView().initializeProductFullImage(imageURL);
        }
    }
}
