package ua.com.alevel.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaRepositories(basePackages ="ua.com.alevel.persistence.repository")
public class SpringDataConfig { }
