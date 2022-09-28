package de.falke327.biketracker.tour;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class TourService {

    private final TourRepository tourRepository;

    public List<Tour> getAllTours() {
        return tourRepository.findAll();
    }

    public Optional<Tour> getTourById(Long id) {
        return tourRepository.findById(id);
    }

    public Tour addTour(Tour tour) {
        return tourRepository.save(tour);
    }

    public void deleteTourById(Long id) {
        tourRepository.deleteById(id);
    }

    public Tour updateTour(Tour tour) {
        return tourRepository.save(tour);
    }
}
