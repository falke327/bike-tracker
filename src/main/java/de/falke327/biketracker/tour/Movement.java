package de.falke327.biketracker.tour;

import de.falke327.biketracker.bike.Bike;
import lombok.*;

import javax.persistence.EmbeddedId;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Movement {

    @EmbeddedId
    private MovementId id;
    private Bike bike;
    private Tour tour;

    public Movement(Bike bike, Tour tour) {
        this.bike = bike;
        this.tour = tour;
    }
}
