package suttondemoHibernate.getpersons;

import de.fhws.fiw.fds.suttondemo.client.models.PersonClientModel;
import de.fhws.fiw.fds.suttondemo.client.web.PersonWebClient;
import de.fhws.fiw.fds.suttondemo.client.web.PersonWebResponse;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.io.IOException;
import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;


// TODO adjust these tests to work with new code
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class TestPersonIT {

    private final static String BASE_URL = "http://localhost:8080/dbdemo/api/persons";

    private PersonWebClient client;

    @BeforeAll
    public void set_up() throws IOException {
        this.client = new PersonWebClient();
        this.client.resetDatabaseOnServer(BASE_URL);
    }

    @Test
    public void test_get_persons_must_be_empty() throws IOException {
        final PersonWebResponse response = this.client.getCollectionOfPersons(BASE_URL);
        assertEquals(200, response.getLastStatusCode());
        assertEquals(0, response.getResponseData().size());
    }

    @Test
    public void test_post_person() throws IOException {
        final PersonClientModel person = new PersonClientModel();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setEmailAddress("james.bond@thws.de");
        person.setBirthDate(LocalDate.of(1940, 7, 7));

        final PersonWebResponse response = this.client.postNewPerson(BASE_URL, person);

        assertEquals(201, response.getLastStatusCode());
        assertTrue(response.getLocationHeader().isPresent());

        final String locationUrl = response.getLocationHeader().get();
        final PersonWebResponse response2 = this.client.getSinglePerson(locationUrl);

        assertEquals(200, response2.getLastStatusCode());

        final PersonClientModel responsePerson = response2.getFirstResponse().get();
        assertEquals(person.getFirstName(), responsePerson.getFirstName());
        assertEquals(person.getLastName(), responsePerson.getLastName());
        assertEquals(person.getEmailAddress(), responsePerson.getEmailAddress());
        assertEquals(person.getBirthDate(), responsePerson.getBirthDate());
    }

    @Test
    public void test_post_person_then_update() throws IOException {
        final PersonClientModel person = new PersonClientModel();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setEmailAddress("james.bond@thws.de");
        person.setBirthDate(LocalDate.of(1940, 7, 7));

        final PersonWebResponse response = this.client.postNewPerson(BASE_URL, person);
        final String personUrl = response.getLocationHeader().get();

        final PersonWebResponse response2 = this.client.getSinglePerson(personUrl);
        final PersonClientModel personResponse = response2.getFirstResponse().get();

        personResponse.setFirstName("Jimmy");

        final PersonWebResponse response3 = this.client.putPerson(response.getLocationHeader().get(), personResponse);

        assertEquals(204, response3.getLastStatusCode());
    }

    @Test
    public void test_post_person_then_delete() throws IOException {
        final PersonClientModel person = new PersonClientModel();
        person.setFirstName("James");
        person.setLastName("Bond");
        person.setEmailAddress("james.bond@thws.de");
        person.setBirthDate(LocalDate.of(1940, 7, 7));

        final PersonWebResponse response = this.client.postNewPerson(BASE_URL, person);
        final String personUrl = response.getLocationHeader().get();
        final PersonWebResponse response3 = this.client.deletePerson(personUrl);

        assertEquals(204, response3.getLastStatusCode());

        final PersonWebResponse response2 = this.client.getSinglePerson(personUrl);

        assertEquals(404, response2.getLastStatusCode());
    }

}
