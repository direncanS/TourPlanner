package com.example.tourapp;

import com.example.tourapp.Model.SingletonSystem;
import com.example.tourapp.Model.Tour;
import org.junit.jupiter.api.Test;


import java.time.LocalDate;
import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class TourAppTest {

    private SingletonSystem handler = SingletonSystem.getInstance();

    @Test
    public void testGetInstance() {
        SingletonSystem instance1 = SingletonSystem.getInstance();
        SingletonSystem instance2 = SingletonSystem.getInstance();
        assertSame(instance1, instance2);
    }

    @Test
    public void testAddTour() {
        String response = handler.addTour("Tour 3", "Description", "Berlin", "Amsterdam", "Bus", "450", "6 Hours");
        assertEquals("Success", response, "Expected Success response");
    }

    @Test
    public void testAddTourWithEmptyFields() {
        String response = handler.addTour("", "", "", "", "", "", "");
        assertEquals("Fill All Fields", response, "Expected error message for empty fields");
    }

    @Test
    public void testAddTourWithSQLInjection() {
        // Test adding a tour with missing fields
        String response = handler.addTour("' or 1=1 --", " ", " ", " ", " ", "10", " ");
        assertEquals("The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters", response, "Expected error message for sql Injected code fields");
    }

    @Test
    public void testAddTourWithCharacterizedRourDIstance() {
        String response = handler.addTour("Tour 3", "Description", "Berlin", "Amsterdam", "Train", "450 Meters", "6 Hours");
        assertEquals("Tour Distance must be a Number", response, "Expected Success response");
    }

    @Test
    public void testUpdateTour() {
        int id = handler.getAllTours().get(0).getId();
        assertEquals("Success", handler.updateTour(id, "Tour 2", "New Description", "Islamabad", "Mumbai", "Bus", "200", "4 Hours"));
    }

    @Test
    public void testUpdateTourWithSQLInjection() {
        assertEquals("The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters", handler.updateTour(40, "' or 1=1 --", "New Description", "Islamabad", "Mumbai", "Bus", "200", "4 Hours"));
    }

    @Test
    public void testAddTourLog() {
        assertEquals("Success", handler.addTourLog("Tour 1", "Easy", "2 Hours", "100", LocalDate.now(), "4.5", "Good"));
    }

    @Test
    public void testAddTourLogWithEmptyFields() {
        assertEquals("Fill All Fields", handler.addTourLog("Tour 1", "", "", "", LocalDate.now(), "4.5", "Good"));
    }

    @Test
    public void testAddTourLogWithSQLInjection() {
        assertEquals("The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters", handler.addTourLog("' or 1=1 --", "Easy", "2 Hours", "100", LocalDate.now(), "4.5", "Good"));
    }

    @Test
    public void testGetTourNames() {
        ArrayList<String> tourNames = handler.getTourNames();
        String name = handler.getAllTours().get(0).getName();
        assertNotNull(tourNames);
        assertTrue(tourNames.contains(name));
    }

    @Test
    public void deleteTourWithInvalidID() {
       assertEquals("Invalid ID", handler.deleteTour(-1), "Expected Invalid ID");
    }

    @Test
    public void deleteTourWithUnexistedID() {
        assertEquals("Tour Not Found", handler.deleteTour(1000), "Expected Tour Not Found");
    }

    @Test
    public void SQLStringSecureCheck() {
        assertEquals(true, handler.isStringInSecure("' or 1=1 --"), "Expected SQL Injection Error Found");
        assertEquals(false, handler.isStringInSecure("SELECT * FROM USERS"), "Expected SQL Injection No Error Found");
        assertEquals(true, handler.isStringInSecure("' or true"), "Expected SQL Injection Error Found");
        assertEquals(false, handler.isStringInSecure("Ahmed"), "Expected SQL Injection Error Not Found");
    }

    @Test
    public void getATourWithSpecificID() {
        Tour t = handler.getAllTours().get(0);
        assertEquals(t, handler.getTour(t.getId()), "Expected Tour Found");
    }

    @Test
    public void updateTourHavingUnexistedID() {
        assertEquals("ID not found", handler.updateTour(1000, "Tour 2", "New Description", "Islamabad", "Mumbai", "Bus", "200", "4 Hours"));
    }

    @Test
    public void updateTourLogHavingUnexistedID() {
        assertEquals("ID not Found", handler.updateTourLog(1000, "1", "Tour 2", "New Description", "10", LocalDate.now(), "Bus", "200"));
    }

    @Test
    public void deleteTourLogWithInvalidID() {
        assertEquals("Invalid ID", handler.deleteTourLog(-1), "Expected Invalid ID");
    }

    @Test
    public void deleteTourLogWithUnexistedID() {
        assertEquals("Tour Log Not Found", handler.deleteTourLog(1000), "Expected Tour Log Not Found");
    }

    @Test
    public void getTourLogWithInvalidID(){
        assertEquals(null, handler.getTourLog(-1), "Expected Null");
    }

}