package lt.ignits.chatApplication.service;

import lt.ignits.chatApplication.dto.request.CreateNewMessageRequest;
import lt.ignits.chatApplication.dto.response.CreatedMessageResponse;
import lt.ignits.chatApplication.dto.response.MessageResponse;

import java.util.List;

public interface MessageService {
    CreatedMessageResponse createMessage(CreateNewMessageRequest request);

    List<MessageResponse> getAllMessages();
}
