package eu.epptec.carStop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter @Setter
@IdClass(PartRideReservationPK.class)
@Table(schema = "app_db", name = "part_ride_reservation")
public class PartRideReservationEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private PartRideEntity ride;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    private ReservationEntity reservation;
}
