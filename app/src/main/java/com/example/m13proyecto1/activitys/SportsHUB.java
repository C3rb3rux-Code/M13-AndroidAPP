package com.example.m13proyecto1.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.dependencies.ValidateUser;

public class SportsHUB extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        Button login = (Button)findViewById(R.id.buttonLogin);
        EditText user = (EditText)findViewById(R.id.emailInput);
        EditText password = (EditText)findViewById(R.id.passwordInput);
        TextView register = (TextView)findViewById(R.id.register);

        login.setOnClickListener(view -> {
            String userS = user.getText().toString();
            String passwordS = password.getText().toString();
            //String url = "http://192.168.1.136:8000/api/verify-user";
            String url = "http://192.168.1.70:8000/api/verify-user";

            if (!userS.isEmpty() && !passwordS.isEmpty() && passwordS.length() >= 8) {
                ValidateUser validate;
                validate = new ValidateUser(SportsHUB.this);
                validate.ValidateUserTask(userS, passwordS, url);
            } else {
                Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
            }
        });

        register.setOnClickListener(view -> {
            Intent intent = new Intent(SportsHUB.this, RegisterScreen.class);
            startActivity(intent);
        });
    }
}