/*
DROP TABLE IF EXISTS user;

CREATE TABLE user
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL
);

DROP TABLE IF EXISTS otp;

CREATE TABLE otp
(
    id       INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    otp VARCHAR(250) NOT NULL
);

INSERT INTO user(username, password)
VALUES ('sachin', 'sachin');

INSERT INTO user(username, password)
VALUES ('admin', 'admin');

INSERT INTO user(username, password)
VALUES ('user', 'user');*/
