package de.falke327.biketracker.owner;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@Service
public class OwnerService {

    private final OwnerRepository ownerRepository;

    public List<Owner> getAllOwners() {
        return ownerRepository.findAll();
    }

    public Optional<Owner> getOwnerById(Long id) {
        return ownerRepository.findById(id);
    }

    public Owner addOwner(Owner owner) {
        // TODO: check if already exists
        return ownerRepository.save(owner);
    }

    public void deleteOwnerById(Long id) {
        // TODO: handle owner not exist
        ownerRepository.deleteById(id);
    }

    public Owner saveOwner(Owner owner) {
        // TODO: check if already exists
        return ownerRepository.save(owner);
    }
}
