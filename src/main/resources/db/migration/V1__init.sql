create table halls (
                       id            bigserial primary key,
                       name          varchar(100) not null unique,
                       rows_count    int not null,
                       seats_per_row int not null
);

create table seats (
                       id       bigserial primary key,
                       hall_id  bigint not null references halls (id),
                       row_num  int not null,
                       seat_num int not null,
                       constraint uq_seat_in_hall unique (hall_id, row_num, seat_num)
);