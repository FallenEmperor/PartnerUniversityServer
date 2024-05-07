package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.PersonDao;
import de.fhws.fiw.fds.suttondemo.server.api.models.Person;

import java.util.function.Predicate;

public class PersonStorage extends AbstractInMemoryStorage<Person> implements PersonDao {
    @Override
    public CollectionModelResult<Person> readByFirstNameAndLastName(String firstName, String lastName, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byFirstAndLastName(firstName, lastName),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    private Predicate<Person> byFirstAndLastName(String firstName, String lastName) {
        return p -> (firstName.isEmpty() || p.getFirstName().equals(firstName) ) && ( lastName.isEmpty() || p.getLastName().equals(lastName));
    }

}
