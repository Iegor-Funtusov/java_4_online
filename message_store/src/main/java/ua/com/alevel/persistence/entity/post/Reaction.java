package ua.com.alevel.persistence.entity.post;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.user.Personal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "reactions")
public class Reaction extends AbstractEntity {

    @ManyToOne
    private Personal personal;

    @ManyToOne
    private Post post;

    @Column(name = "like_post", nullable = false, columnDefinition = "BIT", length = 1)
    private Boolean like;

    public Reaction() {
        super();
        this.like = false;
    }
}
