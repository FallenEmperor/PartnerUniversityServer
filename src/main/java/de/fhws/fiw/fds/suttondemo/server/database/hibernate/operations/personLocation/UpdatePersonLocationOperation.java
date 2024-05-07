package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.personLocation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractUpdateRelationByPrimaryIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonLocationDB;
import jakarta.persistence.EntityManagerFactory;

public class UpdatePersonLocationOperation extends AbstractUpdateRelationByPrimaryIdOperation<PersonDB, LocationDB, PersonLocationDB> {

    public UpdatePersonLocationOperation(EntityManagerFactory emf, long primaryId, LocationDB secondaryModelToUpdate) {
        super(emf, PersonLocationDB.class, PersonDB.class, primaryId, secondaryModelToUpdate);
    }

}
