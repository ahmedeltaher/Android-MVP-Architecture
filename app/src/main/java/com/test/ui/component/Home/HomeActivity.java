package com.test.ui.component.Home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.test.App;
import com.test.R;
import com.test.data.remote.dto.Product;
import com.test.ui.base.BaseActivity;
import com.test.ui.component.productDetails.ProductDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.test.utils.Constants.IMAGE_URL_KEY;

/**
 * Created by AhmedEltaher on 25/11/2016
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;
    @Bind(R.id.rv_products_list)
    RecyclerView rvProducts;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;

    @Override
    protected void initializeDagger() {
        App app = (App) getApplicationContext();
        app.getMainComponent().inject(HomeActivity.this);
    }

    @Override
    protected void initializePresenter() {
        super.presenter = presenter;
        presenter.setView(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_activity;
    }

    @Override
    public void openSettingsView() {

    }

    @Override
    public void openHomeView() {

    }

    @Override
    public void initializeProductsList(List<Product> Products) {
        ProductsAdapter productsAdapter = new ProductsAdapter(presenter.getRecyclerItemListener(), presenter.getActiveProducts());
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(productsAdapter);
    }

    @Override
    public void setLoaderVisiblity(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void navigateToProductDetails(String imageURL) {
        Intent intent = new Intent(this, ProductDetailsActivity.class);
        intent.putExtra(IMAGE_URL_KEY, imageURL);
        startActivity(intent);
    }
}
