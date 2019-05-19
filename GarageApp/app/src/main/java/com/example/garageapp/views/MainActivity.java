package com.example.garageapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.garageapp.model.Garage;
import com.example.garageapp.views.CreateGarageActivity;
import com.example.garageapp.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void moveToManageGarage(View view){
        try {
            FileInputStream fis = openFileInput("garage.dat");
            ObjectInputStream ois = new ObjectInputStream(fis);
            Garage garage = (Garage) ois.readObject();
            Intent intent = new Intent(this, LoginActivity.class);
            intent.putExtra("serialize_data", garage);
            startActivity(intent);
        } catch (IOException e) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);

            builder.setMessage("Garage save file not found.")
                    .setTitle("File not found")
                    .setCancelable(false)
                    .setNegativeButton("Close", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    })
                    .setIcon(android.R.drawable.ic_dialog_alert);

            AlertDialog dialog = builder.create();
            dialog.show();
        } catch (ClassNotFoundException e) {


        }
    }

    public void moveToCreateGarage(View view){
        Intent intent = new Intent(this, CreateGarageActivity.class);
        startActivity(intent);
    }
}
