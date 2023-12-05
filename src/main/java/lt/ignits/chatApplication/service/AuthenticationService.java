package lt.ignits.chatApplication.service;

import lt.ignits.chatApplication.dto.response.JwtAuthenticationResponse;
import lt.ignits.chatApplication.dto.request.SignInRequest;
import lt.ignits.chatApplication.dto.request.CreateNewUserRequest;

public interface AuthenticationService {
    JwtAuthenticationResponse createNewUser(CreateNewUserRequest request);

    JwtAuthenticationResponse signIn(SignInRequest request);
}
