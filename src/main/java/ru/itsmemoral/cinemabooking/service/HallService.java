package ru.itsmemoral.cinemabooking.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.itsmemoral.cinemabooking.dto.CreateHallRequest;
import ru.itsmemoral.cinemabooking.dto.HallResponse;
import ru.itsmemoral.cinemabooking.entity.Hall;
import ru.itsmemoral.cinemabooking.repository.HallRepository;

import java.util.List;

@Service
public class HallService {

    private final HallRepository hallRepository;

    public HallService(HallRepository hallRepository) {
        this.hallRepository = hallRepository;
    }

    @Transactional
    public HallResponse create(CreateHallRequest request) {
        if (hallRepository.existsByName(request.name())) {
            throw new IllegalStateException("Зал с названием '" + request.name() + "' уже существует");
        }
        Hall hall = hallRepository.save(new Hall(request.name(), request.rowsCount(), request.seatsPerRow()));
        return HallResponse.from(hall);
    }

    @Transactional(readOnly = true)
    public List<HallResponse> findAll() {
        return hallRepository.findAll().stream()
                .map(HallResponse::from)
                .toList();
    }
}