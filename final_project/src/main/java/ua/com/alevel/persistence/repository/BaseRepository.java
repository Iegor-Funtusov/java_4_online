package ua.com.alevel.persistence.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.com.alevel.persistence.entity.BaseEntity;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public interface BaseRepository<E extends BaseEntity> extends JpaRepository<E, Long> { }
