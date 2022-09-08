package de.falke327.biketracker.owner;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/v1/owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping("/all")
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @GetMapping("/{id}")
    public Owner getOwnerById(@PathVariable("id") Long id) {
        Optional<Owner> optOwner = ownerService.getOwnerById(id);
        return optOwner.orElse(null);
    }

    @PostMapping(path = "/create", consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Owner addOwner(@RequestBody Owner owner) {
        return ownerService.addOwner(owner);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOwnerById(@PathVariable("id") Long id) {
        ownerService.deleteOwnerById(id);
    }

    @PatchMapping(path = "/update/{id}", consumes = "application/json")
    public Owner patchOwner(@PathVariable("id") Long id, @RequestBody Owner patch) {
        Owner owner = ownerService.getOwnerById(id).orElse(new Owner());

        if (patch.getFirstName() != null) {
            owner.setFirstName(patch.getFirstName());
        }
        if (patch.getLastName() != null) {
            owner.setLastName(patch.getLastName());
        }
        if (!patch.getBikes().isEmpty()) {
            patch.getBikes().forEach(owner::addBike);
        }
        // TODO: how do we remove bikes?

        return ownerService.saveOwner(owner);
    }
}
