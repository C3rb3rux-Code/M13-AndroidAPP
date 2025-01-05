package com.example.m13proyecto1.activitys;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.dependencies.Register;

public class RegisterScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setContentView(R.layout.activity_register_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        String url = "http://127.0.0.1:8000/api/user/add";
        EditText name = (EditText)findViewById(R.id.inputName);
        EditText email = (EditText)findViewById(R.id.inputEmail);
        EditText password = (EditText)findViewById(R.id.inputPassword);
        EditText confirmPassword = (EditText)findViewById(R.id.inputConfirmPassword);
        EditText phone = (EditText)findViewById(R.id.inputPhone);
        Button register = (Button)findViewById(R.id.buttonRegister);

        register.setOnClickListener(view -> {
            String nameS = name.getText().toString();
            String emailS = email.getText().toString();
            String passwordS = password.getText().toString();
            String cPaswordS = confirmPassword.getText().toString();
            String phoneS = phone.getText().toString();
            if (passwordS.equals(cPaswordS)) {
                Register newUser;
                newUser = new Register(RegisterScreen.this);
                newUser.addUser(nameS, emailS, passwordS, phoneS, url);
            } else {
                Toast.makeText(this, "Datos no validos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}