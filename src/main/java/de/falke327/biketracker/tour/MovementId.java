package de.falke327.biketracker.tour;

import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class MovementId implements Serializable {

    private Long bikeId;

    private Long tourId;
}
