package eu.epptec.carStop.controller;

import eu.epptec.carStop.dto.ride.GetRideDto;
import eu.epptec.carStop.dto.ride.PostRideDto;
import eu.epptec.carStop.service.interfaces.RideService;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping()
    public ResponseEntity<GetRideDto> saveRide(@Validated @RequestBody PostRideDto ride){
        return ResponseEntity.ok(this.rideService.createRide(ride));
    }
}
