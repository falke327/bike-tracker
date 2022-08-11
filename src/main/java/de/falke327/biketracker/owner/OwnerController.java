package de.falke327.biketracker.owner;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/owners")
@AllArgsConstructor
public class OwnerController {

    private final OwnerService ownerService;

    @GetMapping
    public List<Owner> getAllOwners() {
        return ownerService.getAllOwners();
    }

    @PostMapping
    public void addOwner(@RequestBody Owner owner) {
        ownerService.addOwner(owner);
    }

//    @PostMapping
//    public void deleteOwnerById(@RequestBody Long id) {
//        ownerService.deleteOwnerById(id);
//    }
}
