package de.falke327.biketracker.bike;

import java.util.Random;

public enum BikeType {
    CHILDREN,
    CITY,
    EBIKE,
    MTB,
    RACE,
    TREKKING,
    OTHER;

    /**
     * This chooses a random bikeType. Should be used primary for testing.
     *
     * @return a random BikeType
     */
    public static BikeType getRandomBikeType() {
        Random random = new Random();
        return values()[random.nextInt(values().length)];
    }
}
