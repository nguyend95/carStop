package eu.epptec.carStop.entity;

import eu.epptec.carStop.enums.ROLE;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "roles", schema = "app_db")
@Getter @Setter
public class RoleEntity {
    @Id
    @GeneratedValue(generator = "role_sequence", strategy = GenerationType.SEQUENCE)
    @SequenceGenerator(name = "role_sequence", schema = "app_db", sequenceName = "role_sequence")
    private int id;

    @Enumerated(EnumType.STRING)
    private ROLE name;

    @OneToMany(mappedBy = "role")
    private List<UserRoleEntity> users;

    public void addUser(UserRoleEntity user){
        this.users.add(user);
        user.setRole(this);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RoleEntity that = (RoleEntity) o;
        return id == that.id && name == that.name;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name);
    }
}
