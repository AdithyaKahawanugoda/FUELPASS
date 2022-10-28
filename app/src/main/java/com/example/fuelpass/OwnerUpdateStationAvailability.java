package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
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
 * Class OwnerUpdateStationAvailability implements the functionality of when the station
 * owner updates the fuel availability status.
 */
public class OwnerUpdateStationAvailability extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private ArrayList<ModelAvailableFuel> availableFuelObj;
    private ModelStation resStation;
    private Button updateBtn;

    private String selectedFuelType, selectedStatus;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.169:8082/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIStationManager apiStationManager = retrofit.create(APIStationManager.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_update_station_availability);
        resStation = new ModelStation();
        Call<ModelStation> call = apiStationManager.getStationData("635a27fcc8a3556fe1452979");
        call.enqueue(new Callback<ModelStation>() {
            @Override
            public void onResponse(Call<ModelStation> call, Response<ModelStation> response) {
                if(response.body() != null){
                    resStation.setStationName(response.body().getStationName());
                    resStation.setId(response.body().getId());
                    resStation.setStationAddress(response.body().getStationAddress());
                    availableFuelObj = response.body().getAvailableFuel();
                    Toast.makeText(getBaseContext(),"Data Loaded",Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<ModelStation> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });

        updateBtn = findViewById(R.id.owner_update_station_availability_btn);
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

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFuelType = fuelTypesSpinner.getSelectedItem().toString();
                selectedStatus = fuelAvailabilityStatusSpinner.getSelectedItem().toString();
                updateAvailability(selectedFuelType,selectedStatus);
            }
        });
    }
    // triggers when spinner item selected by the user
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

    public void updateAvailability(String selectedFuelType, String selectedStatus) {
        availableFuelObj.forEach((fuel)->{
            if(fuel.getFuelType().equals(selectedFuelType)) {
                fuel.setStatus(selectedStatus);
            }
        });
        resStation.setAvailableFuel(availableFuelObj);

        ArrayList<ModelAvailableFuel> availableFuel = resStation.getAvailableFuel();
        availableFuel.forEach((n)->{Log.i("7526-CHK",n.getUnitPrice()+" "+n.getFuelType());});

        Call<ModelStation> call = apiStationManager.updateStation("635a27fcc8a3556fe1452979",resStation);
        call.enqueue(new Callback<ModelStation>() {
            @Override
            public void onResponse(Call<ModelStation> call, Response<ModelStation> response) {
                Toast.makeText(getBaseContext(),"SUCCESSFUL",Toast.LENGTH_SHORT).show();
                Log.d("7526-RES OK",response+"");
                startActivity(new Intent(getApplicationContext(),OwnerHome.class));
            }

            @Override
            public void onFailure(Call<ModelStation> call, Throwable t) {
                Toast.makeText(getBaseContext(),"FAILED",Toast.LENGTH_SHORT).show();
            }
        });

    }
}