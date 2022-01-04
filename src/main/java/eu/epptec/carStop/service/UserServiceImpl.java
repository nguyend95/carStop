package eu.epptec.carStop.service;

import eu.epptec.carStop.dao.UserDataService;
import eu.epptec.carStop.dto.user.UserGetDTO;
import eu.epptec.carStop.dto.user.UserLoginDTO;
import eu.epptec.carStop.dto.user.UserPostDTO;
import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.entity.UserRoleEntity;
import eu.epptec.carStop.enums.ROLE;
import eu.epptec.carStop.repository.RoleRepository;
import eu.epptec.carStop.repository.UserRepository;
import eu.epptec.carStop.service.interfaces.UserService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserDataService userDao;
    private final RoleRepository roleRepository;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserDataService userDao,
                           RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userDao = userDao;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public UserGetDTO save(UserPostDTO model) {
        UserEntity user = new UserEntity();

        user.setEmail(model.getEmail());
        user.setSurname(model.getLastName());
        user.setForename(model.getFirstName());
        user.setPassword(passwordEncoder.encode(model.getPassword()));
        this.convertStringToRole(model.getRoles(), user);

        return this.transformPostToGet(userDao.save(user));
    }

    private void convertStringToRole(List<String> roles, UserEntity user) {
        roles.forEach(role -> {
            UserRoleEntity userRole = new UserRoleEntity();
            user.addRole(userRole);
            this.roleRepository.findByName(ROLE.findByName(role)).get().addUser(userRole);
        });
    }

    private UserGetDTO transformPostToGet(UserEntity save) {
        return null;
    }

    @Override
    public Optional<UserEntity> get(long id) {
        return this.userDao.get(id);
    }

    @Override
    public Optional<UserEntity> get(String email) {
        return this.userDao.getByEmail(email);
    }

    @Override
    public Collection<UserEntity> getAll() {
        return this.userDao.getAll();
    }

    @Override
    public String login(UserLoginDTO user) {
        return null;
    }

    @Override
    public boolean checkInput(UserPostDTO model) {
        return emailExist(model.getEmail())
                && model.getPassword().equals(model.getMatchingPassword())
                && this.checkRoles(model.getRoles());
    }

    private boolean checkRoles(List<String> roles) {
        for (String role : roles)
            if (!ROLE.checkByName(role))
                return false;

        return true;
    }

    private boolean emailExist(String email) {
        return userDao.getByEmail(email).isPresent();
    }

    public Optional<UserEntity> loadUserByEmail(String email) {
        return this.userDao.getByEmail(email);
    }


}
