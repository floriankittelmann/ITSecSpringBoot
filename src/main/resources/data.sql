DROP TABLE IF EXISTS users;
CREATE TABLE USER (
    id LONG AUTO_INCREMENT  PRIMARY KEY,
    username VARCHAR(250) NOT NULL,
    password VARCHAR(250) DEFAULT NULL,
    email VARCHAR(250) NOT NULL
);

INSERT INTO USER (username, password, email) VALUES
                                              ('user', 'password','user@example.com'),
                                              ('hans', 'wurst', 'hans@wurst.info');
