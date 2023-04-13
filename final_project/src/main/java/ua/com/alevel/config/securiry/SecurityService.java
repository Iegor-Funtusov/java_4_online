package ua.com.alevel.config.securiry;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
