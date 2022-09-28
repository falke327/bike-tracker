package de.falke327.biketracker.tour;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/tours")
@AllArgsConstructor
public class TourController {

    private final TourService tourService;

    @GetMapping("/all")
    public List<Tour> getAllTours() {
        return tourService.getAllTours();
    }

    @GetMapping("/{id}")
    public Tour getTourById(@PathVariable("id") Long id) {
        Optional<Tour> optTour = tourService.getTourById(id);

        return optTour.orElse(null);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Tour addTour(@RequestBody Tour tour) {
        return tourService.addTour(tour);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteTourById(@PathVariable("id") Long id) {
        tourService.deleteTourById(id);
    }

    // TODO: check if we need a patch method
}
