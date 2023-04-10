package ua.com.alevel.persistence.entity.user;

import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("MANAGER")
public class Manager extends User {

    public Manager() {
        super();
        setRoleType(RoleType.ROLE_MANAGER);
    }
}
