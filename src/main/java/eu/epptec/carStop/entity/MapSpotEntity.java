package eu.epptec.carStop.entity;

import eu.epptec.carStop.converter.StringToLongConverter;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(schema = "app_db", name = "map_spot")
@Getter @Setter
public class MapSpotEntity {
    @Id
    @Convert(converter = StringToLongConverter.class)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "map_spot_generator")
    @SequenceGenerator(name = "map_spot_generator", sequenceName = "map_spot_sequence", schema = "app_db")
    private String id;

    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "startSpot")
    private List<PartRideEntity> startRides = new ArrayList<>();

    @OneToMany(mappedBy = "destinationSpot")
    private List<PartRideEntity> destinationRides = new ArrayList<>();

    public void addStartRide(PartRideEntity partRide){
        this.startRides.add(partRide);
        partRide.setStartSpot(this);
    }

    public void addDestinationRide(PartRideEntity partRide){
        this.destinationRides.add(partRide);
        partRide.setDestinationSpot(this);
    }

//    private Point point;
}
