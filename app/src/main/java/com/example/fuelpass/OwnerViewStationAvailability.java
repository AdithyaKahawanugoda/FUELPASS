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

/**
 * Class OwnerViewStationAvailability implements the functionality of when the station
 * owner views the fuel availability.
 */
public class OwnerViewStationAvailability extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view_station_availability);

        Spinner fuelTypeSpinner = findViewById(R.id.owner_view_station_availability_input1);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.fuel_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter to the spinner
        fuelTypeSpinner.setAdapter(adapter);
        fuelTypeSpinner.setOnItemSelectedListener(this);
    }
    // triggers when spinner item selected by the user
    @Override
    public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
        String selectedValue = adapterView.getItemAtPosition(i).toString();

        if(!selectedValue.equals("Select Fuel Type")) {
            ((TextView) adapterView.getChildAt(0)).setTextColor(Color.WHITE);
            Toast.makeText(adapterView.getContext(),selectedValue,Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }
}