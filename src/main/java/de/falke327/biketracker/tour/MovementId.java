package de.falke327.biketracker.tour;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Embeddable
public class MovementId implements Serializable {

    @Column(name = "bike")
    private Long bikeId;

    @Column(name = "tour")
    private Long tourId;
}
