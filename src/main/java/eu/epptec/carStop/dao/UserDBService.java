package eu.epptec.carStop.dao;

import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.repository.UserRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.Optional;

@Component
@Profile("!inmemory")
public class UserDBService implements UserDataService {
    private final UserRepository userRepository;

    public UserDBService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<UserEntity> get(long id) {
        return userRepository.findById(id);
    }

    @Override
    public List<UserEntity> getAll() {
        return (List<UserEntity>) userRepository.findAll();
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        return this.userRepository.findByEmail(email);
    }
}
