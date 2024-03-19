INSERT INTO users (firstname, lastname, username, email, password)
VALUES ('admin', 'admin', 'admin', 'admin@admin.com', '$2a$10$y2jak0Y04mJlyO2jI7IWXOcHNNlOQiZDiyBF2SMM0Ef6S2vJzlple'),
       ('user', 'user', 'user', 'user@user.com', '$2a$10$ubRlZNisrBoqN0rDN9tQFezQCwbA/TFfolqtbGwAMJ7DmjgfPQU1u');

INSERT INTO roles (title)
VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1), (1, 2), (2, 2);