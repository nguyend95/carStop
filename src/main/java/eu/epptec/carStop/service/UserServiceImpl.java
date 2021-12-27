package eu.epptec.carStop.service;

import eu.epptec.carStop.dao.UserDataService;
import eu.epptec.carStop.dto.user.UserGetDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDataService userDao;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDataService userDao,
                           PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserGetDTO save(UserPostDTO model) {
        UserEntity user = new UserEntity();

        user.setEmail(model.getEmail());
        user.setSurname(model.getLastName());
        user.setForename(model.getFirstName());
        user.setPassword(passwordEncoder.encode(model.getPassword()));

        return this.transformPostToGet(userDao.save(user));
    }

    private UserGetDTO transformPostToGet(UserEntity save) {
        return null;
    }

    @Override
    public Optional<UserEntity> get(long id) {
        return this.userDao.get(id);
    }

    @Override
    public Collection<UserEntity> getAll() {
        return this.userDao.getAll();
    }

    public boolean checkInput(UserPostDTO model) {
        return emailExist(model.getEmail())
                || model.getPassword().equals(model.getMatchingPassword());
    }

    private boolean emailExist(String email) {
        return userDao.getByEmail(email).isPresent();
    }

    public Optional<UserEntity> loadUserByEmail(String email) {
        return this.userDao.getByEmail(email);
    }
}
