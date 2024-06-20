package de.fhws.fiw.fds.suttondemo.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractRelationQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;

public class QueryByModuleName <R> extends AbstractRelationQuery<R, Module> {

    public QueryByModuleName(long primaryId, int offset, int size) {
        super(primaryId);
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerModuleDao().readAllLinked(this.primaryId, searchParameter);
    }

}