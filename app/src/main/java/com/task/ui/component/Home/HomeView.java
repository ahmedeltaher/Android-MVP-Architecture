package com.task.ui.component.Home;

import com.task.data.remote.dto.Product;
import com.task.ui.base.Presenter;

import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public interface HomeView extends Presenter.View {
    void initializeProductsList(List<Product> Products);

    void setLoaderVisiblity(boolean isVisible);

    void navigateToProductDetails(String ImageURL);
}
