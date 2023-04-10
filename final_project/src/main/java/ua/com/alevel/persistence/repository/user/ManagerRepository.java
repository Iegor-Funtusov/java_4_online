package ua.com.alevel.persistence.repository.user;

import org.springframework.stereotype.Repository;
import ua.com.alevel.persistence.entity.user.Manager;

@Repository
public interface ManagerRepository extends UserRepository<Manager> { }
