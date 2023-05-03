package ua.com.alevel.persistence.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;

@Getter
@Setter
@MappedSuperclass
public abstract class AbstractEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "CREATE_TIME", nullable = false, updatable = false)
    private Date created;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "UPDATE_TIME", nullable = false)
    private Date updated;

    public AbstractEntity() {
        this.created = new Date();
        this.updated = new Date();
    }

    @PreUpdate
    public void onPreUpdate() {
        this.updated = new Date();
    }
}
