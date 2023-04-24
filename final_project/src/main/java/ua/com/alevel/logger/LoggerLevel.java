package ua.com.alevel.logger;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum LoggerLevel {

    INFO("info"),
    WARN("warn"),
    ERROR("error");

    private final String level;
}
