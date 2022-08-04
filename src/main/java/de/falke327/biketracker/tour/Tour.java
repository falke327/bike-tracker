package de.falke327.biketracker.tour;

import java.time.Duration;
import java.time.LocalDate;

public record Tour(Long id, LocalDate date, Double distance, String description, Duration drivingTime,
                   Double maximumSpeed, Double averageSpeed, String additionalInfo) {

}
