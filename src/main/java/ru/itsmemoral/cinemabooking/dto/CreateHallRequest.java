package ru.itsmemoral.cinemabooking.dto;

import jakarta.validation.constraints.*;

public record CreateHallRequest(
        @NotBlank @Size(max = 100) String name,
        @Min(1) @Max(50) int rowsCount,
        @Min(1) @Max(50) int seatsPerRow
) {
}