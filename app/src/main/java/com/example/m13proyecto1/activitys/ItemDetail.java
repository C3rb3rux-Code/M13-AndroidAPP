package com.example.m13proyecto1.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.objects.Product;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class ItemDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_item_detail);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();

        Button goBack = (Button)findViewById(R.id.exitButton);
        ImageButton purchaseButton = (ImageButton)findViewById(R.id.purchase);

        goBack.setOnClickListener(view -> {
            Intent goToProducts = new Intent(this, ProductsScreen.class);
            startActivity(goToProducts);
        });

        purchaseButton.setOnClickListener(view -> {
            Intent goToProducts = new Intent(this, PurchaseScreen.class);
            startActivity(goToProducts);
        });

        TextView productName = (TextView)findViewById(R.id.productText);
        TextView productDescript = (TextView)findViewById(R.id.productDescript);
        ImageView productImg = (ImageView)findViewById(R.id.productImg);
        Button productPrice = (Button)findViewById(R.id.priceButton);

        String photo = intent.getStringExtra("itemDetailPhoto");
        String name = intent.getStringExtra("itemDetailName");
        String description = intent.getStringExtra("itemDetailDescription");
        String price = intent.getStringExtra("itemDetailPrice");

        Picasso.get()
                .load(photo)
                .placeholder(R.drawable.logo_transparent)
                .error(R.drawable.ic_launcher_background)
                .into(productImg);
        productName.setText(name);
        productDescript.setText(description);
        productPrice.setText(price);

        productPrice.setOnClickListener(view -> {
            if (photo != null && name != null && price != null) {
                Intent goPurchase = new Intent(this, PurchaseScreen.class);
                intent.putExtra("productPhoto", photo);
                intent.putExtra("productName", name);
                intent.putExtra("productPrice", price);
                startActivity(goPurchase);
            }
        });
    }
}