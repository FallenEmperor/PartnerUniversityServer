package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.location;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import jakarta.persistence.EntityManagerFactory;

public class DeleteOperationLocationById extends AbstractDeleteByIdOperation<LocationDB> {

    public DeleteOperationLocationById(EntityManagerFactory emf, long idToDelete) {
        super(emf, LocationDB.class, idToDelete);
    }

}
