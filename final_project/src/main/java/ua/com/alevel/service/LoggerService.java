package ua.com.alevel.service;

import ua.com.alevel.logger.LoggerLevel;

public interface LoggerService {

    void print(LoggerLevel level, String message);
}
