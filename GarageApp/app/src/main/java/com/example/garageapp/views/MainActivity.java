package com.example.garageapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.garageapp.views.CreateGarageActivity;
import com.example.garageapp.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToCreateGarage(View view){
        Intent intent = new Intent(this, CreateGarageActivity.class);
        startActivity(intent);
    }
}
