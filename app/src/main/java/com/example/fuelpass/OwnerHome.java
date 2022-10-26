package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

/**
 * Class OwnerHome implements the functionality of the menu screen that the Station Owner sees after
 * logging into the app.
 */
public class OwnerHome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_home);

        //  ui element hooks
        Button btn1 = findViewById(R.id.owner_home_btn1);
        Button btn2 = findViewById(R.id.owner_home_btn2);
        Button btn3 = findViewById(R.id.owner_home_btn3);
        Button btn4 = findViewById(R.id.owner_home_btn4);
        Button btn5 = findViewById(R.id.owner_home_btn5);

        // assign setOnClickListener to the buttons and navigate to respective activity screens
        btn1.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),OwnerViewStationAvailability.class)));
        btn2.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),OwnerStationRestock.class)));
        btn3.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),OwnerPriceUpdate.class)));
        btn4.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),OwnerQuotaUpdate.class)));
        btn5.setOnClickListener(v -> startActivity(new Intent(getApplicationContext(),OwnerUpdateStationAvailability.class)));
    }
}