package com.example.tourapp.Model;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.apache.logging.log4j.LogManager;

import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
public class SingletonSystem {
    private static SingletonSystem instance;

    ArrayList<Tour> toursList;
    ArrayList <TourLog> tourLogsList;

    private static final Logger logger = LogManager.getLogger("tours");

    private SingletonSystem() {
        instance = null;
        toursList = new ArrayList<>();
        tourLogsList = new ArrayList<>();
    }

    public static SingletonSystem getInstance() {
        if (instance == null) {
            synchronized (SingletonSystem.class) {
                if (instance == null) {
                    instance = new SingletonSystem();
                    instance.loadData();
                }
            }
        }
        return instance;
    }

    public boolean isStringInSecure(String inputString){
        String[] sqlInjectionStrings = {"'", "\"", ";", "--", "/*", "*/", "xp_", "exec ", "union ", "drop ", "truncate ", "insert ", "update ", "delete "};
        for (String sqlInjectionString : sqlInjectionStrings) {
            if (inputString.contains(sqlInjectionString)) {
                return true;
            }
        }
        return false;
    }

    private void loadData(){
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Retrieve tour logs from the database
        List<Object[]> rows = session.createNativeQuery(
                        "SELECT tour_log_id, tour_id, difficulty, total_time, total_distance, datetime, rating, comment FROM tour_logs")
                .getResultList();
        // Process retrieved data and add them to the tourLogsList
        for (Object[] row : rows) {
            int tourLogId = (int) row[0];
            int tourId = (int) row[1];
            String difficulty = (String) row[2];
            String totalTime = (String) row[3];
            int totalDistance = (int) row[4];
            Date datetime = (Date) row[5];
            double rating = (double) row[6];
            String comment = (String) row[7];
            TourLog tourLog = new TourLog(tourId, difficulty, totalTime, totalDistance, datetime, (float) rating, comment);
            tourLog.setTourLogId(tourLogId);
            tourLogsList.add(tourLog);
        }

        session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        // Retrieve tour logs from the database
        List<Object[]> rowsTour = session.createNativeQuery(
                        "SELECT id, name, description, \"from\", \"to\", transport_type, tour_distance, estimated_time FROM tours")
                .getResultList();
        // Process retrieved data and add them to the tourLogsList
        for (Object[] row : rowsTour) {
            int tourId = (int) row[0];
            String name = (String) row[1];
            String description = (String) row[2];
            String from = (String) row[3];
            String to = (String) row[4];
            String transportType = (String) row[5];
            int tourDistance = (int) row[6];
            String estimatedTime = (String) row[7];

            Tour tour = new Tour(name, description, from, to, transportType, tourDistance, estimatedTime);
            tour.setId(tourId);
            toursList.add(tour);
        }

        session.getTransaction().commit();
        session.close();
    }



    public ArrayList<Tour> getAllTours(){
            return toursList;
        }

    public ArrayList<TourLog> getAllTourLogs(){
            return tourLogsList;
    }

    public ArrayList<Tour> getTours(String Name){
        ArrayList<Tour> searchedTours = new ArrayList<>();
        for (Tour t: toursList){
            if (t.getName().toLowerCase().contains(Name.toLowerCase())){
                searchedTours.add(t);
            }
        }
        return searchedTours;
    }

    public Tour getTour(int id){
        for (Tour t: toursList){
            if (t.getId() == id){
                return t;
            }
        }
        return null;
    }

    public ArrayList<TourLog> getTourLogs(String Name){
        ArrayList<TourLog> searchedTourLogs = new ArrayList<>();
        for (TourLog t: tourLogsList){
            if (getTour(t.getTourId()).getName().toLowerCase().contains(Name.toLowerCase())){
                searchedTourLogs.add(t);
            }
        }
        return searchedTourLogs;
    }

    public TourLog getTourLog(int id){
        if (id < 0) {
            return null;
        }
        for (TourLog t: tourLogsList){
            if (t.getTourLogId() == id){
                return t;
            }
        }
        return null;
    }

    public String addTour(String name, String description, String from, String to, String transportType, String tourDistance, String estimatedTime) {
        if (name.isEmpty() || description.isEmpty() || from.isEmpty() || to.isEmpty() || transportType.isEmpty() || tourDistance.isEmpty() || estimatedTime.isEmpty()){
            return "Fill All Fields";
        }
        if (isStringInSecure(name) || isStringInSecure(description)|| isStringInSecure(from) || isStringInSecure(to) || isStringInSecure(transportType) || isStringInSecure(estimatedTime)){
            return "The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters";
        }
        try {
            int tourDis = Integer.parseInt(tourDistance);
            Tour t = new Tour(name, description, from, to, transportType, tourDis, estimatedTime);
            t.save(); // Saving in PostgresSQL Database
            toursList.add(t);
            return "Success";
        } catch (NumberFormatException e) {
            return "Tour Distance must be a Number";
        }
    }

    public String updateTour(int id, String name, String description, String from, String to, String transportType, String tourDistance, String estimatedTime) {
        if (name.isEmpty() || description.isEmpty() || from.isEmpty() || to.isEmpty() || transportType.isEmpty() || tourDistance.isEmpty() || estimatedTime.isEmpty()){
            return "Fill All Fields";
        }
        if (isStringInSecure(name) || isStringInSecure(description)|| isStringInSecure(from) || isStringInSecure(to) || isStringInSecure(transportType) || isStringInSecure(estimatedTime)){
            return "The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters";
        }
        try {
            int tourDis = Integer.parseInt(tourDistance);
            for (Tour t: toursList){
                if (t.getId() == id){
                    t.update(name, description, from, to, transportType, tourDis, estimatedTime);
                    t.update(); // Updating in PostgreSQl
                    return "Success";
                }
            }
        } catch (NumberFormatException e) {
            return "Tour Distance must be a Number";
        }

        return "ID not found";
    }

    public String addTourLog(String tourId, String difficulty, String totalTime, String totalDistance, LocalDate datetime, String rating, String comment) {
        if (tourId.isEmpty() || difficulty.isEmpty() || totalTime.isEmpty() || totalDistance.isEmpty() || datetime == null || rating.isEmpty() || comment.isEmpty()){
            return "Fill All Fields";
        }
        if (isStringInSecure(tourId) || isStringInSecure(difficulty) || isStringInSecure(totalTime)|| isStringInSecure(totalDistance) || isStringInSecure(rating) || isStringInSecure(comment)){
            return "The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters";
        }
        try {
            int totalDis = Integer.parseInt(totalDistance);
            int id = 0;
            for (Tour t: toursList){
                if (t.getName().equals(tourId)){
                    id = t.getId();
                    break;
                }
            }
            Date date = Date.from(datetime.atStartOfDay(ZoneId.systemDefault()).toInstant());
            TourLog tl = new TourLog(id, difficulty, totalTime, totalDis, date, Float.parseFloat(rating), comment);
            tourLogsList.add(tl);
            tl.save();
            return "Success";
        } catch (NumberFormatException e) {
            return "Total Distance must be a Number";
        }
    }

    public String updateTourLog(int tourLogId, String tourId, String difficulty, String totalTime, String totalDistance, LocalDate datetime, String rating, String comment) {
        if (tourId.isEmpty() || difficulty.isEmpty() || totalTime.isEmpty() || totalDistance.isEmpty() || datetime == null || rating.isEmpty() || comment.isEmpty()){
            return "Fill All Fields";
        }
        if (isStringInSecure(difficulty) || isStringInSecure(totalTime)|| isStringInSecure(totalDistance) || isStringInSecure(rating) || isStringInSecure(comment)){
            return "The Input Data may lead to SQL Injection. Use only alphabets, numbers and some special Characters";
        }
        try {
            int totalDis = Integer.parseInt(totalDistance);
            int id = 0;
            for (Tour t: toursList){
                if (t.getName().equals(tourId)){
                    id = t.getId();
                    break;
                }
            }
            Date date = Date.from(datetime.atStartOfDay(ZoneId.systemDefault()).toInstant());

            for (TourLog t: tourLogsList){
                if (t.getTourLogId() == tourLogId){
                    t.update(id, difficulty, totalTime, totalDis, date, Float.parseFloat(rating), comment);
                    t.update(); // Updating in Postgresql Database
                    return "Success";
                }
            }
        } catch (NumberFormatException e) {
            return "Total Distance must be a Number";
        }
        return "ID not Found";
    }

    public ArrayList<String> getTourNames(){
        ArrayList<String> tourNames = new ArrayList<>();
        for (Tour t: toursList){
            tourNames.add(t.getName());
        }
        return tourNames;
    }

    public String deleteTour(int id){
        if (id < 0) {
            return "Invalid ID";
        }

        logger.info("Deleting the Tour (ID: " + id + " )");
        for (TourLog t: tourLogsList){
            if (t.getTourId() == id){
                t.delete();
                tourLogsList.remove(t);
            }
        }

        for (Tour t: toursList){
            if (t.getId() == id){
                t.delete();
                toursList.remove(t);
                logger.info("Deleted the Tour (ID: " + id + " )");
                return "Success";
            }
        }

        return "Tour Not Found";
    }

    public String deleteTourLog(int id){
        if (id < 0) {
            return "Invalid ID";
        }
        logger.info("Deleting the TourLog (ID: " + id + " )");
        for (TourLog t: tourLogsList){
            if (t.getTourLogId() == id){
                tourLogsList.remove(t);
                logger.info("Deleted the TourLog (ID: " + id + " )");
                return "Success";
            }
        }
        return "Tour Log Not Found";
    }
}
