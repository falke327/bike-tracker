package de.falke327.biketracker.bike;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BikeService {

    private final BikeRepository bikeRepository;
}
