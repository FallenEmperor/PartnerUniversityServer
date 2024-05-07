package de.fhws.fiw.fds.sutton.server.database.hibernate;

import de.fhws.fiw.fds.sutton.server.api.rateLimiting.model.APIKey;
import de.fhws.fiw.fds.sutton.server.api.rateLimiting.operation.PersistAPIKeyOperation;
import de.fhws.fiw.fds.sutton.server.api.rateLimiting.operation.ReadAllAPIKeysOperation;
import de.fhws.fiw.fds.sutton.server.api.security.model.UserDB;
import de.fhws.fiw.fds.sutton.server.api.security.operations.PersistUserOperation;
import de.fhws.fiw.fds.sutton.server.api.security.operations.ReadAllUsersOperation;
import de.fhws.fiw.fds.sutton.server.database.SearchParameter;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class DatabaseInstaller implements IDatabaseConnection {

    public void install() {
        initializeAPIKeys();
        initializeUsers();
    }

    private void initializeUsers() {
        List<UserDB> users = List.of(
                new UserDB("QuantumCoder", "test123", "ADMIN"),
                new UserDB("InfiniteIllusion", "test123", "SUPERVISOR"),
                new UserDB("EchoEnigma", "test123", "USER")
        );
        users.forEach(user -> new PersistUserOperation(SUTTON_EMF, user).start());
        System.out.println("Installed Users");

        Collection<UserDB> usersFromDB = new ReadAllUsersOperation(SUTTON_EMF, SearchParameter.DEFAULT).start().getResult();
        usersFromDB.forEach(user -> System.out.println("Found User on DB: " + user));
    }

    // TODO change with when Authorization with Users is Implemented
    private void initializeAPIKeys() {
        List<APIKey> apiKeys = new ArrayList<>();
        apiKeys.add(new APIKey("API_KEY_01", 10,10));
        apiKeys.add(new APIKey("API_KEY_02", 10,10));
        apiKeys.add(new APIKey("API_KEY_03", 10,10));
        apiKeys.add(new APIKey("API_KEY_04", 10,10));
        apiKeys.add(new APIKey("API_KEY_05", 10,10));
        apiKeys.forEach(apiKey -> {
            new PersistAPIKeyOperation(SUTTON_EMF, apiKey).start();
        });
        System.out.println("Installed API-Keys.");

        Collection<APIKey> apiKeysOnDB = new ReadAllAPIKeysOperation(SUTTON_EMF, SearchParameter.DEFAULT).start().getResult();
        apiKeysOnDB.forEach(apiKey -> {
            System.out.println("Found API-Key on DB: " + apiKey.toString());
        });
    }

}
