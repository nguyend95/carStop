package eu.epptec.carStop.dao;

import eu.epptec.carStop.entity.UserEntity;

import java.util.Collection;
import java.util.Optional;

public interface UserDataService {
    Optional<UserEntity> get(long id);

    Collection<UserEntity> getAll();

    UserEntity save(UserEntity user);

    Optional<UserEntity> getByEmail(String email);

//    void update(T t);

//    void delete(T t);
}