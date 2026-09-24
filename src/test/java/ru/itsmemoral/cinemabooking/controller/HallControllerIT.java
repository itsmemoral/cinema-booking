package ru.itsmemoral.cinemabooking.controller;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.resttestclient.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import ru.itsmemoral.cinemabooking.IntegrationTestBase;
import ru.itsmemoral.cinemabooking.dto.CreateHallRequest;
import ru.itsmemoral.cinemabooking.dto.HallResponse;

import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("POST /api/halls")
class HallControllerIT extends IntegrationTestBase {

    @Autowired
    private TestRestTemplate restTemplate;

    private String uniqueName() {
        return "Зал-" + UUID.randomUUID();
    }

    @Test
    @DisplayName("создаёт зал и возвращает 201")
    void createsHall() {
        CreateHallRequest request = new CreateHallRequest(uniqueName(), 5, 10);

        ResponseEntity<HallResponse> response =
                restTemplate.postForEntity("/api/halls", request, HallResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CREATED);
        assertThat(response.getBody()).isNotNull();
        assertThat(response.getBody().id()).isNotNull();
        assertThat(response.getBody().totalSeats()).isEqualTo(50);
    }

    @Test
    @DisplayName("возвращает 409 на дубликат названия")
    void rejectsDuplicateName() {
        CreateHallRequest request = new CreateHallRequest(uniqueName(), 3, 3);
        restTemplate.postForEntity("/api/halls", request, HallResponse.class);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/halls", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        assertThat(response.getBody()).contains("уже существует");
    }

    @Test
    @DisplayName("возвращает 400 при нулевом числе рядов")
    void rejectsZeroRows() {
        CreateHallRequest request = new CreateHallRequest(uniqueName(), 0, 10);

        ResponseEntity<String> response =
                restTemplate.postForEntity("/api/halls", request, String.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.BAD_REQUEST);
        assertThat(response.getBody()).contains("rowsCount");
    }
}