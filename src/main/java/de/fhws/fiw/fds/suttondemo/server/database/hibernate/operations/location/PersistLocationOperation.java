package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.location;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import jakarta.persistence.EntityManagerFactory;

public class PersistLocationOperation extends AbstractPersistOperation<LocationDB> {

    public PersistLocationOperation(EntityManagerFactory emf, LocationDB modelToPersist) {
        super(emf, modelToPersist);
    }

}
