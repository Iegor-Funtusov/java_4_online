package ua.com.alevel.entity;

import java.io.Serializable;
import java.time.OffsetDateTime;

public abstract class BaseEntity implements Serializable {

    private String id;
    private OffsetDateTime created;
    private OffsetDateTime updated;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
