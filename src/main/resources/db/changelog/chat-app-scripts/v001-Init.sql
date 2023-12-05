--liquibase formatted sql

-- changeset yourname:1
CREATE TABLE IF NOT EXISTS _user (
  id INT AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255) UNIQUE,
  password VARCHAR(255),
  role VARCHAR(50)
);

-- changeset yourname:2
CREATE TABLE IF NOT EXISTS message (
  id BIGINT AUTO_INCREMENT PRIMARY KEY,
  user_id INT,
  content VARCHAR(1000),
  timestamp TIMESTAMP
);

CREATE SEQUENCE USER_SEQ
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

CREATE SEQUENCE MESSAGE_SEQ
    START WITH 4
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;


-- changeset yourname:3
INSERT INTO _user (id, username, password, role) VALUES
  (1, 'user1', '$2a$10$zqUKsJXBuroHmAmO8XpxGu7o37geB2dvbZrg2tweOvhXTLapnVaPK', 'USER'),
  (2, 'user2', '$2a$10$zqUKsJXBuroHmAmO8XpxGu7o37geB2dvbZrg2tweOvhXTLapnVaPK', 'USER'),
  (3, 'admin', '$2a$10$zqUKsJXBuroHmAmO8XpxGu7o37geB2dvbZrg2tweOvhXTLapnVaPK', 'ADMIN');

-- changeset yourname:4
INSERT INTO message (id, user_id, content, timestamp) VALUES
  (1, 1, 'Hello from user1!', '2023-01-01T10:00:00'),
  (2, 2, 'Hello from user2!', '2023-01-01T10:05:00'),
  (3, 1, 'Reply to user1!', '2023-01-01T10:10:00');