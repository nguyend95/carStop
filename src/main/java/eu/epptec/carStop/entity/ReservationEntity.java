package eu.epptec.carStop.entity;

import eu.epptec.carStop.converter.ReservationStateConverter;
import eu.epptec.carStop.converter.StringToLongConverter;
import eu.epptec.carStop.enums.ReservationStates;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "app_db", name = "part_ride")
@Getter @Setter
public class ReservationEntity {
    @Id
    @Convert(converter = StringToLongConverter.class)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reservation_generator")
    @SequenceGenerator(name = "reservation_generator", sequenceName = "reservation_sequence", schema = "app_db")
    private String id;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private ReservationStates states;

    @Column(name = "seats")
    private int seats;

    @Column(name = "driver_rating")
    private int driverRating;

    @Column(name = "passenger_rating")
    private int passengerRating;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "passenger_id", referencedColumnName = "id")
    private UserEntity passenger;

    @OneToMany(mappedBy = "reservation")
    private List<PartRideReservationEntity> rides;

    public void addRide(PartRideReservationEntity ride){
        this.rides.add(ride);
        ride.setReservation(this);
    }
}
