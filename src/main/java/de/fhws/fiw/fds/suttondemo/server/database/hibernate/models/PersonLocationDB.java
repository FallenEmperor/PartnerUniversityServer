package de.fhws.fiw.fds.suttondemo.server.database.hibernate.models;

import de.fhws.fiw.fds.sutton.server.database.hibernate.models.AbstractDBRelation;
import jakarta.persistence.Entity;

@Entity
public class PersonLocationDB extends AbstractDBRelation<PersonDB, LocationDB> {
    // marker class with @Entity Annotation
}
