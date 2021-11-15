DROP TABLE IF EXISTS users;

CREATE TABLE USER (
    id LONG AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(256) DEFAULT NULL,
    email VARCHAR(256) NOT NULL
);

INSERT INTO USER (username, password, email) Values
    ('user', 'password','user@example.com')