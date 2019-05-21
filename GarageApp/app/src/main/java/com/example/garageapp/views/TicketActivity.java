package com.example.garageapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.garageapp.R;
import com.example.garageapp.model.EntryTicket;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.UserAccount;


public class TicketActivity extends AppCompatActivity {
    private Garage garage;
    private UserAccount currentUser;
    private EntryTicket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket);
        garage = (Garage) getIntent().getSerializableExtra("garage");
        currentUser = (UserAccount) getIntent().getSerializableExtra("currentUser");
        ticket = (EntryTicket) getIntent().getSerializableExtra("ticket");
        setFields(ticket);
    }

    private void setFields(EntryTicket ticket) {
        TextView dateView = (TextView)findViewById(R.id.textView13);
        dateView.setText("Date: " + ticket.getDate());
        TextView timeView = (TextView)findViewById(R.id.textView17);
        timeView.setText("Time Parked: " + ticket.getTimeEntered());
        TextView parkedByView = (TextView)findViewById(R.id.textView14);
        parkedByView.setText("Parked By: " + ticket.getNameOfAttendant());
        TextView spotNumberView = (TextView)findViewById(R.id.textView21);
        spotNumberView.setText("Spot Number: " + ticket.getSpotNumber());
        TextView spotTypeView = (TextView)findViewById(R.id.textView19);
        spotTypeView.setText("Spor Type: " + ticket.getCategoryOfSpot());
        TextView plateNumberView = (TextView)findViewById(R.id.textView20);
        plateNumberView.setText("Plate Number: " + ticket.getPlateNumber());
        TextView paymentView = (TextView)findViewById(R.id.textView16);
        paymentView.setText("Payment Scheme: $" + ticket.getPaymentScheme() + " per hour");
    }

    public void moveBackToManage(View view){
        Intent intent = new Intent(this, ManageGarageActivity.class);
        intent.putExtra("serialize_data", garage);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }
}
