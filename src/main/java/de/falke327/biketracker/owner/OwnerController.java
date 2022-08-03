package de.falke327.biketracker.owner;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping(path = "api/v1/owners")
public class OwnerController {

    @GetMapping
    public List<Owner> getAllOwners() {
        // TODO: implement me
        return Arrays.asList(
                new Owner(1L, "John", "Doe"),
                new Owner(2L, "Anna", "Doe")
        );
    }
}
