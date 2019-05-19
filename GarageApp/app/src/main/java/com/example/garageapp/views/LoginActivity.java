package com.example.garageapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.garageapp.R;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.UserAccount;

public class LoginActivity extends AppCompatActivity {
    private Garage garage;
    private UserAccount currentUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
    }

    public void login(View view){
        EditText usernameText = (EditText)findViewById(R.id.editText9);
        EditText passwordText = (EditText)findViewById(R.id.editText10);
        String username = usernameText.getText().toString().trim();
        String password = passwordText.getText().toString().trim();

        if(garage.login(username, password) == true){
            currentUser = garage.getAccountBag().findAccount(username);
            Intent intent = new Intent(this, ManageGarageActivity.class);
            intent.putExtra("serialize_data", garage);
            intent.putExtra("currentUser", currentUser);
            startActivity(intent);
        } else {

        }
    }
}
