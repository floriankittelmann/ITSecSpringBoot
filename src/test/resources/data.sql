DROP TABLE IF EXISTS users;

CREATE TABLE users (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(256)
);

INSERT INTO users (username, password) Values
    ('user', 'password')