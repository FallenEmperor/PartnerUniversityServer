package de.fhws.fiw.fds.suttondemo.server.database.hibernate.datafaker;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;

import java.time.ZoneId;
import java.util.concurrent.TimeUnit;

public class LocationDataFaker {

    private final Faker faker;

    public LocationDataFaker() {
        this.faker = new Faker();
    }

    public LocationDB createLocation() {
        final LocationDB returnValue = new LocationDB();
        returnValue.setCityName(faker.address().cityName());
        returnValue.setLatitude(Math.random() * 360);
        returnValue.setLongitude(Math.random() * 360);
        returnValue.setVisitedOn(faker.date().past(1, TimeUnit.DAYS).toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        return returnValue;
    }

}
