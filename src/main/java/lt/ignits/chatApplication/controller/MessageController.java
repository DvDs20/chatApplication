package lt.ignits.chatApplication.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lt.ignits.chatApplication.dto.request.CreateNewMessageRequest;
import lt.ignits.chatApplication.dto.response.CreatedMessageResponse;
import lt.ignits.chatApplication.dto.response.MessageResponse;
import lt.ignits.chatApplication.service.MessageService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/message")
@SecurityRequirement(name = "Bearer Authentication")
@AllArgsConstructor
@Slf4j
public class MessageController {

    private final MessageService messageService;

    @Operation(summary = "Create new message")
    @PostMapping
    public ResponseEntity<CreatedMessageResponse> createMessage(@RequestBody CreateNewMessageRequest request) {
        log.info("User trying to create new message: {}", request);
        return ResponseEntity.ok(messageService.createMessage(request));
    }

    @Operation(summary = "Get all messages")
    @GetMapping
    public List<MessageResponse> getAllMessages() {
        log.info("User is trying to get all messages");
        return messageService.getAllMessages();
    }
}
