package com.example.m13proyecto1.dependencies;

import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import com.example.m13proyecto1.activitys.ProductsScreen;

import org.json.JSONObject;

public class ValidateUser {
    Context context;
    public ValidateUser(Context context) {this.context = context;}

    private final ExecutorService executor = Executors.newSingleThreadExecutor();

    public void ValidateUserTask(String user, String password, String url) {
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
                    jsonObject.put("email", user);
                    jsonObject.put("password", password);

                    try (OutputStream os = connection.getOutputStream()) {
                        byte[] input = jsonObject.toString().getBytes("utf-8");
                        os.write(input, 0, input.length);
                    }

                    int responseCode = connection.getResponseCode();

                    //System.out.println("Código de respuesta: " + responseCode);

                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        try {
                            Intent intent = new Intent(context, ProductsScreen.class);
                            context.startActivity(intent);
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    } else {
                        Toast.makeText(context, "Usuario o contraseña no valido", Toast.LENGTH_SHORT).show();
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
