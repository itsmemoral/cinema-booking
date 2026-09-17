package ru.itsmemoral.cinemabooking.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "seats")
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "hall_id", nullable = false)
    private Hall hall;

    @Column(name = "row_num", nullable = false)
    private int rowNum;

    @Column(name = "seat_num", nullable = false)
    private int seatNum;

    protected Seat() {
    }

    Seat(Hall hall, int rowNum, int seatNum) {
        this.hall = hall;
        this.rowNum = rowNum;
        this.seatNum = seatNum;
    }

    public Long getId() {
        return id;
    }

    public Hall getHall() {
        return hall;
    }

    public int getRowNum() {
        return rowNum;
    }

    public int getSeatNum() {
        return seatNum;
    }
}