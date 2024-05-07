package de.fhws.fiw.fds.sutton.server.database.hibernate.models;

public class SuttonColumnConstants {

    //ColumnConstants for Entity AbstractDBModel
    public final static String DB_RELATION_ID = "dbRelationId";
    public final static String PRIMARY_ID = "primaryId";
    public final static String SECONDARY_ID = "secondaryId";

    //ColumnConstants for Entity AbstractDBRelation
    public final static String PRIMARY_MODEL = "primaryModel";
    public final static String SECONDARY_MODEL = "secondaryModel";

    //ColumnConstants for Entity APIKey
    public final static String API_KEY = "apiKey";
    public final static String API_KEY_REQUESTS = "requests";
    public final static String API_KEY_LAST_RESET = "lastReset";
    public final static String API_KEY_RESET_RATE_IN_SCONDS = "resetRateInSeconds";
    public final static String API_KEY_REQUEST_LIMIT = "requestLimit";

    //ColumnConstants for Entity UserDB
    public final static String USER_NAME = "name";
    public final static String USER_PASSWORD = "password";
    public final static String USER_ROLE = "role";

}
