# Cinema Booking

Сервис бронирования мест в кинотеатре. Пет-проект: разработка backend-приложения
и покрытие его автотестами (API, UI, интеграционные).

## Стек

- Java 21, Spring Boot, Spring Data JPA
- PostgreSQL 16, Flyway
- Maven, Docker Compose

## Запуск

```bash
docker compose up -d
./mvnw spring-boot:run
```

Приложение поднимается на `http://localhost:8080`.

## Статус

Проект в разработке. Сделано: схема БД, CRUD залов.
Дальше: сеансы и бронирование, обработка ошибок, автотесты, CI.