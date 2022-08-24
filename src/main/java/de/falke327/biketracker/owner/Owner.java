package de.falke327.biketracker.owner;

import de.falke327.biketracker.bike.Bike;
import lombok.*;

import javax.persistence.*;

import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Owner")
@Table(name = "owner")
public class Owner {

    @Id
    @SequenceGenerator(
            name = "owner_id_seq",
            sequenceName = "owner_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "owner_id_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "first_name",
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            nullable = false
    )
    private String lastName;

    @OneToMany(
            mappedBy = "owner",
            orphanRemoval = true, // remove bikes when owner is removed
            cascade = CascadeType.ALL, // always persist or remove bikes together with owner
            fetch = FetchType.EAGER // fetch the books in same query as owners can slow down big applications
    )
    private List<Bike> bikes = new ArrayList<>();

    public Owner(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public void addBike(Bike bike) {
        if (!this.bikes.contains(bike)) {
            this.bikes.add(bike);
            bike.setOwner(this);
        }
    }

    public void removeBike(Bike bike) {
        if (this.bikes.contains(bike)) {
            this.bikes.remove(bike);
            bike.setOwner(null);
        }
    }
}
