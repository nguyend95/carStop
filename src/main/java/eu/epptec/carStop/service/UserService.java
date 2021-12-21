package eu.epptec.carStop.service;

import eu.epptec.carStop.dto.user.UserGetDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.UserEntity;

import java.util.Optional;

public interface UserService {
    public UserGetDTO save(UserPostDTO model);
    public Optional<UserEntity> get(long id);
}
