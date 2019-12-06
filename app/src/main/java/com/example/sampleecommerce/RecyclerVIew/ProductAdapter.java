package com.example.sampleecommerce.RecyclerVIew;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sampleecommerce.DetailActivity;
import com.example.sampleecommerce.ProductModel;
import com.example.sampleecommerce.R;

import java.util.ArrayList;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.ProductHolder> {

    private Context context;
    private ArrayList<ProductModel> listProduct;

    private ArrayList<ProductModel> getListProduct() {
        return listProduct;
    }

    public void setListProduct(ArrayList<ProductModel> listProduct) {
        this.listProduct = listProduct;
    }

    public ProductAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public ProductHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.product_item, parent, false);
        return new ProductHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ProductHolder holder, int position) {
        Glide.with(context)
                .load(getListProduct().get(position).getImage())
                .apply(new RequestOptions()
//                        .override(249, 310)
                        .centerCrop())
                .into(holder.imageView);
        holder.tvName.setText(getListProduct().get(position).getName());
        holder.tvPrice.setText(String.valueOf(getListProduct().get(position).getPrice()));

        holder.itemView.setOnClickListener(new OnItemClickListener(position, new OnItemClickListener.OnClickCallback() {
            @Override
            public void onItemClicked(View view, int position) {
                Intent intent = new Intent(context, DetailActivity.class);
                ProductModel productModel = new ProductModel();

                productModel.setId(getListProduct().get(position).getId());
                productModel.setImage(getListProduct().get(position).getImage());
                productModel.setName(getListProduct().get(position).getName());
                productModel.setPrice(getListProduct().get(position).getPrice());

                intent.putExtra(DetailActivity.EXTRA_DATA, productModel);
                context.startActivity(intent);
            }
        }));
    }

    @Override
    public int getItemCount() {
        return getListProduct().size();
    }

    class ProductHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView tvName, tvPrice;

        ProductHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.imageView);
            tvName = itemView.findViewById(R.id.text_of_name);
            tvPrice = itemView.findViewById(R.id.text_of_price);
        }

    }
}
