package com.task.ui.component.Home;

import android.content.Intent;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.BaseActivity;
import com.task.ui.component.ScooterLocation.ScooterLocatorActivity;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.Bind;
import butterknife.OnClick;

import static android.support.design.widget.Snackbar.LENGTH_SHORT;
import static android.view.View.GONE;
import static android.view.View.VISIBLE;
import static com.task.utils.Constants.SCOOTERS_KEY;

/**
 * Created by AhmedEltaher on 5/12/2016
 */

public class HomeActivity extends BaseActivity implements HomeView {
    @Inject
    HomePresenter presenter;
    @Bind(R.id.rv_scooters_list)
    RecyclerView rvScooters;
    @Bind(R.id.pb_loading)
    ProgressBar pbLoading;
    @Bind(R.id.tv_no_data)
    TextView tvNoData;
    @Bind(R.id.rl_scooter_list)
    RelativeLayout rlScooterList;
    @Bind(R.id.btn_search)
    ImageButton btnSearch;
    @Bind(R.id.et_search)
    EditText editTextSearch;

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
    public void initializeScootersList(List<Scooter> scooters) {
        ScootersAdapter scootersAdapter = new ScootersAdapter(presenter.getRecyclerItemListener(), scooters);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        rvScooters.setLayoutManager(layoutManager);
        rvScooters.setHasFixedSize(true);
        rvScooters.setAdapter(scootersAdapter);
    }

    @Override
    public void setLoaderVisibility(boolean isVisible) {
        pbLoading.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void navigateToScooterLocator(ArrayList<Scooter> scooters) {
        Intent intent = new Intent(this, ScooterLocatorActivity.class);
        intent.putParcelableArrayListExtra(SCOOTERS_KEY, scooters);
        startActivity(intent);
    }

    @Override
    public void setNoDataVisibility(boolean isVisible) {
        tvNoData.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void setListVisibility(boolean isVisible) {
        rlScooterList.setVisibility(isVisible ? VISIBLE : GONE);
    }

    @Override
    public void showSearchError() {
        Snackbar.make(rlScooterList, getString(R.string.search_error), LENGTH_SHORT).show();
    }

    @Override
    public void showMenuMapError() {
        Snackbar.make(rlScooterList, getString(R.string.map_error), LENGTH_SHORT).show();
    }

    @OnClick({R.id.ic_toolbar_map, R.id.ic_toolbar_refresh, R.id.btn_search})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.ic_toolbar_map:
                presenter.onMapClick();
                break;
            case R.id.ic_toolbar_refresh:
                presenter.getScooters();
                break;
            case R.id.btn_search:
                presenter.onSearchClick(editTextSearch.getText().toString());
        }
    }
}
