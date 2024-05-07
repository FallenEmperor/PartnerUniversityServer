package de.fhws.fiw.fds.suttondemo.server.database.hibernate.datafaker;

import com.github.javafaker.Faker;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;

import java.time.ZoneId;

public class PersonDataFaker {

    private final Faker faker;

    public PersonDataFaker() {
        this.faker = new Faker();
    }

    public PersonDB createPerson() {
        final PersonDB returnValue = new PersonDB();
        returnValue.setFirstName(this.faker.name().firstName());
        returnValue.setLastName(this.faker.name().lastName());
        returnValue.setBirthDate(this.faker.date().birthday().toInstant().atZone(ZoneId.systemDefault()).toLocalDate());
        returnValue.setEmailAddress(this.faker.internet().emailAddress());
        return returnValue;
    }

}
