package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.entity.Personal;

@Repository
public interface PersonalRepository extends UserRepository<Personal> { }
