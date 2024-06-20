package de.fhws.fiw.fds.suttondemo.server.database;

import de.fhws.fiw.fds.sutton.server.database.IDatabaseRelationAccessObject;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;

public interface PartnerModuleDao extends IDatabaseRelationAccessObject<Module> {

    CollectionModelResult<Module> readAllLinked(long primaryId, SearchParameter searchParameter);

    void initializeDatabase();

    void resetDatabase();

}
