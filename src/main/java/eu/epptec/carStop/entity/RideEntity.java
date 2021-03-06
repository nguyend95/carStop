package eu.epptec.carStop.entity;

import eu.epptec.carStop.converter.StringToLongConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(schema = "app_db", name = "ride")
@Getter @Setter
public class RideEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ride_generator")
    @SequenceGenerator(name = "ride_generator", sequenceName = "ride_sequence", schema = "app_db")
    private long id;

    @Column(name = "driver_message")
    private String message;

    @Column(name = "total_seats")
    private int totalSeats;

    @Column(name = "pets")
    private boolean pets;

    @Column(name = "smoke")
    private boolean smoke;

    @Column(name = "music")
    private boolean music;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "driver_id", referencedColumnName = "id")
    private UserEntity driverId;

    @OneToMany(mappedBy = "ride")
    private List<PartRideEntity> partRides;

    @OneToMany(mappedBy = "ride")
    private List<MessagesEntity> messages;

    public void addMessage(MessagesEntity message){
        this.messages.add(message);
        message.setRide(this);
    }

    public void addPartRide(PartRideEntity partRide){
        this.partRides.add(partRide);
        partRide.setRide(this);
    }
}
