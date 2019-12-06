package com.example.sampleecommerce.RecyclerVIew;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.sampleecommerce.ProductModel;
import com.example.sampleecommerce.R;

import java.util.ArrayList;

public class CartAdapter extends RecyclerView.Adapter<CartAdapter.CartHolder> {
    private Context context;
    private final ArrayList<ProductModel> listCart = new ArrayList<>();


    public ArrayList<ProductModel> getListCart() {
        return listCart;
    }

    public void setListCart(ArrayList<ProductModel> listCart) {
        if (listCart.size() > 0){
            this.listCart.clear();
        }
        this.listCart.addAll(listCart);
        notifyDataSetChanged();
    }

    public CartAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public CartHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cart_item, parent, false);
        return new CartHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CartHolder holder, int position) {
        holder.tvName.setText(listCart.get(position).getName());
        holder.tvPrice.setText(listCart.get(position).getPrice());
        holder.tvPcs.setText("x"+listCart.get(position).getPcs());
        Glide.with(context)t
                .load(listCart.get(position).getImage())
                .apply(new RequestOptions().centerCrop())
                .into(holder.imageView);
    }

    @Override
    public int getItemCount() {
        return listCart.size();
    }
    class CartHolder extends RecyclerView.ViewHolder{
        TextView tvName, tvPrice, tvPcs;
        ImageView imageView;

        public CartHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.cart_img);
            tvName = itemView.findViewById(R.id.cart_name);
            tvPrice = itemView.findViewById(R.id.cart_price);
            tvPcs = itemView.findViewById(R.id.cart_pcs);

        }
    }


}
