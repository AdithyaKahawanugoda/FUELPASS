package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class OwnerViewStationAvailability implements the functionality of when the station
 * owner views the fuel availability.
 */
public class OwnerViewStationAvailability extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<ModelAvailableFuel> availableFuel;
    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_view_station_availability);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://192.168.8.169:8082/api/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        APIStationManager apiStationManager = retrofit.create(APIStationManager.class);
        ModelStation modelStation = new ModelStation();
        Call<ModelStation> call = apiStationManager.getStationData("635a27fcc8a3556fe1452979");
        call.enqueue(new Callback<ModelStation>() {
            @Override
            public void onResponse(Call<ModelStation> call, Response<ModelStation> response) {
                if(response.body() != null){
                    availableFuel = response.body().getAvailableFuel();
                    Toast.makeText(getBaseContext(),"Data Loaded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelStation> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });

        textView = findViewById(R.id.owner_view_station_availability_input2);
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
            displaySelectedFuelAvailability(selectedValue);
        }
    }

    @Override
    public void onNothingSelected(AdapterView<?> adapterView) {

    }

    public void displaySelectedFuelAvailability(String fuelType) {
        availableFuel.forEach((fuel)->{
            if(fuel.getFuelType().equals(fuelType)) {
                textView.setText(fuel.getAvailable());
            }
        });
    }
}