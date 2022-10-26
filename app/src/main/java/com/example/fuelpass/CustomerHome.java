package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Class CustomerHome implements the functionality of the menu screen that the Customer sees after
 * logging into the app.
 */
public class CustomerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_customer_home);

        //  ui element hooks
        Button btn1 = findViewById(R.id.customer_home_btn1);
        Button btn2 = findViewById(R.id.customer_home_btn2);
        Button btn3 = findViewById(R.id.customer_home_btn3);
        Button btn4 = findViewById(R.id.customer_home_btn4);

        // assign setOnClickListener to the buttons and navigate to respective activity screens
        btn1.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CustomerRefill.class)));
        btn2.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CustomerNewVehicle.class)));
        btn3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CustomerQuotaList.class)));
        btn4.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),CustomerViewFuelAvailability.class)));

    }
}