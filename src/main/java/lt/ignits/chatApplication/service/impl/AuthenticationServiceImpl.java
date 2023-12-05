package lt.ignits.chatApplication.service.impl;

import lombok.RequiredArgsConstructor;
import lt.ignits.chatApplication.dto.response.JwtAuthenticationResponse;
import lt.ignits.chatApplication.dto.request.SignInRequest;
import lt.ignits.chatApplication.dto.request.CreateNewUserRequest;
import lt.ignits.chatApplication.entities.Role;
import lt.ignits.chatApplication.entities.User;
import lt.ignits.chatApplication.exception.UserAlreadyExistsException;
import lt.ignits.chatApplication.repository.UserRepository;
import lt.ignits.chatApplication.service.AuthenticationService;
import lt.ignits.chatApplication.service.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    @Override
    public JwtAuthenticationResponse createNewUser(CreateNewUserRequest request) {
        if (userRepository.existsByUsername(request.getUsername())) {
            throw new UserAlreadyExistsException("User already exists with this username: " + request.getUsername());
        }
        var user = User.builder()
                .username(request.getUsername()).password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER).build();
        userRepository.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }

    @Override
    public JwtAuthenticationResponse signIn(SignInRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));
        var user = userRepository.findByUsername(request.getUsername())
                .orElseThrow(() -> new IllegalArgumentException("Invalid username or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponse.builder().token(jwt).build();
    }
}
