package eu.epptec.carStop.entity;

import javassist.expr.Instanceof;
import lombok.Getter;
import lombok.Setter;
import org.h2.engine.User;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(schema = "app_db", name = "user")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", schema = "app_db", initialValue = 11)
    private long id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @OneToMany(mappedBy = "driverId")
    private List<RideEntity> rides;

    @OneToMany(mappedBy = "receiver")
    private List<MessagesEntity> receivedMessages;

    @OneToMany(mappedBy = "sender")
    private List<MessagesEntity> sentMessages;

    @OneToMany(mappedBy = "passenger")
    private List<ReservationEntity> reservations;

    public void addReservation(ReservationEntity reservation){
        this.reservations.add(reservation);
        reservation.setPassenger(this);
    }

    public void addReceivedMessage(MessagesEntity message){
        this.receivedMessages.add(message);
        message.setReceiver(this);
    }

    public void addSentMessage(MessagesEntity message){
        this.sentMessages.add(message);
        message.setSender(this);
    }

    public void addRide(RideEntity ride){
        this.rides.add(ride);
        ride.setDriverId(this);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof UserEntity)){
            return false;
        }

        return this.forename.equals(((UserEntity) o).getForename())
                && this.surname.equals(((UserEntity) o).getSurname())
                && this.email.equals(((UserEntity) o).getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(forename, surname, email);
    }
}
