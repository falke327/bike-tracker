package de.falke327.biketracker.bike;

import de.falke327.biketracker.owner.Owner;

public record Bike(Long id, Owner owner, String name, String maker, String model, BikeType bikeType) {

}
