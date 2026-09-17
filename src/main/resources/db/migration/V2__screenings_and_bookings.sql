create table movies (
                        id               bigserial primary key,
                        title            varchar(200) not null,
                        duration_minutes int not null
);

create table screenings (
                            id        bigserial primary key,
                            movie_id  bigint not null references movies (id),
                            hall_id   bigint not null references halls (id),
                            starts_at timestamptz not null,
                            price     numeric(10, 2) not null
);

create table bookings (
                          id             bigserial primary key,
                          screening_id   bigint not null references screenings (id),
                          seat_id        bigint not null references seats (id),
                          customer_email varchar(255) not null,
                          status         varchar(20) not null,
                          created_at     timestamptz not null default now()
);

create unique index uq_active_booking_seat
    on bookings (screening_id, seat_id)
    where status = 'ACTIVE';