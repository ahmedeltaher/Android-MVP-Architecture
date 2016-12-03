package com.task.ui.component.Home;

import android.os.Bundle;

import com.task.data.remote.dto.Product;
import com.task.data.remote.dto.Products;
import com.task.ui.base.Presenter;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.usecase.FoodoraProductsUseCase;
import com.task.usecase.FoodoraProductsUseCase.Callback;

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
                getView().navigateToProductDetails(products.get(position).getImage());
        }
    };

    private final Callback callback = new Callback() {
        @Override
        public void onSuccess(Products allProducts) {
            products = allProducts.getProducts();
                getView().initializeProductsList(getActiveProducts());
                getView().setLoaderVisiblity(false);
        }

        @Override
        public void onFail() {

        }
    };
}
