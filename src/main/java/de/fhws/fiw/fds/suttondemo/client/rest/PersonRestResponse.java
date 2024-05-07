package de.fhws.fiw.fds.suttondemo.client.rest;

import de.fhws.fiw.fds.sutton.client.utils.Link;
import de.fhws.fiw.fds.sutton.client.web.WebApiResponse;
import de.fhws.fiw.fds.sutton.server.api.serviceAdapters.HttpHeaders;
import de.fhws.fiw.fds.suttondemo.client.models.PersonClientModel;
import okhttp3.Headers;

import java.util.Collection;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class PersonRestResponse extends WebApiResponse<PersonClientModel> {

    private final static String HEADER_LOCATION = HttpHeaders.LOCATION;

    private static final String HEADER_TOTALNUMBEROFRESULTS = "X-totalnumberofresults";

    private static final String HEADER_NUMBEROFRESULTS = "X-numberofresults";

    private Map<String, Link> mapRelationTypeToLink;

    public PersonRestResponse(WebApiResponse<PersonClientModel> response) {
        this(
                response.getResponseData(),
                response.getResponseHeaders(),
                response.getLastStatusCode()
        );
    }

    public PersonRestResponse(final Collection<PersonClientModel> responseData,
                              final Headers headers, final int lastStatusCode) {
        super(responseData, headers, lastStatusCode);
        readAllLinks();
    }

    public Optional<String> getLocationHeader() {
        return getResponseHeaders().values(HEADER_LOCATION).stream().findFirst();
    }

    public final boolean containsLink(final String relationType) {
        return this.mapRelationTypeToLink.containsKey(relationType);
    }

    public final Link getLink(final String relationType) {
        return this.mapRelationTypeToLink.getOrDefault(relationType, null);
    }

    public final Map<String, Link> getLinks() {
        return this.mapRelationTypeToLink;
    }

    public final int getTotalNumberOfResults() {
        return getHeaderAsInt(HEADER_TOTALNUMBEROFRESULTS);
    }

    /**
     * @return the number {@link Integer} of received results in the response after executing an HTTP GET collection request
     */
    public final int getNumberOfResults() {
        return getHeaderAsInt(HEADER_NUMBEROFRESULTS);
    }

    private void readAllLinks() {
        this.mapRelationTypeToLink = getResponseHeaders()
                .values("Link")
                .stream()
                .map(l -> Link.parseFromHttpHeader(l))
                .collect(Collectors.toMap(l -> l.getRelationType(), l -> l));
    }

    private int getHeaderAsInt(String headerName) {
        final String value = getResponseHeaders().get(headerName);
        return value != null ? Integer.parseInt(value) : -1;
    }

}
