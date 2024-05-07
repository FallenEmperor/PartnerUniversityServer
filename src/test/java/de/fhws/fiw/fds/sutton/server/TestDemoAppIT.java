package de.fhws.fiw.fds.sutton.server;


import de.fhws.fiw.fds.suttondemo.client.web.PersonWebClient;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class TestDemoAppIT {

    @Test
    public void test_always_true() throws IOException {
        var client = new PersonWebClient();
        var response = client.getCollectionOfPersons("http://localhost:8080/demo/api/persons");
        assertEquals(200, response.getLastStatusCode());
    }

}
