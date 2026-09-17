package ru.itsmemoral.cinemabooking;

import org.springframework.boot.SpringApplication;

public class TestCinemaBookingApplication {

    public static void main(String[] args) {
        SpringApplication.from(CinemaBookingApplication::main).with(TestcontainersConfiguration.class).run(args);
    }

}
