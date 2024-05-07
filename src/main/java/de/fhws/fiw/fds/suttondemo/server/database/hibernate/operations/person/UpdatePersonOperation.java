package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.person;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractUpdateOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class UpdatePersonOperation extends AbstractUpdateOperation<PersonDB> {

    public UpdatePersonOperation(EntityManagerFactory emf, PersonDB modelToUpdate) {
        super(emf, modelToUpdate);
    }

}
