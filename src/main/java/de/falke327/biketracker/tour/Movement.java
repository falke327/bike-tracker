package de.falke327.biketracker.tour;

import de.falke327.biketracker.bike.Bike;
import lombok.*;

import javax.persistence.*;

@ToString
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Movement")
@Table(name = "movements")
public class Movement {

    @EmbeddedId
    private MovementId id;

    @ManyToOne
    @MapsId("bikeId")
    @JoinColumn(
            name = "bike",
            foreignKey = @ForeignKey(
                    name = "movements_bike_fkey"
            )
    )
    private Bike bike;

    @ManyToOne
    @MapsId("tourId")
    @JoinColumn(
            name = "tour",
            foreignKey = @ForeignKey(
                    name = "movements_tour_fkey"
            )
    )
    private Tour tour;

    public Movement(Bike bike, Tour tour) {
        this.bike = bike;
        this.tour = tour;
    }
}
