package de.falke327.biketracker.tour;

import lombok.*;

import java.time.Duration;
import java.time.LocalDate;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Tour {
    private Long id;
    private LocalDate date;
    private Double distance;
    private String description;
    private Duration drivingTime;
    private Double maximumSpeed;
    private Double averageSpeed;
    private String additionalInfo;
}
