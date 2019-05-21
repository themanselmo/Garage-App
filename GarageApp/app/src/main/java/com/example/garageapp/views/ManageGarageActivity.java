package com.example.garageapp.views;

import android.accounts.Account;
import android.content.Context;
import android.content.DialogInterface;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.garageapp.R;
import com.example.garageapp.model.AccountType;
import com.example.garageapp.model.Car;
import com.example.garageapp.model.EntryTicket;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.Motorcycle;
import com.example.garageapp.model.RecieptTicket;
import com.example.garageapp.model.Ticket;
import com.example.garageapp.model.Truck;
import com.example.garageapp.model.UserAccount;
import com.example.garageapp.model.UserAttendant;
import com.example.garageapp.model.UserManager;
import com.example.garageapp.model.Vehicle;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Date;

public class ManageGarageActivity extends AppCompatActivity {
    private Garage garage;
    private UserAccount currentUser;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter myAdapter;
    private RecyclerView.LayoutManager layoutManager;
    private String incomingLicensePlateNumber;
    private Vehicle incomingVehicle;
    private String incomingAccountUsername;
    private String incomingAccountPassoword;
    private UserAccount incomingAccount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage_garage);
        garage = (Garage) getIntent().getSerializableExtra("serialize_data");
        currentUser = (UserAccount) getIntent().getSerializableExtra("currentUser");
        recyclerView = findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        displayCurrentUserToTextView();
        displayGarageToScrollView();

    }

    // log out current user, save garage and move to login window
    public void logout(View view){
    saveGarage(view);
    Intent intent = new Intent(this, LoginActivity.class);
    intent.putExtra("serialize_data", garage);
    startActivity(intent);
    }

    // saves garage to binary file
    public void saveGarage(View view){
        try {
//            File file = new File("garage.dat");
//            file.createNewFile();
            FileOutputStream fos = openFileOutput("garage.dat", Context.MODE_PRIVATE);
            ObjectOutputStream oos = new ObjectOutputStream(fos);
            oos.writeObject(garage);
            oos.close();
            System.out.println("File saved!");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void displayGarage(View view) {
        System.out.println(garage.toString());
    }

    public void displayCurrentUserToTextView(){
        TextView userDisplay = (TextView)findViewById(R.id.textView10);
        String text = "Current User: " + currentUser.getUsername() +
                "\n" + "Account Type: " + currentUser.getAccountType();
        userDisplay.setText(text);
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
                        incomingLicensePlateNumber = text.getText().toString().trim();
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
                            incomingVehicle.setCostRate(garage.getMotorcycleRate());
                            parkVehicle(incomingLicensePlateNumber, incomingVehicle);
                        } else if (selectedPosition == 1) {
                            incomingVehicle = new Car();
                            incomingVehicle.setCostRate(garage.getCarRate());
                            parkVehicle(incomingLicensePlateNumber, incomingVehicle);
                        } else if (selectedPosition == 2) {
                            incomingVehicle = new Truck();
                            incomingVehicle.setCostRate(garage.getTruckRate());
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
        EntryTicket ticket = new EntryTicket();
        Date start = new Date();
        long startTime = start.getTime();
        vehicle.setPlateNumber(plateNumber);
        garage.parkVehicle(vehicle, currentUser.getUsername());
        vehicle.setTimeParked(startTime);
        ticket.setNameOfAttendant(currentUser.getUsername());
        ticket.setPlateNumber(vehicle.getPlateNumber());
        ticket.setCategoryOfSpot(garage.getSpotLastParked().getSpotSize());
        ticket.setSpotNumber(garage.getSpotLastParked().getSpotNumber());
        ticket.setTimeEntered(startTime);
        ticket.setPaymentScheme(vehicle.getCostRate());
        ticket.setDate(start);

        Intent intent = new Intent(this, TicketActivity.class);
        intent.putExtra("ticket", ticket);
        intent.putExtra("garage", garage);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }

    public void unParkCar(View view){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        final EditText text = new EditText(this);
        builder.setTitle("Remove Car")
                .setMessage("Please input the license plate of the car to be removed.")
                .setView(text)
                .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
//                        incomingLicensePlateNumber = text.getText().toString();
                        System.out.println(text.getText().toString());
                        removeVehicle(text.getText().toString().trim());
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

    }

    public void removeVehicle(String plateNumber){
        Date date = new Date();
        Vehicle vehicle = garage.findByPlateNumber(plateNumber).getCurrentV();
        RecieptTicket ticket = new RecieptTicket();
        ticket.setTimeExit(System.currentTimeMillis());
        ticket.setCategoryOfSpot(garage.findByPlateNumber(plateNumber).getSpotSize());
        ticket.setDate(date);
        ticket.setPaymentScheme(garage.findByPlateNumber(plateNumber).getCurrentV().getCostRate());
        ticket.setNameOfAttendant(currentUser.getUsername());
        ticket.setSpotNumber(garage.findByPlateNumber(plateNumber).getSpotNumber());
        ticket.setTimeEntered(garage.findByPlateNumber(plateNumber).getCurrentV().getTimeParked());
        ticket.setPlateNumber(vehicle.getPlateNumber());
        ticket.calculateAmountDue(ticket.getTimeEntered(),ticket.getTimeExit(),ticket.getPaymentScheme());
        garage.removeVehicleByPlateNumber(plateNumber);

        Intent intent = new Intent(this, RecieptTicketActivity.class);
        intent.putExtra("ticket", ticket);
        intent.putExtra("garage", garage);
        intent.putExtra("currentUser", currentUser);
        startActivity(intent);
    }

    public void addUser(View view){
        if(currentUser.getAccountType() == AccountType.Manager) {


            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText text = new EditText(this);
            builder.setTitle("Add User")
                    .setMessage("Please input the username for the account, and select the account type.")
                    .setView(text)
                    .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            incomingAccountUsername = text.getText().toString().trim();
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
            final EditText text2 = new EditText(this);
            builder2.setTitle("Add User")
                    .setMessage("Please input the password for the account, then select account type.")
                    .setView(text2)
                    .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            incomingAccountPassoword = text2.getText().toString().trim();
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
            AlertDialog dialog2 = builder2.create();

            AlertDialog.Builder builder3 = new AlertDialog.Builder(this);
            // second pop up
            String[] vehicleTypes = {"Manager", "Attendant"};
            builder3.setTitle("Enter account type:")
                    .setSingleChoiceItems(vehicleTypes, 0, null)
                    .setPositiveButton("Okay", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                            int selectedPosition = ((AlertDialog) dialog).getListView().getCheckedItemPosition();
                            //continue setting retrieved value from alertdialogue
                            if (selectedPosition == 0) {
                                garage.insertManager(new UserManager(incomingAccountUsername, incomingAccountPassoword));
                            } else if (selectedPosition == 1) {
                                garage.insertAttendant(new UserAttendant(incomingAccountUsername, incomingAccountPassoword));

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

            AlertDialog dialog3 = builder3.create();
            dialog3.show();
            dialog2.show();
            dialog.show();
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Manager access required.")
                    .setTitle("Access Denied")
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
        }

    }

    public void removeUser(View view){
        if(currentUser.getAccountType() == AccountType.Manager) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            final EditText text = new EditText(this);
            builder.setTitle("Remove User")
                    .setMessage("Please input the username of the account to be removed.")
                    .setView(text)
                    .setPositiveButton("Enter", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            incomingAccountUsername = text.getText().toString().trim();
                            garage.removeUserAccount(incomingAccountUsername);
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
        } else {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setMessage("Manager access required.")
                    .setTitle("Access Denied")
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
        }
    }
}

