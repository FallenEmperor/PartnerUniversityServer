package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.location;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import jakarta.persistence.EntityManagerFactory;

public class LoadLocationByIdOperation extends AbstractReadByIdOperation<LocationDB> {

    public LoadLocationByIdOperation(EntityManagerFactory emf, long idToLoad) {
        super(emf, LocationDB.class, idToLoad);
    }

}
