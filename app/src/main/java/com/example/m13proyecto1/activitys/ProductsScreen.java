package com.example.m13proyecto1.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.dependencies.ProductAdapter;
import com.example.m13proyecto1.objects.Product;
import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProductsScreen extends AppCompatActivity {

    //String url = "http://192.168.1.136:8000/api/products/android";
    String url = "http://192.168.1.70:8000/api/products/android";
    private final ExecutorService executor = Executors.newSingleThreadExecutor();
    private final Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_products_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        ArrayList<Product> arrayProducts = new ArrayList<Product>();
        ImageButton purchase = (ImageButton)findViewById(R.id.purchaseButton);
        ListView productList = (ListView)findViewById(R.id.productsList);

        executor.execute(new Runnable() {
            @Override
            public void run() {
                String result = null;

                try {
                    URL direction = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) direction.openConnection();
                    connection.setRequestMethod("GET");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");

                    InputStream entry = connection.getInputStream();
                    BufferedReader lector = new BufferedReader(new InputStreamReader(entry));
                    StringBuilder respuesta = new StringBuilder();
                    String line;

                    while ((line = lector.readLine()) != null) {
                        respuesta.append(line);
                    }

                    entry.close();
                    connection.disconnect();

                    result = respuesta.toString();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                String jsonBBDD = result;
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        JSONObject doc = null;
                        try {
                            doc = new JSONObject(jsonBBDD);
                            JSONArray usuariosArray = doc.getJSONArray("data");

                            for (int i = 0; i < usuariosArray.length(); i++) {
                                JSONObject usuario = usuariosArray.getJSONObject(i);
                                String photo = usuario.getString("product_image");
                                String name = usuario.getString("product_name");
                                String descript = usuario.getString("description");
                                String price = usuario.getString("price");
                                arrayProducts.add(new Product(photo, name, descript, price));
                            }

                            ProductAdapter adapter = new ProductAdapter(ProductsScreen.this, arrayProducts);
                            productList.setAdapter(adapter);
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                });
                purchase.setOnClickListener(view -> {
                    goToPurchase(arrayProducts);
                });
            }
        });
    }

    private void goToPurchase(ArrayList<Product> arrayList){
        ArrayList<Product> purchasedProducts = new ArrayList<>();
        Intent intent = new Intent(ProductsScreen.this, PurchaseScreen.class);

        for (Product product: arrayList) {
            purchasedProducts.add(new Product(product.photo, product.name, product.price));
        }

        intent.putExtra("products_totals", purchasedProducts);
        startActivity(intent);
    }
}