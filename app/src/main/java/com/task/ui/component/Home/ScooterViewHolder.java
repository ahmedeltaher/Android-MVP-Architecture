package com.task.ui.component.Home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.App;
import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.task.utils.ResHelper;

import butterknife.Bind;
import butterknife.ButterKnife;

import static com.task.utils.Constants.FINE_ENERGY;
import static com.task.utils.Constants.WARING_ENERGY;
import static java.lang.String.valueOf;

/**
 * Created by AhmedEltaher on 25/11/16.
 */

public class ScooterViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemListener onItemClickListener;

    @Bind(R.id.tv_motorcycle_vin)
    TextView tvVIN;
    @Bind(R.id.tv_motorcycle_name)
    TextView tvName;
    @Bind(R.id.tv_motorcycle_license_plate)
    TextView tvLicensePlate;
    @Bind(R.id.tv_motorcycle_energy_level)
    TextView tvEnergyLevel;
    @Bind(R.id.rl_scooter_item)
    RelativeLayout scooterItemLayout;


    public ScooterViewHolder(View itemView, RecyclerItemListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, Scooter scooter, RecyclerItemListener recyclerItemListener) {
        tvVIN.setText(scooter.getVin());
        tvName.setText(scooter.getModel());
        tvLicensePlate.setText(scooter.getLicensePlate());
        tvEnergyLevel.setText(valueOf(scooter.getEnergyLevel()));
        if (scooter.getEnergyLevel() > FINE_ENERGY) {
            scooterItemLayout.setBackgroundColor(ResHelper.getColor(App.getContext(), R.color.green));
        } else if (scooter.getEnergyLevel() > WARING_ENERGY) {
            scooterItemLayout.setBackgroundColor(ResHelper.getColor(App.getContext(), R.color.yellow));
        } else {
            scooterItemLayout.setBackgroundResource(ResHelper.getColor(App.getContext(), R.color.red));
        }
        scooterItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

