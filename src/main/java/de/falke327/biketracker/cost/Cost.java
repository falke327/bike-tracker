package de.falke327.biketracker.cost;

import de.falke327.biketracker.bike.Bike;

import java.math.BigDecimal;
import java.time.LocalDate;

public record Cost(Long id, LocalDate date, String description, BigDecimal price, Bike bike) {

}
