package com.test.ui.component.Home;

import com.test.data.remote.dto.Product;
import com.test.ui.base.Presenter;

import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface HomeView extends Presenter.View {
    void initializeProductsList(List<Product> Products);

    void setLoaderVisiblity(boolean isVisible);

    void navigateToProductDetails(String ImageURL);
}
