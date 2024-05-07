package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.location;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadAllOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import jakarta.persistence.EntityManagerFactory;

public class LoadAllLocationsOperations extends AbstractReadAllOperation<LocationDB> {

    public LoadAllLocationsOperations(EntityManagerFactory emf, SearchParameter searchParameter) {
        super(emf, LocationDB.class, searchParameter);
    }

}
