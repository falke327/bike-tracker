package de.falke327.biketracker.integration;

import de.falke327.biketracker.bike.Bike;
import de.falke327.biketracker.bike.BikeType;
import de.falke327.biketracker.owner.Owner;
import de.falke327.biketracker.owner.OwnerRepository;
import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.jdbc.datasource.init.ScriptException;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(SpringExtension.class)
public class OwnerTest {

    @Autowired
    protected OwnerRepository ownerRepository;

    @BeforeEach
    public void cleanUp() {
        ownerRepository.deleteAll();
        initDb();
    }

    @Test
    public void testUsersExistsStatusCode() throws IOException {
        // Given
        HttpUriRequest request = new HttpGet("http://localhost:8080/api/v1/owners/all");

        // When
        HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request); // FIXME this one fails org.apache.http.conn.HttpHostConnectException: Connect to localhost:8080 [localhost/127.0.0.1, localhost/0:0:0:0:0:0:0:1] failed: Connection refused: connect

        //Then
        assertEquals(HttpStatus.OK, httpResponse.getStatusLine().getStatusCode());

    }

    protected void initDb() throws ScriptException {
        Owner testOwner = new Owner("Klaus", "Testmann");
        Bike testBike = new Bike(null, null, "Test", "Foo", "Bar", BikeType.RACE);
        testOwner.addBike(testBike);
        ownerRepository.save(testOwner);
    }
}
