package eu.epptec.carStop.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class PartRideReservationPK implements Serializable {
    private ReservationEntity reservation;
    private PartRideEntity ride;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        PartRideReservationPK that = (PartRideReservationPK) o;
        return Objects.equals(reservation, that.reservation) && Objects.equals(ride, that.ride);
    }

    @Override
    public int hashCode() {
        return Objects.hash(reservation, ride);
    }
}
