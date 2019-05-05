package com.example.garageapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.garageapp.R;
import com.example.garageapp.model.Garage;

public class CreateGarageActivity extends AppCompatActivity {
    private Garage garage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_garage);
    }

    public Garage getGarage() {
        return garage;
    }

    public void moveToManageGarageActivity(View view){
        if(createGarage() == true) {
            Intent intent = new Intent(this, ManageGarageActivity.class);
            intent.putExtra("serialize_data", garage);
            startActivity(intent);
        } else {

        }
    }


    public boolean createGarage(){
        EditText inputMotorcycleSpaceCount = (EditText)findViewById(R.id.editText2);
        EditText inputCarSpaceCount = (EditText)findViewById(R.id.editText4);
        EditText inputTruckSpaceCount = (EditText)findViewById(R.id.editText5);
        EditText inputMotorcycleRate = (EditText)findViewById(R.id.editText6);
        EditText inputCarRate = (EditText)findViewById(R.id.editText7);
        EditText inputTruckRate = (EditText)findViewById(R.id.editText8);

        if(!inputMotorcycleSpaceCount.getText().toString().equals("") &&
                !inputCarSpaceCount.getText().toString().equals("") &&
                !inputTruckSpaceCount.getText().toString().equals("") &&
                !inputMotorcycleRate.getText().toString().equals("") &&
                !inputCarRate.getText().toString().equals("") &&
                !inputTruckRate.getText().toString().equals("")){
            int numMotorcycles = Integer.valueOf(inputMotorcycleSpaceCount.getText().toString());
            int numCars = Integer.valueOf(inputCarSpaceCount.getText().toString());
            int numTrucks = Integer.valueOf(inputTruckSpaceCount.getText().toString());
            double motorcycleRate = Double.valueOf(inputMotorcycleRate.getText().toString());
            double carRate = Double.valueOf(inputCarRate.getText().toString());
            double truckRate = Double.valueOf(inputTruckRate.getText().toString());
            garage = new Garage(numMotorcycles, numCars, numTrucks,
                    motorcycleRate, carRate, truckRate);
            return true;
        } else {
            System.out.println("here");

            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Please fill out input entirely.")
                    .setTitle("Complete Input")
                    .setCancelable(false)
                    .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog dialog = builder.create();
         dialog.show();
            return false;
        }
    }
}
