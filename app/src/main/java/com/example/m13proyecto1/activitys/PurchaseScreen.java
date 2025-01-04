package com.example.m13proyecto1.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.dependencies.PurchaseAdapter;
import com.example.m13proyecto1.objects.Product;

import java.util.ArrayList;

public class PurchaseScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_purchase_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Intent intent = getIntent();
        Button goBack = (Button)findViewById(R.id.backButton);
        ListView productList = (ListView)findViewById(R.id.purchaseProductsScreen);
        ImageButton goToPurchase = (ImageButton)findViewById(R.id.cariitoButton);
        TextView totalPrice = (TextView)findViewById(R.id.totalPrice);

        goBack.setOnClickListener(view -> {
            Intent goToProducts = new Intent(this, ProductsScreen.class);
            startActivity(goToProducts);
        });

        ArrayList<Product> arrayProducts = new ArrayList<Product>();
        if (intent != null) {
            String photo = intent.getStringExtra("productPhoto");
            String name = intent.getStringExtra("productName");
            String price = intent.getStringExtra("productPrice");
            arrayProducts.add(new Product(photo, name, price));
            totalPrice.setText(price);
        }

        goToPurchase.setOnClickListener(view -> {
            Intent intent1 = new Intent(this, BuyScreen.class);
            startActivity(intent1);
        });

        PurchaseAdapter adapter = new PurchaseAdapter(this, arrayProducts);
        productList.setAdapter(adapter);
    }
}