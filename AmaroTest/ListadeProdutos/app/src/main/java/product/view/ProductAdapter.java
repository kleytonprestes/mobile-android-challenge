package product.view;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import base.Utils;
import listaprodutos.com.br.listadeprodutos.R;
import product.model.Product;

public class ProductAdapter extends RecyclerView.Adapter<ProductAdapter.MyViewHolder> {

    private ArrayList<Product> products;
    private Context context;
    private ProductClickInterface clickInterface;

    public ProductAdapter(Context context, ProductClickInterface clickInterface, ArrayList<Product> products) {

        this.context = context;
        this.products = products;
        this.clickInterface = clickInterface;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.custom_view_product, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        final Product product = products.get(position);
        holder.productTitle.setText(product.getName());
        holder.productPrice.setText(String.valueOf(product.getActualPrice()));

        Utils.loadImage(context, product.getImage(), holder.productImage);

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickInterface.onItemClickListener(product);
            }
        });

    }

    @Override
    public int getItemCount() {
        return products != null ? products.size() : 0;
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView productTitle;
        TextView productPrice;
        ImageView productImage;


        MyViewHolder(View itemView) {
            super(itemView);
            productTitle = itemView.findViewById(R.id.product_title);
            productPrice = itemView.findViewById(R.id.product_price);
            productImage = itemView.findViewById(R.id.image_product);

        }
    }
}
