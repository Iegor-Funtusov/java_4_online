package ua.com.alevel.entity;

import java.io.Serializable;

public abstract class BaseEntity implements Serializable {

    private String id;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
