package de.falke327.biketracker.tour;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.SEQUENCE;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Tour")
@Table(name = "tours")
public class Tour {

    @Id
    @SequenceGenerator(
            name = "tours_id_seq",
            sequenceName = "tours_id_seq",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "tours_id_seq"
    )
    @Column(
            name = "id",
            updatable = false
    )
    private Long id;

    @Column(
            name = "tour_date",
            nullable = false
    )
    private LocalDate date;

    @Column(
            name = "distance",
            columnDefinition = "NUMERIC(10,2)",
            nullable = false
    )
    private Double distance;

    @Column(
            name = "description",
            nullable = false
    )
    private String description;

    @Column(
            name = "duration"
    )
    private Integer drivingTime;

    @Column(
            name = "v_max",
            columnDefinition = "NUMERIC(5,2)"
    )
    private Double maximumSpeed;

    @Column(
            name = "info"
    )
    private String additionalInfo;

    @OneToMany(
            cascade = {CascadeType.PERSIST, CascadeType.REMOVE},
            mappedBy = "tour"
    )
    @JsonManagedReference(value = "tour-movement")
    private List<Movement> movements = new ArrayList<>();

    public void addMovement(Movement movement) {
        if (!this.movements.contains(movement)) {
            this.movements.add(movement);
        }
    }

    public void removeMovement(Movement movement) {
        this.movements.remove(movement);
    }
}
