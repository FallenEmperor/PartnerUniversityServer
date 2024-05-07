package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.person;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractPersistOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class PersistPersonOperation extends AbstractPersistOperation<PersonDB> {

    public PersistPersonOperation(EntityManagerFactory emf, PersonDB modelToPersist) {
        super(emf, modelToPersist);
    }

}
