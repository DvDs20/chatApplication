package lt.ignits.chatApplication.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lt.ignits.chatApplication.dto.request.CreateNewMessageRequest;
import lt.ignits.chatApplication.dto.response.CreatedMessageResponse;
import lt.ignits.chatApplication.dto.response.MessageResponse;
import lt.ignits.chatApplication.service.MessageService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.time.LocalDateTime;
import java.util.List;

import static org.mockito.Mockito.lenient;
import static org.mockito.Mockito.mock;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(MockitoExtension.class)
public class MessageControllerTest {

    @Mock
    private MessageService messageService;

    @InjectMocks
    private MessageController messageController;

    private final MockMvc mockMvc;

    private final ObjectMapper objectMapper;

    public MessageControllerTest() {
        this.objectMapper = new ObjectMapper();
        this.messageService = mock(MessageService.class);
        this.messageController = new MessageController(messageService);
        this.mockMvc = MockMvcBuilders.standaloneSetup(messageController).build();
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void createMessage() throws Exception {
        // Given
        CreateNewMessageRequest request = new CreateNewMessageRequest("message test");
        CreatedMessageResponse createdMessageResponse = new CreatedMessageResponse("message test", LocalDateTime.now());
        lenient().when(messageService.createMessage(request)).thenReturn(createdMessageResponse);

        // When-Then
        mockMvc.perform(post("/api/v1/message")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk());
    }

    @Test
    @WithMockUser(username = "user1", roles = "USER")
    void getAllMessages() throws Exception {
        // Given
        List<MessageResponse> messageResponses = List.of(new MessageResponse());
        lenient().when(messageService.getAllMessages()).thenReturn(messageResponses);

        // When-Then
        mockMvc.perform(get("/api/v1/message"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$").isArray());
    }
}
