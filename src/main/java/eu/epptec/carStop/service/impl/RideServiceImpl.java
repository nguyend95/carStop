package eu.epptec.carStop.service.impl;

import eu.epptec.carStop.dto.reservation.ReservationDto;
import eu.epptec.carStop.dto.reservation.ReservationGetDto;
import eu.epptec.carStop.dto.ride.FindRideDto;
import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;
import eu.epptec.carStop.entity.PartRideReservationEntity;
import eu.epptec.carStop.entity.ReservationEntity;
import eu.epptec.carStop.entity.RideEntity;
import eu.epptec.carStop.repository.ReservationRepository;
import eu.epptec.carStop.repository.RideRepository;
import eu.epptec.carStop.service.PartRideService;
import eu.epptec.carStop.service.ReservationService;
import eu.epptec.carStop.service.RideService;
import eu.epptec.carStop.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
public class RideServiceImpl implements RideService {
    private final PartRideService partRideService;
    private final UserService userService;
    private final RideRepository rideRepository;
    private final ReservationRepository reservationRepository;
    private final ReservationService reservationService;

    public RideServiceImpl(PartRideService partRideService, UserService userService,
                           RideRepository rideRepository, ReservationRepository reservationRepository,
                           ReservationService reservationService) {
        this.partRideService = partRideService;
        this.userService = userService;
        this.rideRepository = rideRepository;
        this.reservationRepository = reservationRepository;
        this.reservationService = reservationService;
    }

    @Override
    @Transactional
    public GetRideDto createRide(PostRideDto ride) {
        RideEntity rideEntity = this.newRideFromInput(ride);
        this.rideRepository.save(rideEntity);
        return null;
    }

    private RideEntity newRideFromInput(PostRideDto ride) {
        RideEntity rideEntity = new RideEntity();

        rideEntity.setDriverId(userService.get(ride.getDriverId()).get());
        rideEntity.setMessage(ride.getNotes());
        rideEntity.setTotalSeats(ride.getTotalSeats());
        rideEntity.setMusic(ride.isMusic());
        rideEntity.setPets(ride.isPets());
        rideEntity.setSmoke(ride.isSmoke());
        rideEntity.setPartRides(this.partRideService.createPartRides(ride.getStops(), rideEntity));

        return rideEntity;
    }

    @Override
    public GetRideDto getRide(String rideId) {
        return null;
    }

    @Override
    public List<GetRideDto> getAllRides() {
        List<RideEntity> rides = this.rideRepository.findAll();
        List<GetRideDto> result = new ArrayList<>();

        for (RideEntity ride : rides){
            GetRideDto rideDto = this.rideEntityToRideDto(ride);

        }

        return result;
    }

    @Override
    public List<GetRideDto> findRideByInformation(FindRideDto findInformation) {
        return null;
    }

    @Override
    public ReservationGetDto createReservation(long id, ReservationDto reservationInfo) {
        UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication()
                .getPrincipal();
        String username = userDetails.getUsername();
        ReservationEntity reservation = this.createNewReservation(username, reservationInfo);

        return this.reservationService.getReservation(reservation.getId());
    }

    private ReservationEntity createNewReservation(String username, ReservationDto reservationInfo) {
        ReservationEntity reservation = new ReservationEntity();
        PartRideReservationEntity partRideReservation = new PartRideReservationEntity();

        reservation.setPassenger(this.userService.get(username).get());
        reservation.addRide(partRideReservation);
        reservation.setSeats(reservationInfo.getSeats());
        this.partRideService.createReservation(partRideReservation);

        return this.reservationRepository.save(reservation);
    }

    @Override
    public boolean rideExists(long id) {
        return this.rideRepository.existsById(id);
    }

    private GetRideDto rideEntityToRideDto(RideEntity ride) {
        GetRideDto rideDto = new GetRideDto();

        rideDto.setMusic(ride.isMusic());
        rideDto.setPets(ride.isPets());
        rideDto.setSmoke(ride.isSmoke());


        return null;
    }


}
