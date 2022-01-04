package eu.epptec.carStop.entity;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Getter @Setter
public class UserRolesEntityPK implements Serializable {
    private RoleEntity role;
    private UserEntity user;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        UserRolesEntityPK that = (UserRolesEntityPK) o;
        return Objects.equals(role, that.role) && Objects.equals(user, that.user);
    }

    @Override
    public int hashCode() {
        return Objects.hash(role, user);
    }
}
