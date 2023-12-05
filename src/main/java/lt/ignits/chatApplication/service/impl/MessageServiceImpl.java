package lt.ignits.chatApplication.service.impl;

import lombok.AllArgsConstructor;
import lt.ignits.chatApplication.dto.request.CreateNewMessageRequest;
import lt.ignits.chatApplication.dto.response.CreatedMessageResponse;
import lt.ignits.chatApplication.dto.response.MessageResponse;
import lt.ignits.chatApplication.entities.Message;
import lt.ignits.chatApplication.entities.User;
import lt.ignits.chatApplication.repository.MessageRepository;
import lt.ignits.chatApplication.service.MessageService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository messageRepository;

    private static final String ANONYMOUS_USERNAME = "Anonymous";

    @Override
    public CreatedMessageResponse createMessage(CreateNewMessageRequest request) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        messageRepository.save(
                Message.builder()
                        .content(request.getContent())
                        .user(user)
                        .timestamp(LocalDateTime.now())
                        .build()
        );

        return CreatedMessageResponse.builder()
                .content(request.getContent())
                .timestamp(LocalDateTime.now())
                .build();
    }

    @Override
    public List<MessageResponse> getAllMessages() {
        return mapMessagesToResponses(
                messageRepository.findAllByOrderByTimestampDesc()
        );
    }

    private List<MessageResponse> mapMessagesToResponses(List<Message> messages) {
        return messages.stream()
                .map(this::mapMessageToResponse)
                .collect(Collectors.toList());
    }

    private MessageResponse mapMessageToResponse(Message message) {
        return MessageResponse.builder()
                .username(message.getUser() == null ? ANONYMOUS_USERNAME : message.getUser().getUsername())
                .timestamp(message.getTimestamp())
                .content(message.getContent())
                .build();
    }
}
