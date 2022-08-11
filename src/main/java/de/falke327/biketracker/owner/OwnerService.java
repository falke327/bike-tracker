package de.falke327.biketracker.owner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public void addOwner(Owner owner) {
        // TODO: check if already exists
        ownerRepository.save(owner);
    }

    public void deleteOwnerById(Long id) {
        ownerRepository.deleteById(id);
    }
}
