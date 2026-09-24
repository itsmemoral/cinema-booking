package ru.itsmemoral.cinemabooking.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.itsmemoral.cinemabooking.dto.CreateHallRequest;
import ru.itsmemoral.cinemabooking.dto.HallResponse;
import ru.itsmemoral.cinemabooking.entity.Hall;
import ru.itsmemoral.cinemabooking.exception.HallAlreadyExistsException;
import ru.itsmemoral.cinemabooking.repository.HallRepository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@DisplayName("HallService")
class HallServiceTest {

    @Mock
    private HallRepository hallRepository;

    @InjectMocks
    private HallService hallService;

    @Test
    @DisplayName("создает зал и рассчитывает общее число мест")
    void createsHallWithCorrectTotalSeats() {
        CreateHallRequest request = new CreateHallRequest("Зеленый", 4, 8);
        when(hallRepository.existsByName("Зеленый")).thenReturn(false);
        when(hallRepository.save(any(Hall.class))).thenAnswer(invocation -> invocation.getArgument(0));

        HallResponse response = hallService.create(request);

        assertThat(response.name()).isEqualTo("Зеленый");
        assertThat(response.rowsCount()).isEqualTo(4);
        assertThat(response.seatsPerRow()).isEqualTo(8);
        assertThat(response.totalSeats()).isEqualTo(32);
    }

    @Test
    @DisplayName("бросает исключение, если зал с таким названием уже есть")
    void throwsWhenHallNameIsTaken() {
        CreateHallRequest request = new CreateHallRequest("Красный", 5, 10);
        when(hallRepository.existsByName("Красный")).thenReturn(true);

        assertThatThrownBy(() -> hallService.create(request))
                .isInstanceOf(HallAlreadyExistsException.class)
                .hasMessageContaining("Красный");
        verify(hallRepository, never()).save(any());
    }
}