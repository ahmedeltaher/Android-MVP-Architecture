package com.test.ui.component.Home;

import android.os.Bundle;

import com.test.data.remote.dto.Product;
import com.test.data.remote.dto.Products;
import com.test.ui.base.Presenter;
import com.test.ui.base.listeners.RecyclerItemListener;
import com.test.usecase.FoodoraProductsUseCase;
import com.test.usecase.FoodoraProductsUseCase.Callback;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class HomePresenter extends Presenter<HomeView> {

    private final FoodoraProductsUseCase foodoraProductsUseCase;
    private List<Product> products;

    @Inject
    public HomePresenter(FoodoraProductsUseCase foodoraProductsUseCase) {
        this.foodoraProductsUseCase = foodoraProductsUseCase;
    }

    @Override
    public void initialize(Bundle extras) {
        super.initialize(extras);
        foodoraProductsUseCase.getProducts(callback);
    }

    public RecyclerItemListener getRecyclerItemListener() {
        return recyclerItemListener;
    }

    public List<Product> getActiveProducts() {
        List<Product> activeProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.isActive()) {
                activeProducts.add(product);
            }
        }
        return activeProducts;
    }

    private final RecyclerItemListener recyclerItemListener = new RecyclerItemListener() {
        @Override
        public void onItemSelected(int position) {
            //TODO goto ImageActivity
            if (isViewAlive.get()) {
                getView().navigateToProductDetails(products.get(position).getImage());
            }
        }
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(Products allProducts) {
            products = allProducts.getProducts();
            if (isViewAlive.get()) {
                getView().initializeProductsList(getActiveProducts());
                getView().setLoaderVisiblity(false);
            }
        }

        @Override
        public void onFail() {

        }
    };
}
