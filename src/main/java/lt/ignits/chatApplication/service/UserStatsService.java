package lt.ignits.chatApplication.service;

import lt.ignits.chatApplication.dto.response.UserStatsResponse;

public interface UserStatsService {

    UserStatsResponse getUserStats(Integer userId);
}
