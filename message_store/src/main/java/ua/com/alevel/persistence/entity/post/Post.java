package ua.com.alevel.persistence.entity.post;

import lombok.Getter;
import lombok.Setter;

import ua.com.alevel.persistence.entity.AbstractEntity;
import ua.com.alevel.persistence.entity.entity.Personal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table(name = "posts")
public class Post extends AbstractEntity {

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "message", nullable = false, columnDefinition = "TEXT")
    private String message;

    @Column(name = "comment", columnDefinition = "TEXT")
    private String comment;

    @ManyToOne
    private Personal personal;

    public Post() {
        super();
    }
}
