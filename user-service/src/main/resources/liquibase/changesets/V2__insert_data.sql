INSERT INTO users (firstname, lastname, username, email, password)
VALUES ('admin', 'admin', 'admin', 'admin@admin.com', 'admin'),
       ('user', 'user', 'user', 'user@user.com', 'user');

INSERT INTO roles (title)
VALUES ('ROLE_ADMIN'), ('ROLE_USER');

INSERT INTO user_role (user_id, role_id)
VALUES (1, 1), (1, 2), (2, 2);