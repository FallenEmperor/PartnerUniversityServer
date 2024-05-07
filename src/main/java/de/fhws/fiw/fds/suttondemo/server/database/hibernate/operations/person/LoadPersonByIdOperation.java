package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.person;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractReadByIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class LoadPersonByIdOperation extends AbstractReadByIdOperation<PersonDB> {

    public LoadPersonByIdOperation(EntityManagerFactory emf, long idToLoad) {
        super(emf, PersonDB.class, idToLoad);
    }

}
