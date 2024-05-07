package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.personLocation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteRelationByIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonLocationDB;
import jakarta.persistence.EntityManagerFactory;

public class DeletePersonLocationOperation extends AbstractDeleteRelationByIdOperation<PersonDB, LocationDB, PersonLocationDB> {

    public DeletePersonLocationOperation(EntityManagerFactory emf, long primaryId, long secondaryId) {
        super(emf, PersonLocationDB.class, primaryId, secondaryId);
    }

}
