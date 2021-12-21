package eu.epptec.carStop.entity;

import javassist.expr.Instanceof;
import lombok.Getter;
import lombok.Setter;
import org.h2.engine.User;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Objects;

@Entity
@Table(schema = "app-data")
@Getter @Setter
public class UserEntity {
    @Id
    @GeneratedValue(generator = "user-sequence-generator")
    private long id;

    private String forename;

    private String surname;

    private String password;

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
