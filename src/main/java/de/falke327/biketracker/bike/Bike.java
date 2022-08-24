package de.falke327.biketracker.bike;

import de.falke327.biketracker.owner.Owner;
import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.SEQUENCE;

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
    @SequenceGenerator(
            name = "bike_id_seq",
            sequenceName = "bike_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "bike_id_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @ManyToOne
    @JoinColumn(
            name = "bike_owner",
            nullable = false,
            referencedColumnName = "id",
            foreignKey = @ForeignKey(
                    name = "bike_bike_owner_fkey"
            )
    )
    private Owner owner;

    @Column(
            name = "name"
    )
    private String name;

    @Column(
            name = "vendor"
    )
    private String maker;

    @Column(
            name = "model"
    )
    private String model;

    @Column(
            name = "type"
    )
    private BikeType bikeType;
}
