package com.example.tourapp.Model;

import jakarta.persistence.*;

import java.util.Objects;

@Entity
@Table(name = "tours", schema = "public", catalog = "postgres")
public class ToursEntity {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Basic
    @Column(name = "name")
    private String name;
    @Basic
    @Column(name = "description")
    private String description;
    @Basic
    @Column(name = "from")
    private String from;
    @Basic
    @Column(name = "to")
    private String to;
    @Basic
    @Column(name = "transport_type")
    private String transportType;
    @Basic
    @Column(name = "tour_distance")
    private Integer tourDistance;
    @Basic
    @Column(name = "estimated_time")
    private String estimatedTime;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

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

    public Integer getTourDistance() {
        return tourDistance;
    }

    public void setTourDistance(Integer tourDistance) {
        this.tourDistance = tourDistance;
    }

    public String getEstimatedTime() {
        return estimatedTime;
    }

    public void setEstimatedTime(String estimatedTime) {
        this.estimatedTime = estimatedTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToursEntity that = (ToursEntity) o;
        return id == that.id && Objects.equals(name, that.name) && Objects.equals(description, that.description) && Objects.equals(from, that.from) && Objects.equals(to, that.to) && Objects.equals(transportType, that.transportType) && Objects.equals(tourDistance, that.tourDistance) && Objects.equals(estimatedTime, that.estimatedTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, description, from, to, transportType, tourDistance, estimatedTime);
    }
}
