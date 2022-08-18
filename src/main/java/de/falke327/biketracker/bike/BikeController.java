package de.falke327.biketracker.bike;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "api/v1/bikes")
@AllArgsConstructor
public class BikeController {

    private final BikeService bikeService;
}
