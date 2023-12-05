package lt.ignits.chatApplication.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserStatsResponse {
    private Integer userId;
    private String username;
    private Long messageCount;
    private LocalDateTime firstMessageTime;
    private LocalDateTime lastMessageTime;
    private Double avgMessageLength;
    private String lastMessageText;
}
