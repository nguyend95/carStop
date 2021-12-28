create table if not exists app_db.map_spot(
    ID INT PRIMARY KEY,
    city_name varchar(90)
--     coordinates GEOMETRY(POINT)
);

CREATE TABLE IF NOT EXISTS app_db.ride(
    id int primary key,
    driver_message varchar(300),
    total_seats int,
    pets boolean,
    smoke boolean,
    music boolean,
    driver_id int,
    constraint fk_user
        foreign key(driver_id)
            references app_db.user(id)
);

CREATE TABLE IF NOT EXISTS app_db.part_ride(
    id int primary key,
    price int,
    depart_time timestamp,
    ride_order int,
    map_spot_start_id int,
    map_spot_destination_id int,
    ride_id int,
    constraint fk_ride_id
        foreign key(ride_id)
            references app_db.ride(id),
    constraint fk_destination_id
        foreign key(map_spot_destination_id)
            references app_db.map_spot(id),
    constraint fk_start_id
        foreign key(map_spot_start_id)
            references app_db.map_spot(id)
);

-- create message table
CREATE TABLE IF NOT EXISTS app_db.messages (
    ID INT PRIMARY KEY,
    content varchar(500),
    received boolean,
    c_time TIMESTAMP,
    ride_id int,
    sender_id int,
    receiver_id int,
    constraint fk_sender
        foreign key(sender_id)
            references app_db.user(id),
    constraint fk_receiver
        foreign key(receiver_id)
            references app_db.user(id),
    constraint fk_ride
        foreign key(ride_id)
            references app_db.ride(id)
);

create table if not exists app_db.reservation(
    id int primary key,
    status varchar(30),
    seats int,
    driver_rating int,
    passenger_id int,
    passenger_rating int,
    foreign key(passenger_id)
        references app_db.user(id)
);

create table if not exists app_db.part_ride_reservation(
    part_ride_id int,
    reservation_id int,
    foreign key(part_ride_id)
        references app_db.part_ride(id),
    foreign key(reservation_id)
        references app_db.reservation(id),
    primary key(part_ride_id, reservation_id)
);

CREATE SEQUENCE if not exists app_db.map_spot_sequence
    start with 1
    increment by 1;

CREATE SEQUENCE if not exists app_db.part_ride_sequence
    start with 1
    increment by 1;

CREATE SEQUENCE if not exists app_db.ride_sequence
    start with 1
    increment by 1;

CREATE SEQUENCE if not exists app_db.message_sequence
    start with 1
    increment by 1;

CREATE SEQUENCE if not exists app_db.reservation_sequence
    start with 1
    increment by 1;