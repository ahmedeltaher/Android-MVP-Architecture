package com.task.ui.component.Home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.R;
import com.task.data.remote.dto.Scooter;
import com.task.ui.base.listeners.RecyclerItemListener;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.graphics.Color.GREEN;
import static android.graphics.Color.RED;
import static android.graphics.Color.YELLOW;
import static java.lang.String.valueOf;

/**
 * Created by AhmedEltaher on 25/11/16.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

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


    public ProductViewHolder(View itemView, RecyclerItemListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, Scooter scooter, RecyclerItemListener recyclerItemListener) {
        tvVIN.setText(scooter.getVin());
        tvName.setText(scooter.getModel());
        tvLicensePlate.setText(scooter.getLicensePlate());
        tvEnergyLevel.setText(valueOf(scooter.getEnergyLevel()));
        if (scooter.getEnergyLevel() > 50) {
            scooterItemLayout.setBackgroundColor(GREEN);
        } else if (scooter.getEnergyLevel() > 30) {
            scooterItemLayout.setBackgroundColor(YELLOW);
        } else {
            scooterItemLayout.setBackgroundColor(RED);
        }
        scooterItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

