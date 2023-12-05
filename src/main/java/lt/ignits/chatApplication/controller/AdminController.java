package lt.ignits.chatApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ignits.chatApplication.dto.request.CreateNewUserRequest;
import lt.ignits.chatApplication.dto.response.JwtAuthenticationResponse;
import lt.ignits.chatApplication.dto.response.UserStatsResponse;
import lt.ignits.chatApplication.service.AuthenticationService;
import lt.ignits.chatApplication.service.UserService;
import lt.ignits.chatApplication.service.UserStatsService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("api/v1/admin")
@SecurityRequirement(name = "Bearer Authentication")
@Slf4j
public class AdminController {

    private final AuthenticationService authenticationService;
    private final UserService userService;
    private final UserStatsService userStatsService;

    @Operation(summary = "Create new user")
    @PostMapping("/create-new-user")
    public ResponseEntity<JwtAuthenticationResponse> createNewUser(@RequestBody CreateNewUserRequest request) {
        log.info("Admin trying to create user: {}", request);
        return ResponseEntity.ok(authenticationService.createNewUser(request));
    }

    @Operation(summary = "Delete user by ID")
    @DeleteMapping("/users/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable Integer userId) {
        log.info("Admin trying to delete user with id: {}", userId);
        userService.deleteUser(userId);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Get user statistics by ID")
    @GetMapping("/user-stats/{userId}")
    public ResponseEntity<UserStatsResponse> getUserStats(@PathVariable Integer userId) {
        log.info("Admin trying to get user statistics with user id: {}", userId);
        UserStatsResponse userStatsResponse = userStatsService.getUserStats(userId);
        return ResponseEntity.ok(userStatsResponse);
    }

}
