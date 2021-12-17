package main.java.eu.epptec.carStop.service;

import main.java.eu.epptec.carStop.dto.user.UserPostDTO;
import main.java.eu.epptec.carStop.entity.UserEntity;
import main.java.eu.epptec.carStop.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    private final UserRepository userRepository;

    private PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Autowired
    public void setPasswordEncoder(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserPostDTO createUser(UserPostDTO model) {
        UserEntity user = new UserEntity();

        user.setEmail(model.getEmail());
        user.setSurname(model.getLastName());
        user.setForename(model.getFirstName());
        user.setPassword(passwordEncoder.encode(model.getPassword()));

        return model;
    }


    public boolean checkInput(UserPostDTO model) {
        return !emailExist(model.getEmail())
                && model.getPassword().equals(model.getMatchingPassword());
    }

    private boolean emailExist(String email) {
//        return UserRepository.findByEmail(email) != null;
        return true;
    }
}
