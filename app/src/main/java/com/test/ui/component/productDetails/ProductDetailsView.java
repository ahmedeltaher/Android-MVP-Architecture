package com.test.ui.component.productDetails;

import com.test.ui.base.Presenter;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ProductDetailsView extends Presenter.View {
    void initializeProductFullImage(String imageURL);
}
