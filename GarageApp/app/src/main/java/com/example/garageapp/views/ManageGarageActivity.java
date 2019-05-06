package com.example.garageapp.views;

import android.content.DialogInterface;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;

import com.example.garageapp.R;
import com.example.garageapp.model.Car;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.Motorcycle;
import com.example.garageapp.model.Truck;
import com.example.garageapp.model.Vehicle;

public class ManageGarageActivity extends AppCompatActivity {
    private Garage garage;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String incomingLicensePlateNumber;
    private Vehicle incomingVehicle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_garage);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        displayGarageToScrollView();

    }

    public void displayGarage(View view) {
        System.out.println(garage.toString());
    }

    // implement so it only shows filled parking spots
    public void displayGarageToScrollView() {
        String[] myDataSet = new String[garage.getTotalSpots()];
        for (int i = 0; i < garage.getTotalSpots(); i++) {
            myDataSet[i] = garage.getSpot(i).toString() + "\n" +
                    "-----------------------";
        }

        myAdapter = new MyAdapter(myDataSet);
        recyclerView.setAdapter(myAdapter);
    }


    // -- Squeeze the popup dialogue code into smaller individual methods
    public void parkCar(View view) {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText text = new EditText(this);
        builder.setTitle("Park Car")
                .setMessage("Please input the license plate of the car, and select the vehicle type.")
                .setView(text)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        incomingLicensePlateNumber = text.getText().toString();
                        dialog.dismiss();
                    }
                })
                .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog = builder.create();
        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        // second pop up
        String[] vehicleTypes = {"Motorcycle", "Car", "Truck"};
        builder2.setTitle("Enter vehicle type:")
                .setSingleChoiceItems(vehicleTypes, 0, null)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                        //continue setting retrieved value from alertdialogue
                        if (selectedPosition == 0) {
                            incomingVehicle = new Motorcycle();
                            parkVehicle(incomingLicensePlateNumber, incomingVehicle);
                        } else if (selectedPosition == 1) {
                            incomingVehicle = new Car();
                            parkVehicle(incomingLicensePlateNumber, incomingVehicle);
                        } else if (selectedPosition == 2) {
                            incomingVehicle = new Truck();
                            parkVehicle(incomingLicensePlateNumber, incomingVehicle);
                        }
                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                })
                .setIcon(android.R.drawable.ic_dialog_alert);

        AlertDialog dialog2 = builder2.create();
        dialog2.show();
        dialog.show();
    }

    public void parkVehicle(String plateNumber, Vehicle vehicle) {
        vehicle.setPlateNumber(plateNumber);
        garage.parkVehicle(vehicle);
        displayGarageToScrollView();
    }
}

