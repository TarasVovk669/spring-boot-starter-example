--
CREATE TABLE Author (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100) NOT NULL,
                        age INT
);

CREATE TABLE Article (
                         id BIGINT PRIMARY KEY AUTO_INCREMENT,
                         name VARCHAR(255) NOT NULL,
                         date_publication DATE,
                         author_id BIGINT,
                         FOREIGN KEY (author_id) REFERENCES Author(id)
);