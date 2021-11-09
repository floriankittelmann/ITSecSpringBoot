DROP TABLE IF EXISTS users;

CREATE TABLE USER (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(256)
);

INSERT INTO USER (username, password) Values
    ('user', 'password')