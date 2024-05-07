package de.fhws.fiw.fds.suttondemo.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBModel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.time.LocalDate;

@Entity
@Table(name = "location")
public class LocationDB extends AbstractDBModel {

    @Column(name = "cityName")
    private String cityName;

    @Column(name = "longitude")
    private double longitude;

    @Column(name = "latitude")
    private double latitude;

    @Column(name = "visitedOn")
    private LocalDate visitedOn;

    public LocationDB() {
        // make JPA happy
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public LocalDate getVisitedOn() {
        return visitedOn;
    }

    public void setVisitedOn(LocalDate visitedOn) {
        this.visitedOn = visitedOn;
    }
}
