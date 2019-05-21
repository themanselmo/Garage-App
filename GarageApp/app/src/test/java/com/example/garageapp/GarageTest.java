package com.example.garageapp;

import com.example.garageapp.model.Car;
import com.example.garageapp.model.Garage;
import com.example.garageapp.model.UserAttendant;

import org.junit.Test;

import static org.junit.Assert.*;

public class GarageTest {

    @Test
    public void parkVehicleTest(){
        Garage garage = new Garage(3,3,3);
        assertEquals(true,garage.parkVehicle(new Car(), "Max"));
    }

    @Test
    public void removeVehicleTest(){
        Garage garage = new Garage(3,3,3);
        Car car = new Car();
        car.setPlateNumber("123");
        garage.parkVehicle(car, "Max");

        assertEquals(true, garage.removeVehicleByPlateNumber("123"));
    }

    @Test
    public void insertAttentantTest(){
        Garage garage = new Garage(3,3,3);
        UserAttendant me = new UserAttendant("Max", "123");
        assertEquals(true, garage.insertAttendant(me));

    }

    @Test
    public void insertAttendant2Test(){
        Garage garage = new Garage(3,3,3);
        UserAttendant me = new UserAttendant("Max", "123");
        garage.insertAttendant(me);
        UserAttendant me2 = new UserAttendant("Max", "123");
        assertEquals(false, garage.insertAttendant(me2));
    }

    @Test
    public void loginTest(){
        Garage garage = new Garage(3,3,3);
        UserAttendant me = new UserAttendant("Max","123");
        garage.insertAttendant(me);
        assertEquals(true, garage.login("Max","123"));
    }
}
