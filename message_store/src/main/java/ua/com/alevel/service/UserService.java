package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.entity.User;

public interface UserService<U extends User> extends CrudService<U> { }
