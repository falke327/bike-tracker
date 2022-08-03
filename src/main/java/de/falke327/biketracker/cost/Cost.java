package de.falke327.biketracker.cost;

import de.falke327.biketracker.bike.Bike;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Cost {
    private Long id;
    private LocalDate date;
    private String description;
    private BigDecimal price;
    private Bike bike;
}
