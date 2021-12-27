package eu.epptec.carStop.entity;

import javassist.expr.Instanceof;
import lombok.Getter;
import lombok.Setter;
import org.h2.engine.User;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(schema = "app_db", name = "user")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "user_generator")
    @SequenceGenerator(name = "user_generator", sequenceName = "user_sequence", schema = "app_db")
    private long id;

    @Column(name = "forename")
    private String forename;

    @Column(name = "surname")
    private String surname;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

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
