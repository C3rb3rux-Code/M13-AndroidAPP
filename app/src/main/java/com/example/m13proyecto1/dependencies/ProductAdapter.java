package com.example.m13proyecto1.dependencies;

import static androidx.core.content.ContextCompat.startActivity;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.activitys.ItemDetail;
import com.example.m13proyecto1.activitys.ProductsScreen;
import com.example.m13proyecto1.objects.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ProductAdapter extends ArrayAdapter<Product> {
    public ProductAdapter(@NonNull Context context, ArrayList<Product> users) {
        super(context, 0, users);
    }

    private static class ViewHolder {
        ImageView productImage;
        TextView productName;
        TextView productDescript;
        Button productBuy;
    }

    @NonNull
    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.product_design, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.productImage = (ImageView)convertView.findViewById(R.id.productImage);
            viewHolder.productName = (TextView)convertView.findViewById(R.id.productName);
            viewHolder.productDescript = (TextView)convertView.findViewById(R.id.productDescription);
            viewHolder.productBuy = (Button)convertView.findViewById(R.id.buyButton);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }

        if (product != null) {
            Picasso.get()
                    .load(product.getImageUrl())
                    .placeholder(R.drawable.logo_transparent)
                    .error(R.drawable.ic_launcher_background)
                    .into(viewHolder.productImage);
            viewHolder.productName.setText(product.name);
            viewHolder.productDescript.setText(product.descript);
            viewHolder.productBuy.setText(product.price + " €");
        }

        viewHolder.productBuy.setOnClickListener(view -> {
            Intent intent = new Intent(ProductAdapter.this.getContext(), ItemDetail.class);
            if (product != null) {
                intent.putExtra("itemDetailPhoto", product.getImageUrl());
                intent.putExtra("itemDetailName", product.name);
                intent.putExtra("itemDetailDescription", product.descript);
                intent.putExtra("itemDetailPrice", product.price);
            }
            getContext().startActivity(intent);
        });

        return convertView;
    }
}
