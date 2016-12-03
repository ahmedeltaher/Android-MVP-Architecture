package com.task.ui.component.Home;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.ProgressBar;

import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;
import com.task.ui.component.ScooterLocation.ProductDetailsActivity;

import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.task.utils.Constants.IMAGE_URL_KEY;

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
    public void initializeScootersList(List<Scooter> scooters) {
        ScootersAdapter scootersAdapter = new ScootersAdapter(presenter.getRecyclerItemListener(), scooters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvProducts.setLayoutManager(layoutManager);
        rvProducts.setHasFixedSize(true);
        rvProducts.setAdapter(scootersAdapter);
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
