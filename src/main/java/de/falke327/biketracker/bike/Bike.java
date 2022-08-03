package de.falke327.biketracker.bike;

import de.falke327.biketracker.owner.Owner;
import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Bike {
    private Long id;
    private Owner owner;
    private String name;
    private String maker;
    private String model;
    private BikeType bikeType;
}
