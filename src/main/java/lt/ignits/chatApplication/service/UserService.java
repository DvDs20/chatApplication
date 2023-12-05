package lt.ignits.chatApplication.service;

import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService {
    UserDetailsService userDetailsService();
    void deleteUser(Integer userId);
}
