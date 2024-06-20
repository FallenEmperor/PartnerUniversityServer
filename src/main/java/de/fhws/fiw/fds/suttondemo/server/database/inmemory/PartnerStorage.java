package de.fhws.fiw.fds.suttondemo.server.database.inmemory;

import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.inmemory.AbstractInMemoryStorage;
import de.fhws.fiw.fds.sutton.server.database.inmemory.InMemoryPaging;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.PartnerDao;

import java.util.function.Predicate;

/* Partner Storage allows for filtration of all partner universities by the university name, country, or contact person */

public class PartnerStorage extends AbstractInMemoryStorage<PartnerUniversity> implements PartnerDao {
    @Override
    public CollectionModelResult<PartnerUniversity> readByUniversityName(String universityName, SearchParameter searchParameter) {
        return InMemoryPaging.page(this.readAllByPredicate(
                byUniversityName(universityName),
                searchParameter
        ), searchParameter.getOffset(), searchParameter.getSize());
    }

    public void resetDatabase() {
        this.storage.clear();
    }

    private Predicate<PartnerUniversity> byUniversityName(String university) {
        return p -> (university.isEmpty() || p.getUniversityName().equals(university));
    }

}
