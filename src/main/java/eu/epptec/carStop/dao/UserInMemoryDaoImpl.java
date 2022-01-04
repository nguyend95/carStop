package eu.epptec.carStop.dao;

import eu.epptec.carStop.entity.UserEntity;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Component
@Profile("inmemory")
public class UserInMemoryDaoImpl implements UserDataService {
    private Map<Long, UserEntity> users = new HashMap<>();
    private AtomicLong counter = new AtomicLong(0);

    @Override
    public Optional<UserEntity> get(long id) {
        return Optional.ofNullable(users.get(id));
    }

    @Override
    public Collection<UserEntity> getAll() {
        return null;
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        UserEntity copy = new UserEntity();
        UserEntity returnCopy = new UserEntity();

        BeanUtils.copyProperties(userEntity, copy);
        copy.setId(counter.getAndIncrement());
        users.put(copy.getId(), userEntity);
        BeanUtils.copyProperties(copy, returnCopy);

        return returnCopy;
    }

    @Override
    public Optional<UserEntity> getByEmail(String email) {
        UserEntity copy = new UserEntity();

        for (Map.Entry<Long, UserEntity> user : users.entrySet()){
            if (email.equals(user.getValue().getEmail())){
                BeanUtils.copyProperties(user, copy);
                return Optional.of(copy);
            }
        }

        return Optional.empty();
    }

//    @Override
//    public void update(UserEntity userEntity) {
//        users.remove(this.get(userEntity.getId()).get());
//        this.save(userEntity);
//    }
//
//    @Override
//    public void delete(UserEntity userEntity) {
//        users.remove(userEntity);
//    }
}
