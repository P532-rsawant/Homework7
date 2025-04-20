package edu.iu.habahram.DinerPancakeHouseMerge.controllers;

import edu.iu.habahram.DinerPancakeHouseMerge.model.MenuItemRecord;
import edu.iu.habahram.DinerPancakeHouseMerge.repository.MergerRepository;
import edu.iu.habahram.DinerPancakeHouseMerge.model.SignupRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/merger")
public class MergerController {
    private final MergerRepository mergerRepository;

    public MergerController(MergerRepository mergerRepository) {
        this.mergerRepository = mergerRepository;
    }

    @GetMapping
    public List<MenuItemRecord> merge() {
        return mergerRepository.getMergedMenu();
    }

    @GetMapping("/vegetarian")
    public List<MenuItemRecord> getVegetarianItems() {
        return mergerRepository.getVegetarianItems();
    }
    @GetMapping("/breakfast")
    public List<MenuItemRecord> getBreakfastItems() {
        return mergerRepository.getBreakfastItems();
    }
    @GetMapping("/lunch")
    public List<MenuItemRecord> getLunchItems() {
        return mergerRepository.getLunchItems();
    }
    @GetMapping("/supper")
    public List<MenuItemRecord> getSupperItems() {
        return mergerRepository.getSupperItems();
    }
    @PostMapping("/signup")
    public boolean signup(@RequestBody SignupRequest signupRequest) {
        return mergerRepository.signup(signupRequest.username(), signupRequest.password(), signupRequest.email());
    }
}
