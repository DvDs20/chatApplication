package lt.ignits.chatApplication.service.impl;

import lombok.AllArgsConstructor;
import lt.ignits.chatApplication.dto.response.UserStatsResponse;
import lt.ignits.chatApplication.entities.Message;
import lt.ignits.chatApplication.entities.User;
import lt.ignits.chatApplication.exception.UserNotFoundWithSpecifiedIdException;
import lt.ignits.chatApplication.repository.MessageRepository;
import lt.ignits.chatApplication.repository.UserRepository;
import lt.ignits.chatApplication.service.UserStatsService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@AllArgsConstructor
public class UserStatsServiceImpl implements UserStatsService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;

    @Override
    public UserStatsResponse getUserStats(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundWithSpecifiedIdException("User not found with id " + userId));

        Long userMessageCount = messageRepository.countMessagesByUser(user);
        LocalDateTime firstUserMessageTime = messageRepository.findFirstMessageByUserOrderByTimestampAsc(user)
                .map(Message::getTimestamp).orElse(null);
        LocalDateTime lastUserMessageTime = messageRepository.findFirstMessageByUserOrderByTimestampDesc(user)
                .map(Message::getTimestamp).orElse(null);
        Double avgUserMessageLength = messageRepository.findAverageMessageLengthByUser(user).orElse(null);
        String lastUserMessageText = messageRepository.findLastMessageContentByUser(user).orElse(null);

        return UserStatsResponse.builder()
                .userId(userId)
                .username(user.getUsername())
                .messageCount(userMessageCount)
                .firstMessageTime(firstUserMessageTime)
                .lastMessageTime(lastUserMessageTime)
                .avgMessageLength(avgUserMessageLength)
                .lastMessageText(lastUserMessageText)
                .build();
    }
}
