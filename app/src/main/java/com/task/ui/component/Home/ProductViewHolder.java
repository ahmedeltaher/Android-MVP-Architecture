package com.task.ui.component.Home;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.task.R;
import com.task.data.remote.dto.Product;
import com.task.ui.base.listeners.RecyclerItemListener;
import com.squareup.picasso.Picasso;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.view.View.GONE;

/**
 * Created by AhmedEltaher on 25/11/16.
 */

public class ProductViewHolder extends RecyclerView.ViewHolder {

    private RecyclerItemListener onItemClickListener;

    @Bind(R.id.tv_product_name)
    TextView tvProductName;
    @Bind(R.id.tv_product_original_price)
    TextView tvOriginalPrice;
    @Bind(R.id.tv_product_current_price)
    TextView tvCurrentPrice;
    @Bind(R.id.tv_product_brand)
    TextView tvBrandName;
    @Bind(R.id.iv_product_image)
    ImageView ivProductImage;
    @Bind(R.id.rl_product_item)
    RelativeLayout productItemLayout;


    public ProductViewHolder(View itemView, RecyclerItemListener onItemClickListener) {
        super(itemView);
        this.onItemClickListener = onItemClickListener;
        ButterKnife.bind(this, itemView);
    }

    public void bind(int position, Product product, RecyclerItemListener recyclerItemListener) {
        tvProductName.setText(product.getName());
        double originalPrice = product.getPrice().getOriginal();
        double currentPrice = product.getPrice().getCurrent();
        tvOriginalPrice.setText(product.getPrice().getOriginal() + product.getPrice().getCurrency());
        if (originalPrice == currentPrice) {
            tvCurrentPrice.setVisibility(GONE);
        } else {
            tvCurrentPrice.setText(product.getPrice().getCurrent() + product.getPrice().getCurrency());
        }
        tvBrandName.setText(product.getBrand());
        Picasso.with(ivProductImage.getContext()).load(product.getImage()).placeholder(R.drawable
            .shirt_icon).fit().into(ivProductImage);
        productItemLayout.setOnClickListener(v -> recyclerItemListener.onItemSelected(position));
    }
}

