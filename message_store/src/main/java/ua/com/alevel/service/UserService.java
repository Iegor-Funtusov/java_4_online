package ua.com.alevel.service;

import ua.com.alevel.persistence.entity.user.User;

public interface UserService<U extends User> extends CrudService<U> { }
