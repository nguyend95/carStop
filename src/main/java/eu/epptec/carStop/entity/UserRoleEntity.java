package eu.epptec.carStop.entity;


import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@IdClass(UserRolesEntityPK.class)
@Getter @Setter
public class UserRoleEntity {
    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    private RoleEntity role;

    @Id
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private UserEntity user;
}
