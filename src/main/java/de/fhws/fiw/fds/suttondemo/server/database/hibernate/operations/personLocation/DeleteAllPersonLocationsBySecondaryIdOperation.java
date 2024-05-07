package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.personLocation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractDeleteAllRelationsBySecondaryIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonLocationDB;
import jakarta.persistence.EntityManagerFactory;

public class DeleteAllPersonLocationsBySecondaryIdOperation extends AbstractDeleteAllRelationsBySecondaryIdOperation<PersonDB, LocationDB, PersonLocationDB> {

    public DeleteAllPersonLocationsBySecondaryIdOperation(EntityManagerFactory emf, long secondaryId) {
        super(emf, PersonLocationDB.class, secondaryId);
    }

}
