package ru.itsmemoral.cinemabooking.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.itsmemoral.cinemabooking.entity.Hall;

public interface HallRepository extends JpaRepository<Hall, Long> {

    boolean existsByName(String name);
}