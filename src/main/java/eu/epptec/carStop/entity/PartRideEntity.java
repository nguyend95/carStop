package eu.epptec.carStop.entity;

import eu.epptec.carStop.converter.StringToLongConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.List;

@Entity
@Table(schema = "app_db", name = "part_ride")
@Getter @Setter
public class PartRideEntity {
    @Id
    @Convert(converter = StringToLongConverter.class)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "part_ride_generator")
    @SequenceGenerator(name = "part_ride_generator", sequenceName = "part_ride_sequence", schema = "app_db")
    private String id;

    @Column(name = "price")
    private long price;

    @Column(name = "depart_time")
    private Timestamp departTime;

    @Column(name = "ride_order")
    private int rideOrder;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_spot_start_id")
    private MapSpotEntity startSpot;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "map_spot_destination_id")
    private MapSpotEntity destinationSpot;

    @OneToMany(mappedBy = "ride")
    private List<PartRideReservationEntity> reservations;

    public void addReservations(PartRideReservationEntity reservation){
        this.reservations.add(reservation);
        reservation.setRide(this);
    }
}
