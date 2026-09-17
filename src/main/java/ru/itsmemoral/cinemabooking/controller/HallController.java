package ru.itsmemoral.cinemabooking.controller;

import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import ru.itsmemoral.cinemabooking.dto.CreateHallRequest;
import ru.itsmemoral.cinemabooking.dto.HallResponse;
import ru.itsmemoral.cinemabooking.service.HallService;

import java.util.List;

@RestController
@RequestMapping("/api/halls")
public class HallController {

    private final HallService hallService;

    public HallController(HallService hallService) {
        this.hallService = hallService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public HallResponse create(@Valid @RequestBody CreateHallRequest request) {
        return hallService.create(request);
    }

    @GetMapping
    public List<HallResponse> findAll() {
        return hallService.findAll();
    }
}