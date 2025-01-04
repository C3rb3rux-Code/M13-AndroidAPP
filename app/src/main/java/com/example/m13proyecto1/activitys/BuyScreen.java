package com.example.m13proyecto1.activitys;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.m13proyecto1.R;
import com.example.m13proyecto1.extras.Extra;

import java.time.LocalDate;

public class BuyScreen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_buy_screen);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        EditText numCard = (EditText)findViewById(R.id.creditCardText);
        EditText cardOwner = (EditText)findViewById(R.id.nameCreditCardText);
        EditText expiredDate = (EditText)findViewById(R.id.expiredDateText);
        EditText cvvCard = (EditText)findViewById(R.id.cvvText);
        Button submit = (Button)findViewById(R.id.submitButton);

        String card = numCard.getText().toString();
        String owner = cardOwner.getText().toString();
        String expired = expiredDate.getText().toString();
        String cvv = cvvCard.getText().toString();

        submit.setOnClickListener(view -> {
            if (Extra.validateCard(card) && Extra.validateCVV(cvv) && Extra.validateDate(expired) && owner.isEmpty()) {
                Intent intent = new Intent(this, thanksToBuy.class);
                startActivity(intent);
            } else {
                Toast.makeText(this, "Datos invalidos", Toast.LENGTH_SHORT).show();
            }

        });
    }
}