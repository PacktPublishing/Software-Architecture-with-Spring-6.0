DELETE FROM user_role;
DELETE FROM role;
DELETE FROM users;

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO users ( id, city, country, email, name, phone_number, state) VALUES (1, 'New York', 'United States', 'user@wxauction.com', 'User X', '123456789', 'NY');
INSERT INTO users ( id, city, country, email, name, phone_number, state) VALUES (2, 'New York', 'United States', 'admin@wxauction.com', 'Admin X', '123456789', 'NY');

INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);
