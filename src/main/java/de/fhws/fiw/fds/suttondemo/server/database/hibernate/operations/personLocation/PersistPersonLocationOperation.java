package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.personLocation;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.relation.AbstractPersistRelationByPrimaryIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.LocationDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonLocationDB;
import jakarta.persistence.EntityManagerFactory;

public class PersistPersonLocationOperation extends AbstractPersistRelationByPrimaryIdOperation<PersonDB, LocationDB, PersonLocationDB> {

    public PersistPersonLocationOperation(EntityManagerFactory emf, long primaryId, LocationDB locationDB) {
        super(emf, PersonLocationDB.class, PersonDB.class, primaryId, locationDB);
    }
}
