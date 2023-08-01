CREATE TABLE IF NOT EXISTS mst_roles (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    title       VARCHAR(255) UNIQUE
);

CREATE TABLE IF NOT EXISTS mst_users (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name    VARCHAR(255) NOT NULL,
    user_name    VARCHAR(255) NOT NULL UNIQUE,
    email        VARCHAR(255) NOT NULL UNIQUE,
    phone_number VARCHAR(15) UNIQUE,
    password     VARCHAR(255) NOT NULL,
    role_id      BIGINT,
    FOREIGN KEY (role_id) REFERENCES mst_roles(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mst_teacher (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id  BIGINT,
    FOREIGN KEY (user_id) REFERENCES mst_users(id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS mst_courses (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description VARCHAR(255),
    title       VARCHAR(255)
);

CREATE TABLE IF NOT EXISTS mst_students (
    id           BIGINT AUTO_INCREMENT PRIMARY KEY,
    english_full_name    VARCHAR(255) ,
    arabic_full_name    VARCHAR(255) ,
    email        VARCHAR(255) ,
    telephone_number VARCHAR(15) ,
    address VARCHAR(15)
);

CREATE TABLE IF NOT EXISTS trn_students_courses (
    id       BIGINT AUTO_INCREMENT PRIMARY KEY,
    FOREIGN KEY (student_id) REFERENCES mst_students(id) ,
    FOREIGN KEY (course_id) REFERENCES mst_courses(id)
);

-- Insert Into mst_roles --
INSERT INTO mst_roles (title, description)
VALUES ('teacher', 'teacher role');

-- Insert Into mst_users --
INSERT INTO mst_users (full_name, user_name, email, password, role_id)
VALUES ('user', 'info@gmail.com.jo', 'info@gmail.com.jo', '$2a$10$O9jWFnnr8i/YfMQ/N8uH9ufCPlIk37MdOF0YIOj.vVoZJlgTxXRam', 1);

-- Insert Into mst_teacher --
INSERT INTO mst_teacher (user_id) VALUES (1);

-- Insert Into mst_students --

INSERT INTO mst_students (id, english_full_name, arabic_full_name, email, telephone_number, address) VALUES (-1, "hashem", "هاشم", "hashem@gmail.com","078562626","amman");
INSERT INTO mst_students (id, english_full_name, arabic_full_name, email, telephone_number, address) VALUES (-2, "mohammad", "محمد", "mo@gmail.com","0768562626","amman");

-- Insert Into mst_courses --

INSERT INTO mst_courses (id, title, description) VALUES (-1, "math", "lets do some maths");
INSERT INTO mst_courses (id, title, description) VALUES (-2, "science", "lets do some science");

-- Insert Into trn_students_courses --

INSERT INTO trn_students_courses (id, student_id, course_id) VALUES (-1, -1, -1);
INSERT INTO trn_students_courses (id, student_id, course_id) VALUES (-2, -1, -2);
INSERT INTO trn_students_courses (id, student_id, course_id) VALUES (-3, -2, -2);




