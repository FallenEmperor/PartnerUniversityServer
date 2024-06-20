
package de.fhws.fiw.fds.suttondemo.server.api.states.modules;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.services.ServiceContext;
import de.fhws.fiw.fds.sutton.server.api.states.get.AbstractGetCollectionState;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.Module;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;
import jakarta.ws.rs.core.Response;

public class GetAllModules extends AbstractGetCollectionState<Response, Module> {
    public GetAllModules(ServiceContext serviceContext, AbstractQuery<Response, Module> query) {
        super(serviceContext, query);
    }


    @Override
    protected void defineTransitionLinks() {

        // Link to create new module

        addLink(ModuleUri.REL_PATH, ModuleRelTypes.CREATE_MODULE, getAcceptRequestHeader());

        // Link to get single module

        addLink(ModuleUri.REL_PATH_ID, ModuleRelTypes.GET_SINGLE_MODULE, getAcceptRequestHeader());

    }

    public static class AllModules<R> extends AbstractQuery<R, Module> {
        @Override
        protected CollectionModelResult<Module> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
            return DaoFactory.getInstance().getModuleDao().readAll();
        }
    }

}
