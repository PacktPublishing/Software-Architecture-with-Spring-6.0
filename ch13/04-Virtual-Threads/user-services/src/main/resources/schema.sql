create table users
(
    id           bigserial
        primary key,
    city         varchar(255),
    country      varchar(255),
    email        varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    state        varchar(255)
);

alter table users
    owner to auction_app;

create table role
(
    id   bigserial
        primary key,
    name varchar(255)
);

alter table role
    owner to auction_app;

create table user_role
(
    user_id bigint not null
        constraint fkj345gk1bovqvfame88rcx7yyx
            references users,
    role_id bigint not null
        constraint fka68196081fvovjhkek5m97n3y
            references role,
    primary key (user_id, role_id)
);

alter table user_role
    owner to auction_app;

create table person
(
    id         integer not null
        primary key,
    first_name varchar(255),
    last_name  varchar(255)
);

alter table person
    owner to auction_app;

INSERT INTO role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO role (id, name) VALUES (2, 'ROLE_USER');

INSERT INTO user_role (user_id, role_id) VALUES (2, 1);
INSERT INTO user_role (user_id, role_id) VALUES (1, 2);
INSERT INTO user_role (user_id, role_id) VALUES (2, 2);

INSERT INTO users (id, city, country, email, name, phone_number, state) VALUES ('New York', 'United States', 'user@wxauction.com', 'User X', '123456789', 'NY');
INSERT INTO users (id, city, country, email, name, phone_number, state) VALUES ('New York', 'United States', 'admin@wxauction.com', 'Admin X', '123456789', 'NY');


