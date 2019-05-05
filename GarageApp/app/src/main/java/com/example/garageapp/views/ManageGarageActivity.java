package com.example.garageapp.views;

import android.content.DialogInterface;
import android.preference.MultiSelectListPreference;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.example.garageapp.R;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.Vehicle;
import com.example.garageapp.model.VehicleSize;

import java.util.ArrayList;

public class ManageGarageActivity extends AppCompatActivity {
    private Garage garage;
    private String incomingLicensePlateNumber;
    private String incomingVehicleSize;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_garage);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
//        displayGarageToScrollView();

    }

    public void displayGarage(View view){
        System.out.println(garage.toString());
    }

    public void displayGarageToScrollView(){

        ScrollView myRoot = findViewById(R.id.scrollRoot);
        LinearLayout rootLayout = findViewById(R.id.rootLayout);
        LinearLayout itemLayout = findViewById(R.id.itemRoot);
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);

//        for(int i = 0; i < garage.getTotalSpots(); i++) {
            TextView textView = new TextView(this);
            textView.setText(garage.getSpot(1).toString());
            layout.addView(textView);
//        }
        itemLayout.addView(layout);
        rootLayout.addView(itemLayout);
    }


    // -- Squeeze the popup dialogue code into smaller individual methods
    public void parkCar(View view){
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
        dialog.show();


        AlertDialog.Builder builder2 = new AlertDialog.Builder(this);
        // second pop up
       String[] vehicleTypes = {"Motorcycle", "Car", "Truck"};
       boolean[] checkedType = {false, false, false};
        String itemSelected = "";
        builder2.setTitle("Enter vehicle type:")
                .setSingleChoiceItems(vehicleTypes, 0, null)
                .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                        int selectedPosition = ((AlertDialog)dialog).getListView().getCheckedItemPosition();
                        //continue setting retrieved value from alertdialogue
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


//        Vehicle incomingVehicle = new Vehicle();
//        garage.parkVehicle();
        }
    }

