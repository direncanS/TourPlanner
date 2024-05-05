package com.example.tourapp.Model;

import org.hibernate.Session;

import java.math.BigInteger;

public class Tour {
    private int id;
    private String name;
    private String description;
    private String from;
    private String to;
    private String transportType;
    private int tourDistance;
    private String estimatedTime;

    public Tour() {

    }

    public int getId() {
        return id;
    }

    public Tour(String name, String description, String from, String to, String transportType, int tourDistance, String estimatedTime) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
    }

    public void update(String name, String description, String from, String to, String transportType, int tourDistance, String estimatedTime) {
        this.name = name;
        this.description = description;
        this.from = from;
        this.to = to;
        this.transportType = transportType;
        this.tourDistance = tourDistance;
        this.estimatedTime = estimatedTime;
    }
    public void setId(int id){ this.id = id; }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public int getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(int tourDistance) {
        this.tourDistance = tourDistance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    public void save(){
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "INSERT INTO tours (name, description, \"from\", \"to\", transport_type, tour_distance, estimated_time) " +
                "VALUES (:name, :description, :from, :to, :transportType, :tourDistance, :estimatedTime) " +
                "RETURNING id";
        Number insertedId = (Number) session.createNativeQuery(sql)
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("transportType", transportType)
                .setParameter("tourDistance", tourDistance)
                .setParameter("estimatedTime", estimatedTime)
                .getSingleResult();
        session.getTransaction().commit();

        this.id = insertedId.intValue();
    }

    public void update() {
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "UPDATE tours SET name = :name, description = :description, \"from\" = :from, " +
                "\"to\" = :to, transport_type = :transportType, tour_distance = :tourDistance, " +
                "estimated_time = :estimatedTime WHERE id = :id";

        session.createNativeQuery(sql)
                .setParameter("name", name)
                .setParameter("description", description)
                .setParameter("from", from)
                .setParameter("to", to)
                .setParameter("transportType", transportType)
                .setParameter("tourDistance", tourDistance)
                .setParameter("estimatedTime", estimatedTime)
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void delete() {
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "DELETE FROM tours WHERE id = :id";

        session.createNativeQuery(sql)
                .setParameter("id", id)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

}


