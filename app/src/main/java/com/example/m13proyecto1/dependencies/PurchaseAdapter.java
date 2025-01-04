package com.example.m13proyecto1.dependencies;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.objects.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class PurchaseAdapter extends ArrayAdapter<Product> {
    public PurchaseAdapter(@NonNull Context context, ArrayList<Product> users) {
        super(context, 0, users);
    }

    private static class ViewHolder {
        ConstraintLayout layout;
        ImageView productImage;
        TextView productName;
        TextView productPrice;
        Button productErase;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        Product product = getItem(position);
        ViewHolder viewHolder;

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.purchase_design, parent, false);
            viewHolder = new ViewHolder();
            viewHolder.layout = (ConstraintLayout)convertView.findViewById(R.id.layout1);
            viewHolder.productImage = (ImageView)convertView.findViewById(R.id.imgProduct);
            viewHolder.productName = (TextView)convertView.findViewById(R.id.nameProduct);
            viewHolder.productPrice = (TextView)convertView.findViewById(R.id.number);
            viewHolder.productErase = (Button)convertView.findViewById(R.id.removeItem);
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
            viewHolder.productPrice.setText(product.price + "€");
        }

        viewHolder.productErase.setOnClickListener(view -> {
            viewHolder.layout.setVisibility(View.INVISIBLE);
        });

        return convertView;
    }

}

