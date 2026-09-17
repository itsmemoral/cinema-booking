package ru.itsmemoral.cinemabooking.dto;

import ru.itsmemoral.cinemabooking.entity.Hall;

public record HallResponse(Long id, String name, int rowsCount, int seatsPerRow, int totalSeats) {

    public static HallResponse from(Hall hall) {
        return new HallResponse(
                hall.getId(),
                hall.getName(),
                hall.getRowsCount(),
                hall.getSeatsPerRow(),
                hall.getRowsCount() * hall.getSeatsPerRow()
        );
    }
}