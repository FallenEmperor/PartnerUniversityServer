package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.location;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import jakarta.persistence.EntityManagerFactory;

public class UpdateLocationOperation extends AbstractUpdateOperation<LocationDB> {

    public UpdateLocationOperation(EntityManagerFactory emf, LocationDB modelToUpdate) {
        super(emf, modelToUpdate);
    }

}
