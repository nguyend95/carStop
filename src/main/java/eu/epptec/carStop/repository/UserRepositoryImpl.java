package eu.epptec.carStop.repository;

import eu.epptec.carStop.entity.UserEntity;

public abstract class UserRepositoryImpl implements UserRepository{


    @Override
    public UserEntity findByEmail(String email) {
        return null;
    }


}
