package de.fhws.fiw.fds.suttondemo.server.api.queries;

import de.fhws.fiw.fds.sutton.server.api.queries.AbstractQuery;
import de.fhws.fiw.fds.sutton.server.api.queries.PagingBehaviorUsingOffsetSize;
import de.fhws.fiw.fds.sutton.server.database.DatabaseException;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;
import de.fhws.fiw.fds.sutton.server.database.results.CollectionModelResult;
import de.fhws.fiw.fds.suttondemo.server.api.models.PartnerUniversity;
import de.fhws.fiw.fds.suttondemo.server.database.DaoFactory;

public class QueryByUniversityName<R> extends AbstractQuery<R, PartnerUniversity> {

    private String uniName;
    private String order;

    public QueryByUniversityName(String uniName, int offset, int size, String order) {
        this.uniName = uniName;
        this.order = order;
        this.pagingBehavior = new PagingBehaviorUsingOffsetSize<>(offset, size);
    }

    public String getUniName() {
        return this.uniName;
    }

    public void setUniName(String uniName) {
        this.uniName = uniName;
    }

    public String getOrder() {
        return this.order;
    }

    public void setOrder(String order) {
        this.order = order;
    }

    public String getOrderAttribute() {
        if(order.length() > 0){
            return this.order.substring(1);
        }
        else{
            return "";
        }
    }

    private String inverseSortingOrderOrDefault(String orderAttribute) {
        if (getOrderAttribute().equals(orderAttribute)) {
            return inverseSortingOrder();
        } else {
            return "%2B" + orderAttribute;
        }
    }

    private String inverseSortingOrder() {
        return this.order.startsWith("+") || this.order.startsWith("%2B") ? "-" + this.order.substring(1) : "%2B" + this.order.substring(1);
    }

    protected CollectionModelResult<PartnerUniversity> doExecuteQuery(SearchParameter searchParameter) throws DatabaseException {
        return DaoFactory.getInstance().getPartnerDao().readByUniversityName(this.uniName, searchParameter);
    }

}
