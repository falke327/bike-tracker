package de.falke327.biketracker.owner;

import lombok.*;

@ToString
@Getter
@Setter
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Owner {
    private Long id;
    private String firstName;
    private String lastName;
}
