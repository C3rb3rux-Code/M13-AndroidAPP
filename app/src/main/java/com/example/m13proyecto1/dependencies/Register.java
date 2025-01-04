package com.example.m13proyecto1.dependencies;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.m13proyecto1.activitys.ProductsScreen;

import org.json.JSONObject;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Register {
    Context context;
    public Register(Context context) {this.context = context;}

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void addUser(String userName, String userEmail, String userPassword, String userPhone, String url) {
        executor.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    URL direction = new URL(url);
                    HttpURLConnection connection = (HttpURLConnection) direction.openConnection();
                    connection.setRequestMethod("POST");
                    connection.setRequestProperty("Content-Type", "application/json");
                    connection.setRequestProperty("Accept", "application/json");
                    connection.setDoOutput(true);

                    JSONObject jsonObject = new JSONObject();
                    jsonObject.put("name", userName);
                    jsonObject.put("email", userEmail);
                    jsonObject.put("password", userPassword);
                    jsonObject.put("phone", userPhone);

                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = jsonObject.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    int responseCode = connection.getResponseCode();

                    System.out.println("Código de respuesta: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try {
                            Intent intent = new Intent(context, ProductsScreen.class);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
