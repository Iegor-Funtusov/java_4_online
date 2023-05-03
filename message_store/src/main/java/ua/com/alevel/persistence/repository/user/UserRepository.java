package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.entity.User;
import ua.com.alevel.persistence.repository.AbstractRepository;

import java.util.Optional;

@Repository
public interface UserRepository<U extends User> extends AbstractRepository<U> {

    Optional<User> findByEmail(String email);
    boolean existsByEmail(String email);
}
