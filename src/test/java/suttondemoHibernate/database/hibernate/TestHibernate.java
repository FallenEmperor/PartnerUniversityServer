package suttondemoHibernate.database.hibernate;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.CollectionModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.hibernate.results.SingleModelHibernateResult;
import de.fhws.fiw.fds.sutton.server.database.results.NoContentResult;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao.PersonDaoHibernate;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.dao.PersonDaoHibernateImpl;
import de.fhws.fiw.fds.suttondemo.server.database.hibernate.models.PersonDB;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


public class TestHibernate extends AbstractHibernateTestHelper{

    @Test
    public void test_db_save_successful() throws Exception {
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate dao = new PersonDaoHibernateImpl();
        NoContentResult resultSave = dao.create(person);

        assertFalse(resultSave.hasError());

        CollectionModelHibernateResult<PersonDB> resultGetAll = dao.readAll();
        assertEquals(1, resultGetAll.getResult().size());
    }

    @Test
    public void test_db_load_by_id() throws Exception {
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate dao = new PersonDaoHibernateImpl();
        NoContentResult resultSave = dao.create(person);

        assertFalse(resultSave.hasError());

        SingleModelHibernateResult<PersonDB> resultGetById = dao.readById(person.getId());
        assertEquals(person.getEmailAddress(), resultGetById.getResult().getEmailAddress());
    }

    @Test
    public void test_db_delete_by_id() throws Exception {
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate dao = new PersonDaoHibernateImpl();
        NoContentResult resultSave = dao.create(person);

        assertFalse(resultSave.hasError());

        NoContentResult resultDelete = dao.delete(person.getId());
        assertFalse(resultDelete.hasError());
    }

    @Test
    public void test_db_update() throws Exception {
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate dao = new PersonDaoHibernateImpl();
        NoContentResult resultSave = dao.create(person);

        assertFalse(resultSave.hasError());

        SingleModelHibernateResult<PersonDB> resultGetById = dao.readById(person.getId());
        PersonDB personInDB = resultGetById.getResult();
        personInDB.setFirstName("Jimmy");

        NoContentResult resultUpdate = dao.update(personInDB);
        assertFalse(resultUpdate.hasError());

        SingleModelHibernateResult<PersonDB> resultGetByIdAfterUpdate = dao.readById(person.getId());
        assertEquals("Jimmy", resultGetByIdAfterUpdate.getResult().getFirstName());
    }


    @Test
    public void test_db_load_by_names() throws Exception {
        PersonDB person = new PersonDB();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setBirthDate(LocalDate.of(1948, 7, 7));
        person.setEmailAddress("james.bond@thws.de");

        PersonDaoHibernate dao = new PersonDaoHibernateImpl();
        NoContentResult resultSave = dao.create(person);

        assertFalse(resultSave.hasError());

        CollectionModelHibernateResult<PersonDB> resultByNames = dao.readByFirstNameAndLastName("", "Bond", new SearchParameter());
        assertEquals(1, resultByNames.getResult().size());
    }

}
