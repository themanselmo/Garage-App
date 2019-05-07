package com.example.garageapp.model;

public class Demo {
    public static void main(String[]args){
//        Garage g1 = new Garage(3,4,2);
//        g1.DisplayParkingSpots();
//
//        Car c1 = new Car();
//        c1.setPlateNumber("UJB 2170");
//        Motorcycle m1 = new Motorcycle();
//        m1.setPlateNumber("RPK 1970");
//        Truck t1 = new Truck();
//        t1.setPlateNumber("KFT 4435");
//        Truck t2 = new Truck();
//        t2.setPlateNumber("KFB 4435");
//        Truck t3 = new Truck();
//        t3.setPlateNumber("KFA 4435");
//        g1.parkVehicle(c1);
//        g1.parkVehicle(m1);
//        g1.parkVehicle(t1);
//        g1.parkVehicle(t2);
//        g1.parkVehicle(t3);
//
////        g1.DisplayParkingSpots();
//
//
//        AccountBag accounts = new AccountBag();
//        accounts.insertManager("max","123");
//        accounts.insertAttendant("rich","123");
//        accounts.insertAttendant("vic","123");
//        accounts.insertAttendant("frank","123");
//        accounts.insertAttendant("max","123");
//        accounts.insertAttendant("lindsey","123");
//
//        System.out.println(accounts.displayAccounts());

  ////////////////////////////////////////////////////////////

    Garage garage = new Garage(3,3,3);
    Car car = new Car();
    car.setPlateNumber("123");
    car.setCostRate(1);

    garage.parkVehicle(car);
    garage.DisplayParkingSpots();

    System.out.println("-------------");

    garage.removeVehicleByPlateNumber("123");
    System.out.println("-------------");

    garage.DisplayParkingSpots();

    }
}
