package ua.com.alevel.service;

import org.springframework.data.domain.Page;
import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.web.data.PersistenceRequestData;

public interface CrudService<AE extends AbstractEntity> {

    void create(AE ae);
    void update(AE ae);
    void delete(Long id);
    AE findById(Long id);
    Page<AE> findAll(PersistenceRequestData persistenceRequestData);
}
