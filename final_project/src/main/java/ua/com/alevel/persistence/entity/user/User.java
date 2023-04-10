package ua.com.alevel.persistence.entity.user;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.BaseEntity;
import ua.com.alevel.persistence.type.RoleType;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "users")
public class User extends BaseEntity {

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role_type",nullable = false)
    private RoleType roleType;

    @Column(nullable = false)
    private Boolean enabled;

    public User() {
        super();
        this.enabled = true;
    }
}
