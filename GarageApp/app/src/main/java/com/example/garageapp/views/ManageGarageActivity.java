package com.example.garageapp.views;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.garageapp.R;
import com.example.garageapp.model.Garage;

public class ManageGarageActivity extends AppCompatActivity {
    private Garage garage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
        setContentView(R.layout.activity_manage_garage);
    }

    public void displayGarage(View view){
        System.out.println(garage.toString());
    }
}
