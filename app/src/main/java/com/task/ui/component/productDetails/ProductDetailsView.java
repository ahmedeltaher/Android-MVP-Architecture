package com.task.ui.component.productDetails;

import com.task.ui.base.Presenter;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface ProductDetailsView extends Presenter.View {
    void initializeProductFullImage(String imageURL);
}
