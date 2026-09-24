package ru.itsmemoral.cinemabooking.exception;

public class HallAlreadyExistsException extends RuntimeException {

    public HallAlreadyExistsException(String name) {
        super("Зал с названием '" + name + "' уже существует");
    }
}