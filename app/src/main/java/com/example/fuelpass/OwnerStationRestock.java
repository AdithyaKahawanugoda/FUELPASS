package com.example.fuelpass;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Class OwnerStationRestock implements the functionality of when the station
 * owner adds in a request for the restock of fuel in the station.
 */
public class OwnerStationRestock extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    private DatePickerDialog datePickerDialog;
    private Button dateBtn, timeBtn, updateBtn;
    private EditText editText;
    private int hour, minute;

    private ArrayList<ModelAvailableFuel> availableFuelObj;
    private ModelStation resStation;

    private String selectedFuelType, restockAmount;

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl("http://192.168.8.169:8082/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build();
    APIStationManager apiStationManager = retrofit.create(APIStationManager.class);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_owner_station_restock);

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

        Spinner fuelTypesSpinner = findViewById(R.id.owner_station_restock_input1);
        dateBtn = findViewById(R.id.owner_station_restock_btn1);
        timeBtn = findViewById(R.id.owner_station_restock_btn2);
        updateBtn = findViewById(R.id.owner_station_restock_btn3);
        editText = findViewById(R.id.owner_station_restock_input2);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
        R.array.fuel_types_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(R.layout.spinner_list);
        // Apply the adapter to the spinner
        fuelTypesSpinner.setAdapter(adapter);
        fuelTypesSpinner.setOnItemSelectedListener(this);

        initDatePicker();
        dateBtn.setText(getTodayDate());

        updateBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                selectedFuelType = fuelTypesSpinner.getSelectedItem().toString();
                restockAmount = editText.getText().toString();
                restockStation(selectedFuelType,restockAmount);
            }
        });
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

    // return current date
    private String getTodayDate() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        month = month + 1;
        int day = cal.get(Calendar.DAY_OF_MONTH);
        return makeDateString(day, month, year);
    }

    // concatenate day, month and year
    private String makeDateString(int day, int month, int year) {
        return getMonthFormat(month) + " " + day + " " + year;
    }

    // return month name in string format
    private String getMonthFormat(int month) {
        if(month == 1)
            return "JAN";
        if(month == 2)
            return "FEB";
        if(month == 3)
            return "MAR";
        if(month == 4)
            return "APR";
        if(month == 5)
            return "MAY";
        if(month == 6)
            return "JUN";
        if(month == 7)
            return "JUL";
        if(month == 8)
            return "AUG";
        if(month == 9)
            return "SEP";
        if(month == 10)
            return "OCT";
        if(month == 11)
            return "NOV";
        if(month == 12)
            return "DEC";

        //default should never happen
        return "JAN";
    }

    // calculate current date, month and year before opening date picker dialog
    private void initDatePicker() {
        DatePickerDialog.OnDateSetListener dateSetListener = (datePicker, year, month, day) -> {
            month = month + 1;
            String date = makeDateString(day, month, year);
            dateBtn.setText(date);
        };

        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        int style =  AlertDialog.THEME_HOLO_DARK;

        datePickerDialog = new DatePickerDialog(this, style, dateSetListener, year, month, day);
        //datePickerDialog.getDatePicker().setMaxDate(System.currentTimeMillis());
    }

    // open date picker dialog
    public void openDatePicker(View view)
    {
        datePickerDialog.show();
    }

    // open time picker dialog
    public void popTimePicker(View view)
    {
        TimePickerDialog.OnTimeSetListener onTimeSetListener = (timePicker, selectedHour, selectedMinute) -> {
            hour = selectedHour;
            minute = selectedMinute;
            timeBtn.setText(String.format(Locale.getDefault(), "%02d:%02d",hour, minute));
        };

        // int style = AlertDialog.THEME_HOLO_DARK;

        TimePickerDialog timePickerDialog = new TimePickerDialog(this, /*style,*/ onTimeSetListener, hour, minute, true);

        timePickerDialog.setTitle("Select Time");
        timePickerDialog.show();
    }

    public void restockStation(String selectedFuelType, String restockAmount) {
        availableFuelObj.forEach((fuel)->{
            if(fuel.getFuelType().equals(selectedFuelType)) {
                float availableFuelAmount = Float.parseFloat(fuel.getAvailable());
                fuel.setAvailable(Float.toString(availableFuelAmount+Float.parseFloat(restockAmount)));
            }
        });
        resStation.setAvailableFuel(availableFuelObj);

        ArrayList<ModelAvailableFuel> availableFuel = resStation.getAvailableFuel();
        availableFuel.forEach((n)->{
            Log.i("7526-CHK",n.getUnitPrice()+" "+n.getFuelType());});

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