package de.falke327.biketracker.integration;

import de.falke327.biketracker.bike.Bike;
import de.falke327.biketracker.bike.BikeType;
import de.falke327.biketracker.owner.Owner;
import de.falke327.biketracker.owner.OwnerRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPatch;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.datasource.init.ScriptException;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT,
        properties = {
                "server.port=8080"
        }
)
public class OwnerTest {

    Logger logger = LoggerFactory.getLogger(OwnerTest.class);

    private Owner testOwner;
    private Bike testBike;

    @Autowired
    protected OwnerRepository ownerRepository;

    @BeforeEach
    public void cleanUp() {
        ownerRepository.deleteAll();
        initDb();
    }

    @Test
    public void testOwnersExistsStatusCodeAndMimeType() throws IOException {
        // Given
        final String JSON_MIME_TYPE = "application/json";
        final HttpUriRequest REQUEST = new HttpGet("http://localhost:8080/api/v1/owners/all");

        // When
        final HttpResponse HTTP_RESPONSE = HttpClientBuilder.create().build().execute(REQUEST);

        //Then
        assertEquals(200, HTTP_RESPONSE.getStatusLine().getStatusCode());

        String responseMimeType = ContentType.getOrDefault(HTTP_RESPONSE.getEntity()).getMimeType();
        assertEquals(JSON_MIME_TYPE, responseMimeType);
    }

    @Test
    public void testOwnerAndBikeFromResponse() throws IOException {
        // Given
        final HttpUriRequest REQUEST = new HttpGet("http://localhost:8080/api/v1/owners/" + this.testOwner.getId());

        // When
        final HttpResponse HTTP_RESPONSE = HttpClientBuilder.create().build().execute(REQUEST);

        //Then
        Owner responseOwner = RetrieveUtil.retrieveResourceFromResponse(HTTP_RESPONSE, Owner.class);
        Bike responseBike = responseOwner.getBikes().get(0);

        assertEquals(this.testOwner.getFirstName(), responseOwner.getFirstName());
        assertEquals(this.testOwner.getLastName(), responseOwner.getLastName());
        assertEquals(this.testBike.getName(), responseBike.getName());
        assertEquals(this.testBike.getMaker(), responseBike.getMaker());
        assertEquals(this.testBike.getModel(), responseBike.getModel());
        assertEquals(this.testBike.getBikeType(), responseBike.getBikeType());
    }

    @Test
    public void testUserResponseInvalidId() throws IOException {
        // Given
        final long INVALID_ID = this.testOwner.getId() + 999;
        logger.debug("Called with id: " + INVALID_ID + "// Original is: " + this.testOwner.getId());
        final HttpUriRequest REQUEST = new HttpGet("http://localhost:8080/api/v1/owners/" + INVALID_ID);

        // When
        final HttpResponse HTTP_RESPONSE = HttpClientBuilder.create().build().execute(REQUEST);

        // Then
        assertEquals(200, HTTP_RESPONSE.getStatusLine().getStatusCode()); // TODO: this should fail for invalid ownerId
    }

    @Test
    public void testOwnerCreation() throws IOException {
        // Given
        final Owner NEW_TEST_OWNER = new Owner("Hans", "Hansen");
        final Bike NEW_TEST_BIKE = new Bike(null, null, "Enterprise", "Utopia Planitia", "Spaceship", BikeType.OTHER);
        NEW_TEST_OWNER.addBike(NEW_TEST_BIKE);

        HttpPost request = new HttpPost("http://localhost:8080/api/v1/owners/create");
        String jsonBody = createJsonBody(NEW_TEST_OWNER, NEW_TEST_BIKE);

        request.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        logger.debug(request.getEntity().toString());

        // When
        final HttpResponse HTTP_RESPONSE = HttpClientBuilder.create().build().execute(request);

        // Then
        assertEquals(201, HTTP_RESPONSE.getStatusLine().getStatusCode());
        logger.debug(HTTP_RESPONSE.getEntity().toString());

        Owner responseOwner = RetrieveUtil.retrieveResourceFromResponse(HTTP_RESPONSE, Owner.class);
        Owner checkOwner = ownerRepository.findById(responseOwner.getId()).get();

        assertEquals(NEW_TEST_OWNER.getFirstName(), checkOwner.getFirstName());
        assertEquals(NEW_TEST_OWNER.getLastName(), checkOwner.getLastName());
    }

    @Test
    public void testOwnerPatch() throws IOException {
        // Given
        final Owner NEW_TEST_OWNER = new Owner("Hans", "Hansen");
        final Bike NEW_TEST_BIKE = new Bike(null, null, "Enterprise", "Utopia Planitia", "Spaceship", BikeType.OTHER);
        NEW_TEST_OWNER.addBike(NEW_TEST_BIKE);

        HttpPatch request = new HttpPatch("http://localhost:8080/api/v1/owners/update/" + this.testOwner.getId());
        String jsonBody = createJsonBody(NEW_TEST_OWNER, NEW_TEST_BIKE);

        request.setEntity(new StringEntity(jsonBody, ContentType.APPLICATION_JSON));
        logger.debug(request.getEntity().toString());

        // When
        final HttpResponse HTTP_RESPONSE = HttpClientBuilder.create().build().execute(request);

        // Then
        assertEquals(200, HTTP_RESPONSE.getStatusLine().getStatusCode());
        logger.debug(HTTP_RESPONSE.getEntity().toString());

        Owner checkOwner = ownerRepository.findById(this.testOwner.getId()).get();

        assertEquals(NEW_TEST_OWNER.getFirstName(), checkOwner.getFirstName());
        assertEquals(NEW_TEST_OWNER.getLastName(), checkOwner.getLastName());
        assertNotEquals(this.testOwner.getFirstName(), checkOwner.getFirstName());
        assertNotEquals(this.testOwner.getLastName(), checkOwner.getLastName());
    }

    private String createJsonBody(Owner newTestOwner, Bike newTestBike) {
        String jsonBody = "{"
                + "\"firstName\":\"" + newTestOwner.getFirstName() + "\","
                + "\"lastName\":\"" + newTestOwner.getLastName() + "\","
                + "\"bikes\": [" + "{"
                + "\"name\":\"" + newTestBike.getName() + "\","
                + "\"maker\":\"" + newTestBike.getMaker() + "\","
                + "\"model\":\"" + newTestBike.getModel() + "\","
                + "\"bikeType\":\"" + newTestBike.getBikeType() + "\""
                + "}" + "]"
                + "}";
        logger.info(jsonBody);
        return jsonBody;
    }

    protected void initDb() throws ScriptException {
        this.testOwner = new Owner("Klaus", "Testmann");
        this.testBike = new Bike(null, null, "Test", "Foo", "Bar", BikeType.RACE);
        testOwner.addBike(this.testBike);
        ownerRepository.save(this.testOwner);
    }
}
