create database report_db;

\c report_db;

create table public.auction
(
    id           bigserial
        primary key,
    orig_id         varchar,
    created_at   timestamp,
    active      boolean,
    description text,
    maxbid      numeric,
    minbid      numeric,
    product_id   bigint
);

alter table auction
    owner to auction_app;

create table public.bid
(
     id           bigserial
        primary key,
    orig_id       varchar,
    amount    decimal,
    user_id    bigint,
    auction_id varchar,
    created_at timestamp
);

alter table public.bid
    owner to auction_app;

create table public.users
(
    id           numeric,
    city         varchar(255),
    country      varchar(255),
    email        varchar(255),
    name         varchar(255),
    phone_number varchar(255),
    state        varchar(255)
);

alter table users
    owner to auction_app;

create table public.product
(
    id          numeric,
    description varchar(255),
    name        varchar(255),
    photo       bytea,
    user_id     bigint
);

alter table product
    owner to auction_app;
