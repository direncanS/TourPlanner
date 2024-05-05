package com.example.tourapp.Model;

import org.hibernate.Session;

import java.math.BigInteger;
import java.sql.Timestamp;
import java.util.Date;
import com.example.tourapp.Model.HibranateUtil;

public class TourLog {
    private static int tourLogIdCount = 0;
    private int tourLogId;
    private int tourId;
    private String difficulty;
    private String totalTime;
    private int totalDistance;
    private Date datetime;
    private float rating;
    private String comment;

    public TourLog(int tourId, String difficulty, String totalTime, int totalDistance, Date datetime, float rating, String comment) {
        this.tourLogId = TourLog.tourLogIdCount + 1;
        TourLog.tourLogIdCount++;
        this.tourId = tourId;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
        this.datetime = datetime;
        this.rating = rating;
        this.comment = comment;
    }

    public void update(int tourId, String difficulty, String totalTime, int totalDistance, Date datetime, float rating, String comment) {
        this.tourId = tourId;
        this.difficulty = difficulty;
        this.totalTime = totalTime;
        this.totalDistance = totalDistance;
        this.datetime = datetime;
        this.rating = rating;
        this.comment = comment;
    }

    public int getTourId() {
        return tourId;
    }

    public void setTourId(int tourId) {
        this.tourId = tourId;
    }

    public int getTourLogId() {
        return tourLogId;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }

    public int getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(int totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setTourLogId(int tourLogId){
        this.tourLogId = tourLogId;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public void save() {
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "INSERT INTO tour_logs (tour_id, difficulty, total_time, total_distance, datetime, rating, comment) " +
                "VALUES (:tourId, :difficulty, :totalTime, :totalDistance, :datetime, :rating, :comment) " +
                "RETURNING tour_log_id";

        Number insertedId = (Number) session.createNativeQuery(sql)
                .setParameter("tourId", tourId)
                .setParameter("difficulty", difficulty)
                .setParameter("totalTime", totalTime)
                .setParameter("totalDistance", totalDistance)
                .setParameter("datetime", new Timestamp(datetime.getTime()))
                .setParameter("rating", rating)
                .setParameter("comment", comment)
                .getSingleResult();

        session.getTransaction().commit();
        session.close();
        this.tourLogId = insertedId.intValue();
    }

    public void update() {
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "UPDATE tour_logs SET tour_id = :tourId, difficulty = :difficulty, total_time = :totalTime, " +
                "total_distance = :totalDistance, datetime = :datetime, rating = :rating, comment = :comment " +
                "WHERE tour_log_id = :tourLogId";

        session.createNativeQuery(sql)
                .setParameter("tourId", tourId)
                .setParameter("difficulty", difficulty)
                .setParameter("totalTime", totalTime)
                .setParameter("totalDistance", totalDistance)
                .setParameter("datetime", new Timestamp(datetime.getTime()))
                .setParameter("rating", rating)
                .setParameter("comment", comment)
                .setParameter("tourLogId", tourLogId)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }

    public void delete() {
        Session session = HibranateUtil.getSessionFactory().openSession();
        session.beginTransaction();

        String sql = "DELETE FROM tour_logs WHERE tour_log_id = :tourLogId";

        session.createNativeQuery(sql)
                .setParameter("tourLogId", tourLogId)
                .executeUpdate();

        session.getTransaction().commit();
        session.close();
    }
}
