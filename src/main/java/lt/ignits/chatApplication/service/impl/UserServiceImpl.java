package lt.ignits.chatApplication.service.impl;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lt.ignits.chatApplication.repository.MessageRepository;
import lt.ignits.chatApplication.repository.UserRepository;
import lt.ignits.chatApplication.service.UserService;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    @Override
    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    @Transactional
    public void deleteUser(Integer userId) {
        messageRepository.setUserToNull(userId);
        userRepository.deleteById(userId);
    }
}
