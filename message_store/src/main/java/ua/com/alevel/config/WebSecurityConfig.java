package ua.com.alevel.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;

    public WebSecurityConfig(UserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/css/**", "/js/**","/registration", "/").permitAll()
                .antMatchers("/admin/**").access("hasRole('ROLE_ADMIN')")
                .antMatchers("/personal/**").access("hasRole('ROLE_PERSONAL')")
                .antMatchers("/registration", "/login").permitAll()
                .anyRequest().authenticated()

                .and().formLogin().loginPage("/login").permitAll()
                .and().logout().logoutUrl("/logout").logoutSuccessUrl("/login");
    }

    @Bean
    public AuthenticationManager customAuthenticationManager() throws Exception {
        return authenticationManager();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder builder) throws Exception {
        builder
                .userDetailsService(userDetailsService)
                .passwordEncoder(passwordEncoder());
    }
}
