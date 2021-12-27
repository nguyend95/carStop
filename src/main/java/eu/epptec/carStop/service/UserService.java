package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.user.UserGetDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

public interface UserService {
    UserGetDTO save(UserPostDTO model);
    Optional<UserEntity> get(long id);
    Collection<UserEntity> getAll();
}
