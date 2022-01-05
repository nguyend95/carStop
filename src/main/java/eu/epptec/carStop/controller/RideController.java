package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.reservation.ReservationDto;
import eu.epptec.carStop.dto.reservation.ReservationGetDto;
import eu.epptec.carStop.dto.ride.FindRideDto;
import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;
import eu.epptec.carStop.entity.ReservationEntity;
import eu.epptec.carStop.service.RideService;
import javassist.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/ride")
public class RideController {
    private final RideService rideService;

    public RideController(RideService rideService) {
        this.rideService = rideService;
    }

    @GetMapping("/{rideId}")
    public ResponseEntity<GetRideDto> findRide(@PathVariable String rideId){
        return ResponseEntity.ok(this.rideService.getRide(rideId));
    }

    @PostMapping()
    public ResponseEntity<GetRideDto> saveRide(@Validated @RequestBody PostRideDto ride){
        return ResponseEntity.ok(this.rideService.createRide(ride));
    }

    @GetMapping
    public List<GetRideDto> findAllRide(){
        return this.rideService.getAllRides();
    }

    public List<GetRideDto> findRide(@Validated @RequestBody FindRideDto findInformation){
        return this.rideService.findRideByInformation(findInformation);
    }

    @PostMapping("/{id}/reservation")
    public ReservationGetDto reservationPartRide(@PathVariable long id, @Validated @RequestBody ReservationDto reservationInfo){
//        zkontrolovat - volne misto, jizda existuje, part_rides existuje

        return this.rideService.createReservation(id, reservationInfo);
    }
}
