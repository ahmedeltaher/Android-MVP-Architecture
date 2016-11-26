package com.foodora.ui.component.productDetails;

import com.foodora.ui.base.Presenter;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ProductDetailsView extends Presenter.View {
    void initializeProductFullImage(String imageURL);
}
