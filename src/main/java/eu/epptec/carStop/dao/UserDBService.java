package eu.epptec.carStop.dao;

import eu.epptec.carStop.entity.UserEntity;
import eu.epptec.carStop.repository.UserRepository;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;

@Component
@Profile("db")
public class UserDBService implements UserDataService {
//    private final EntityManager em;
    private final UserRepository userRepository;

    public UserDBService(
//            EntityManager em,
            UserRepository userRepository) {
        this.userRepository = userRepository;
//        this.em = em;
    }

    @Override
    public Optional<UserEntity> get(long id) {
        return userRepository.findById(id);
    }

//    @Override
//    public List<UserEntity> getAll() {
//        return em.createQuery("select e from UserEntity e")
//                .getResultList();
//    }

    @Override
    public UserEntity save(UserEntity userEntity) {
//        this.executeInsideTransaction(em -> em.persist(userEntity));
        return userRepository.save(userEntity);
    }

    @Override
    public Map.Entry<Long, UserEntity> getByEmail(String email) {
        return null;
    }

//    @Override
//    public void update(UserEntity userEntity) {
//        this.executeInsideTransaction(em -> em.merge(userEntity));
//    }
//
//    @Override
//    public void delete(UserEntity userEntity) {
//        this.executeInsideTransaction(em -> em.remove(userEntity));
//    }
//
//    private void executeInsideTransaction(Consumer<EntityManager> action) {
//        EntityTransaction tx = em.getTransaction();
//        try {
//            tx.begin();
//            action.accept(em);
//            tx.commit();
//        }
//        catch (RuntimeException e) {
//            tx.rollback();
//            throw e;
//        }
//    }
}
