package de.fhws.fiw.fds.sutton.server;


import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.github.javafaker.Faker;

import de.fhws.fiw.fds.suttondemo.client.models.ModuleModel;
import de.fhws.fiw.fds.suttondemo.client.models.PartnerUniversityModel;
import de.fhws.fiw.fds.suttondemo.client.rest.RealRestClient;

public class TestAppIT {
    final private Faker faker = new Faker();
    private RealRestClient client;

    @BeforeEach
    public void setUp() throws IOException{
       this.client = new RealRestClient();
       this.client.resetDatabase();
    }

    @Test
    public void test_dispatcher_is_available() throws IOException {
        client.start();
        assertEquals(200, client.getLastStatusCode());
    }

    @Test
    public void test_dispatcher_is_get_all_partners_allowed() throws IOException {
        client.start();
        assertTrue(client.isGetAllPartnersAllowed());
    }

    @Test
    public void test_create_partner_is_create_partner_allowed() throws IOException {
        client.start();
        assertTrue(client.isCreatePartnerAllowed());
    }

    @Test void test_create_person() throws IOException
    {
        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("2024-05-03");
        partner.setAutumnSemesterStart("2024-09-15");

        client.createPartner(partner);
        
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_partner_and_get_new_partner() throws IOException
    {
        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerAllowed() );

        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());

        var partnerFromServer = client.partnerData().get(0);
        assertEquals( "THWS", partnerFromServer.getUniversityName() );
    }

    @Test void test_create_5_partners_and_get_all() throws IOException
    {
        /*
         * The next statements look strange, because we call the dispatcher in all
         * iterations. But this is how the client works. The dispatcher is the entry point
         * and we need to call it in order to get the URL to create a new person.
         */
        
        for( int i=0; i<5; i++ ) {
            client.start();

            var partner = new PartnerUniversityModel();
            partner.setUniversityName(faker.name().name());
            partner.setCountry(faker.funnyName().toString());
            partner.setDepartmentName(faker.funnyName().toString());
            partner.setDepartmentUrl(faker.random().toString());
            partner.setContactPerson(faker.funnyName().toString());
            partner.setOutgoingStudents(faker.number().randomDigitNotZero());
            partner.setIncomingStudents(faker.number().randomDigitNotZero());
            partner.setSpringSemesterStart(faker.date().toString());
            partner.setAutumnSemesterStart(faker.date().toString());

            client.createPartner(partner);
            assertEquals(201, client.getLastStatusCode());
        }

        /* Now we call the dispatcher to get the URL to get all persons */
        
        client.start();
        assertTrue( client.isGetAllPartnersAllowed() );

        client.getAllPartners();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.partnerData().size());
        
        /* Set the cursor to the first person, not really necessary, but to make it clear here */
        
        client.setPartnerCursor(0);
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());
    }

    /* 
     * Check the functionality of deleting a partner by creating five, deleting one,
     * and subsequently checking the size of the storage that it lowered by one.
     */

    @Test void test_create_5_partners_and_delete_first() throws IOException
    {
        
        for( int i=0; i<5; i++ ) {
            client.start();

            var partner = new PartnerUniversityModel();
            partner.setUniversityName(faker.name().name());
            partner.setCountry(faker.funnyName().toString());
            partner.setDepartmentName(faker.funnyName().toString());
            partner.setDepartmentUrl(faker.random().toString());
            partner.setContactPerson(faker.funnyName().toString());
            partner.setOutgoingStudents(faker.number().randomDigitNotZero());
            partner.setIncomingStudents(faker.number().randomDigitNotZero());
            partner.setSpringSemesterStart(faker.date().toString());
            partner.setAutumnSemesterStart(faker.date().toString());

            client.createPartner(partner);
            assertEquals(201, client.getLastStatusCode());
        }

        client.start();
        assertTrue( client.isGetAllPartnersAllowed() );

        client.getAllPartners();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.partnerData().size());

        assertTrue( client.isGetSinglePartnerAllowed() );

        client.setPartnerCursor(0); // Index can be changed to check if other objects can be deleted
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isDeletePartnerAllowed());

        client.deletePartner();
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetAllPartnersAllowed());

        // Finally checks whether the partner was deleted

        client.getAllPartners();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(4, client.partnerData().size());

    }

        @Test void test_create_and_update_partner() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetAllPartnersAllowed() );
        client.getAllPartners();

        client.getSinglePartner();
        assertEquals("THWS", client.partnerData().get(0).getUniversityName());

        assertTrue(client.isUpdatePartnerAllowed());

        var newPartner = new PartnerUniversityModel();
        newPartner.setUniversityName("Auburn");
        newPartner.setCountry("USA");
        newPartner.setDepartmentName("Computer Science");
        newPartner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        newPartner.setContactPerson("Biedermann");
        newPartner.setOutgoingStudents(40);
        newPartner.setIncomingStudents(56);
        newPartner.setSpringSemesterStart("15.03.2024");
        newPartner.setAutumnSemesterStart("15.09.2024");

        assertTrue( client.isUpdatePartnerAllowed() );
        client.updatePartner(newPartner);
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetSinglePartnerAllowed());
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Auburn", client.partnerData().get(0).getUniversityName());
        assertEquals("USA", client.partnerData().get(0).getCountry());
        assertEquals("Computer Science", client.partnerData().get(0).getDepartmentName());
    }

    /* Module Test cases begin here */


    @Test void test_create_module_of_partner() throws IOException
    {

        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerAllowed() );

        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateSingleModuleOfPartnerAllowed());

        var module = new ModuleModel();
        module.setModuleName("Grundlage Verteilte Systeme");
        module.setSemester(1);
        module.setCreditPoints(99999);

        client.createModuleOfPartner(module);
        assertEquals(201, client.getLastStatusCode());
    }

    @Test void test_create_module_of_partner_and_get_module() throws IOException
    {

        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");

        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerAllowed() );

        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isCreateSingleModuleOfPartnerAllowed());

        var module = new ModuleModel();
        module.setModuleName("Grundlage Verteilte Systeme");
        module.setSemester(1);
        module.setCreditPoints(99999);

        client.createModuleOfPartner(module);
        assertEquals(201, client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());

        client.getSingleModule();
        assertEquals(200, client.getLastStatusCode());

        var moduleFromPartner = client.moduleData().get(0);
        assertEquals("Grundlage Verteilte Systeme", moduleFromPartner.getModuleName());

    }

    @Test void test_update_module_of_partner() throws IOException {
        client.start();
        client.initializeDatabase();

        client.start();

        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");
    
        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetAllPartnersAllowed() );

        client.getAllPartners();

        client.setPartnerCursor(0);
        client.getSinglePartner();
        
        client.createModuleOfPartner(new ModuleModel("Verteilte Systeme", 1, 2));

        client.getSingleModule();
        
        assertTrue(client.isUpdateSingleModuleOfPartnerAllowed());

        client.updateModule(new ModuleModel("Data Mining", 2, 4));
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();
        assertEquals(200, client.getLastStatusCode());
        assertEquals("Data Mining", client.moduleData().get(0).getModuleName());
    }

    @Test void test_delete_one_module_of_partner() throws IOException
    {
            
        client.start();
    
        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");
    
        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());
        assertTrue( client.isGetSinglePartnerAllowed() );
    
        client.getSinglePartner();
        assertEquals(200, client.getLastStatusCode());
    
        assertTrue(client.isCreateSingleModuleOfPartnerAllowed());
    
        var module = new ModuleModel();
        module.setModuleName("Grundlage Verteilte Systeme");
        module.setSemester(1);
        module.setCreditPoints(99999);
    
        client.createModuleOfPartner(module);
        assertEquals(201, client.getLastStatusCode());
    
        assertTrue(client.isGetSingleModuleAllowed());
    
        client.getSingleModule();
        assertEquals(200, client.getLastStatusCode());

        assertTrue(client.isDeleteSingleModuleOfPartnerAllowed());
        client.deleteModule();

        assertEquals(204, client.getLastStatusCode());
    }

    @Test void delete_one_of_five_modules_of_partner()  throws IOException
    {
        client.start();
    
        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");
    
        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());

        for( int i=0; i<5; i++ ) {
            client.start();
            client.getAllPartners();
            client.getSinglePartner();
            assertTrue(client.isCreateSingleModuleOfPartnerAllowed());

            var module = new ModuleModel();
            module.setModuleName(faker.name().name());
            module.setSemester(faker.number().numberBetween(1, 2));
            module.setCreditPoints(faker.number().randomDigitNotZero());
            
            client.createModuleOfPartner(module);
            assertEquals(201, client.getLastStatusCode());
        }

        assertTrue(client.isGetSingleModuleAllowed());
        client.getSingleModule();

        assertTrue(client.isDeleteSingleModuleOfPartnerAllowed());
        client.deleteModule();
        assertEquals(204, client.getLastStatusCode());

        assertTrue(client.isGetAllModulesPartnerAllowed());
        client.getAllModulesOfPartner();

        assertEquals(200, client.getLastStatusCode());
        assertEquals(4, client.moduleData().size());
    }

    @Test void test_read_all_5_created_modules_of_partner() throws IOException
    {
        client.start();
    
        var partner = new PartnerUniversityModel();
        partner.setUniversityName("THWS");
        partner.setCountry("Germany");
        partner.setDepartmentName("Informatik");
        partner.setDepartmentUrl("https://THWSTotallyRealWebsite.de");
        partner.setContactPerson("Biedermann");
        partner.setOutgoingStudents(40);
        partner.setIncomingStudents(56);
        partner.setSpringSemesterStart("15.03.2024");
        partner.setAutumnSemesterStart("15.09.2024");
    
        client.createPartner(partner);
        assertEquals(201, client.getLastStatusCode());

        for( int i=0; i<5; i++ ) {
            client.start();
            client.getAllPartners();
            client.getSinglePartner();
            assertTrue(client.isCreateSingleModuleOfPartnerAllowed());

            var module = new ModuleModel();
            module.setModuleName(faker.name().name());
            module.setSemester(faker.number().numberBetween(1, 2));
            module.setCreditPoints(faker.number().randomDigitNotZero());
            
            client.createModuleOfPartner(module);
            assertEquals(201, client.getLastStatusCode());
        }

        assertTrue(client.isGetAllModulesPartnerAllowed());
        client.getAllModulesOfPartner();
        assertEquals(200, client.getLastStatusCode());
        assertEquals(5, client.moduleData().size());

    }

}