CREATE TABLE IF NOT EXISTS managers (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    post_code CHAR(8),
    prefecture CHAR(4),
    city VARCHAR(255),
    street VARCHAR(255),
    other VARCHAR(255),
    phone_number VARCHAR(255),
    birth_day DATE,
    gender CHAR(1),
    good_language VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS tasks (
    id BIGINT NOT NULL PRIMARY KEY AUTO_INCREMENT,
    title VARCHAR(256) NOT NULL,
    content VARCHAR(256) NOT NULL,
    period VARCHAR(256) NOT NULL,
    importance VARCHAR(256) NOT NULL,
    manager_id BIGINT NOT NULL,
    constraint tasks_fk foreign key(manager_id) references managers(id)
);

