package com.example.garageapp.views;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.garageapp.R;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.UserAccount;
import com.example.garageapp.model.UserAttendant;
import com.example.garageapp.model.UserManager;

public class CreateManagerActivity extends AppCompatActivity {
    private Garage garage;
    private UserAccount currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_manager);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
    }

    public void moveToManageGarageAcivity(View view){
        if(insertManager() == true) {
            Intent intent = new Intent(this, ManageGarageActivity.class);
            intent.putExtra("serialize_data", garage);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
        } else {

        }
    }

    public boolean insertManager(){
        EditText usernameText = (EditText)findViewById(R.id.editText);
        EditText passwordText = (EditText)findViewById(R.id.editText3);

        if(!usernameText.getText().toString().equals("") &&
                !passwordText.getText().toString().equals("")){
            String username = usernameText.getText().toString().trim();
            String password = passwordText.getText().toString().trim();
            if(garage.getAccountBag().containsUsername(username) == false){
                UserManager newManager = new UserManager(username, password);
                currentUser = newManager;
                garage.insertManager(newManager);
                return true;
            }
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Username already exists.")
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
        } else {
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
