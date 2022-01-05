package eu.epptec.carStop.service.impl;

import eu.epptec.carStop.dto.ride.StopDto;
import eu.epptec.carStop.entity.PartRideEntity;
import eu.epptec.carStop.entity.PartRideReservationEntity;
import eu.epptec.carStop.entity.RideEntity;
import eu.epptec.carStop.repository.PartRideRepository;
import eu.epptec.carStop.service.MapSpotService;
import eu.epptec.carStop.service.PartRideService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@Service
public class PartRideServiceImpl implements PartRideService {
    private final MapSpotService mapSpotService;
    private final PartRideRepository partRideRepository;

    public PartRideServiceImpl(MapSpotService mapSpotService, PartRideRepository partRideRepository) {
        this.mapSpotService = mapSpotService;
        this.partRideRepository = partRideRepository;
    }

    @Override
    @Transactional
    public List<PartRideEntity> createPartRides(List<StopDto> stops, RideEntity rideEntity) {
        List<PartRideEntity> result = new ArrayList<>();

        for (int i = 1; i < stops.size(); i++) {
            PartRideEntity partRide = new PartRideEntity();

            partRide.setDepartTime(new Timestamp(stops.get(i).getDepartureTime().getTime()));
            mapSpotService.createOrFind(stops.get(i - 1).getCity()).addStartRide(partRide);
            mapSpotService.createOrFind(stops.get(i).getCity()).addDestinationRide(partRide);

            partRide.setPrice(stops.get(i).getPrice());
            partRide.setRideOrder(i);

            this.partRideRepository.save(partRide);
            result.add(partRide);
        }

        return result;
    }

    @Override
    public void createReservation(PartRideReservationEntity partRideReservation) {

    }
}
