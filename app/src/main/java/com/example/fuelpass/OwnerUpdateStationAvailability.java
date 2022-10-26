package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class OwnerUpdateStationAvailability extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_update_station_availability);

        Spinner fuelTypesSpinner = findViewById(R.id.owner_update_station_availability_input1);
        Spinner fuelAvailabilityStatusSpinner = findViewById(R.id.owner_update_station_availability_input2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter1 = ArrayAdapter.createFromResource(this,
                R.array.fuel_types_array, android.R.layout.simple_spinner_item);
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this,
                R.array.fuel_availability_status_array, android.R.layout.simple_spinner_item);

        // Specify the layout to use when the list of choices appears
        adapter1.setDropDownViewResource(R.layout.spinner_list);
        adapter2.setDropDownViewResource(R.layout.spinner_list);

        // Apply the adapter to the spinner
        fuelTypesSpinner.setAdapter(adapter1);
        fuelTypesSpinner.setOnItemSelectedListener(this);

        fuelAvailabilityStatusSpinner.setAdapter(adapter2);
        fuelAvailabilityStatusSpinner.setOnItemSelectedListener(this);
    }

    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedValue = adapterView.getItemAtPosition(i).toString();

        if(!selectedValue.equals("Select Vehicle Type") && !selectedValue.equals("Select Availability")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            Toast.makeText(adapterView.getContext(),selectedValue,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}