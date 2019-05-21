package com.example.garageapp.views;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.garageapp.R;
import com.example.garageapp.model.EntryTicket;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.RecieptTicket;
import com.example.garageapp.model.UserAccount;

public class RecieptTicketActivity extends AppCompatActivity {
    private Garage garage;
    private UserAccount currentUser;
    private RecieptTicket ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reciept_ticket);
        garage = (Garage) getIntent().getSerializableExtra("garage");
        currentUser = (UserAccount) getIntent().getSerializableExtra("currentUser");
        ticket = (RecieptTicket) getIntent().getSerializableExtra("ticket");
        setFields(ticket);
    }

    private void setFields(RecieptTicket ticket) {
        TextView dateView = (TextView)findViewById(R.id.textView27);
        dateView.setText("Date: " + ticket.getDate());
        TextView timeView = (TextView)findViewById(R.id.textView24);
        timeView.setText("Time Parked: " + ticket.getTimeEntered());
        TextView parkedByView = (TextView)findViewById(R.id.textView25);
        parkedByView.setText("Parked By: " + ticket.getNameOfAttendant());
        TextView spotNumberView = (TextView)findViewById(R.id.textView15);
        spotNumberView.setText("Spot Number: " + ticket.getSpotNumber());
        TextView spotTypeView = (TextView)findViewById(R.id.textView23);
        spotTypeView.setText("Spor Type: " + ticket.getCategoryOfSpot());
        TextView plateNumberView = (TextView)findViewById(R.id.textView18);
        plateNumberView.setText("Plate Number: " + ticket.getPlateNumber());
        TextView paymentView = (TextView)findViewById(R.id.textView28);
        paymentView.setText("Payment Scheme: $" + ticket.getPaymentScheme() + " per hour");
        TextView costView = (TextView)findViewById(R.id.textView22);
        costView.setText("Amount due: $" + ticket.getAmountCharged());
    }

    public void moveBackToManage(View view){
        Intent intent = new Intent(this, ManageGarageActivity.class);
        intent.putExtra("serialize_data", garage);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }
}
