package com.example.tourapp.Model;

import jakarta.persistence.*;

import java.sql.Timestamp;
import java.util.Objects;

@Entity
@Table(name = "tour_logs", schema = "public", catalog = "postgres")
public class TourLogsEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "tour_log_id")
    private int tourLogId;
    @Basic
    @Column(name = "tour_id")
    private Integer tourId;
    @Basic
    @Column(name = "difficulty")
    private String difficulty;
    @Basic
    @Column(name = "total_time")
    private String totalTime;
    @Basic
    @Column(name = "total_distance")
    private Integer totalDistance;
    @Basic
    @Column(name = "datetime")
    private Timestamp datetime;
    @Basic
    @Column(name = "rating")
    private Double rating;
    @Basic
    @Column(name = "comment")
    private String comment;

    public int getTourLogId() {
        return tourLogId;
    }

    public void setTourLogId(int tourLogId) {
        this.tourLogId = tourLogId;
    }

    public Integer getTourId() {
        return tourId;
    }

    public void setTourId(Integer tourId) {
        this.tourId = tourId;
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

    public Integer getTotalDistance() {
        return totalDistance;
    }

    public void setTotalDistance(Integer totalDistance) {
        this.totalDistance = totalDistance;
    }

    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TourLogsEntity that = (TourLogsEntity) o;
        return tourLogId == that.tourLogId && Objects.equals(tourId, that.tourId) && Objects.equals(difficulty, that.difficulty) && Objects.equals(totalTime, that.totalTime) && Objects.equals(totalDistance, that.totalDistance) && Objects.equals(datetime, that.datetime) && Objects.equals(rating, that.rating) && Objects.equals(comment, that.comment);
    }

    @Override
    public int hashCode() {
        return Objects.hash(tourLogId, tourId, difficulty, totalTime, totalDistance, datetime, rating, comment);
    }
}
