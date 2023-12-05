package lt.ignits.chatApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ignits.chatApplication.dto.response.JwtAuthenticationResponse;
import lt.ignits.chatApplication.dto.request.SignInRequest;
import lt.ignits.chatApplication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Operation(summary = "Sign In with username and password")
    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponse> signIn(@RequestBody SignInRequest request) {
        log.info("Performing Sig In with these credentials: {}", request);
        return ResponseEntity.ok(authenticationService.signIn(request));
    }
}
