CREATE TABLE users (
    id LONG AUTO_INCREMENT  PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) DEFAULT NULL
);

INSERT INTO users (username, password) VALUES
                                              ('user', 'password'),
                                              ('hans', 'wurst');
