package de.falke327.biketracker.bike;

import de.falke327.biketracker.owner.Owner;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Bike")
@Table(name = "bike")
public class Bike {

    @Id
    private Long id;

    private Owner owner;
    private String name;
    private String maker;
    private String model;
    private BikeType bikeType;
}
