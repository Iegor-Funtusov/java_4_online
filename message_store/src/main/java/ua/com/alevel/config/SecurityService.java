package ua.com.alevel.config;

public interface SecurityService {

    boolean isAuthenticated();
    void autoLogin(String username, String password);
}
