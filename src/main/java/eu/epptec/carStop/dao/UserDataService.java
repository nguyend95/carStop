package eu.epptec.carStop.dao;

import eu.epptec.carStop.entity.UserEntity;

import java.util.Map;
import java.util.Optional;

public interface UserDataService {
    Optional<UserEntity> get(long id);

//    Collection<T> getAll();

    UserEntity save(UserEntity user);

    Map.Entry<Long, UserEntity> getByEmail(String email);

//    void update(T t);

//    void delete(T t);
}