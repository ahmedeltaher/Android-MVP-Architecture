package com.task.ui.component.Home;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.task.R;
import com.task.data.remote.dto.Product;
import com.task.ui.base.listeners.RecyclerItemListener;

import java.util.List;

/**
 * Created by AhmedEltaher on 25/11/16.
 */

public class ProductsAdapter extends RecyclerView.Adapter<ProductViewHolder> {
    private final List<Product> products;
    private RecyclerItemListener onItemClickListener;

    public ProductsAdapter(RecyclerItemListener onItemClickListener, List<Product> products) {
        this.onItemClickListener = onItemClickListener;
        this.products = products;
    }

    @Override
    public ProductViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductViewHolder(view, onItemClickListener);
    }

    @Override
    public void onBindViewHolder(ProductViewHolder holder, int position) {
        holder.bind(position, products.get(position), onItemClickListener);
    }

    @Override
    public int getItemCount() {
        return products.size();
    }


}

