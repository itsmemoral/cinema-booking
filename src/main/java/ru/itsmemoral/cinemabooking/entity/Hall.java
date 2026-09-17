package ru.itsmemoral.cinemabooking.entity;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "halls")
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(name = "rows_count", nullable = false)
    private int rowsCount;

    @Column(name = "seats_per_row", nullable = false)
    private int seatsPerRow;

    @OneToMany(mappedBy = "hall", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Seat> seats = new ArrayList<>();

    protected Hall() {
    }

    public Hall(String name, int rowsCount, int seatsPerRow) {
        this.name = name;
        this.rowsCount = rowsCount;
        this.seatsPerRow = seatsPerRow;
        for (int row = 1; row <= rowsCount; row++) {
            for (int seat = 1; seat <= seatsPerRow; seat++) {
                this.seats.add(new Seat(this, row, seat));
            }
        }
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public int getSeatsPerRow() {
        return seatsPerRow;
    }

    public List<Seat> getSeats() {
        return seats;
    }
}