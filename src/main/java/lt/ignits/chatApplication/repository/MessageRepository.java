package lt.ignits.chatApplication.repository;

import jakarta.transaction.Transactional;
import lt.ignits.chatApplication.entities.Message;
import lt.ignits.chatApplication.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    @Query("SELECT m FROM Message m ORDER BY m.timestamp DESC")
    List<Message> findAllByOrderByTimestampDesc();

    @Modifying
    @Transactional
    @Query("UPDATE Message m SET m.user = null WHERE m.user.id = :userId")
    void setUserToNull(Integer userId);

    @Query("SELECT COUNT(m) FROM Message m WHERE m.user = :user")
    Long countMessagesByUser(@Param("user") User user);

    @Query("SELECT m FROM Message m WHERE m.user = :user ORDER BY m.timestamp ASC LIMIT 1")
    Optional<Message> findFirstMessageByUserOrderByTimestampAsc(@Param("user") User user);


    @Query("SELECT m FROM Message m WHERE m.user = :user ORDER BY m.timestamp DESC LIMIT 1")
    Optional<Message> findFirstMessageByUserOrderByTimestampDesc(@Param("user") User user);


    @Query("SELECT AVG(LENGTH(m.content)) FROM Message m WHERE m.user = :user")
    Optional<Double> findAverageMessageLengthByUser(@Param("user") User user);

    @Query("SELECT m.content FROM Message m WHERE m.user = :user ORDER BY m.timestamp DESC LIMIT 1")
    Optional<String> findLastMessageContentByUser(@Param("user") User user);

}
