package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.personLocation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsByPrimaryIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonLocationDB;
import jakarta.persistence.EntityManagerFactory;

public class DeleteAllPersonLocationsByPrimaryIdOperation extends AbstractDeleteAllRelationsByPrimaryIdOperation<PersonDB, LocationDB, PersonLocationDB> {

    public DeleteAllPersonLocationsByPrimaryIdOperation(EntityManagerFactory emf, long primaryId) {
        super(emf, PersonLocationDB.class, primaryId);
    }

}
