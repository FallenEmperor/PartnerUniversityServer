package de.fhws.fiw.fds.suttondemo.server.database.hibernate.operations.person;

import de.fhws.fiw.fds.sutton.server.database.hibernate.operations.model.AbstractDeleteByIdOperation;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import jakarta.persistence.EntityManagerFactory;

public class DeleteOperationPersonById extends AbstractDeleteByIdOperation<PersonDB> {

    public DeleteOperationPersonById(EntityManagerFactory emf, long idToDelete) {
        super(emf, PersonDB.class, idToDelete);
    }

}
