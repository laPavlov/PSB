create table users
(
    id           serial
        constraint users_pk
            primary key,
    first_name   varchar,
    last_name    varchar,
    health_state varchar
);

alter table users
    owner to postgres;

create unique index users_id_uindex
    on users (id);

INSERT INTO ttp.users (id, first_name, last_name, health_state) VALUES (1, 'Artem', 'Pavlov', '1');
INSERT INTO ttp.users (id, first_name, last_name, health_state) VALUES (2, 'Dmitriy', 'Ivanov', '1');
INSERT INTO ttp.users (id, first_name, last_name, health_state) VALUES (3, 'Aleksei', 'Petrov', '1');
INSERT INTO ttp.users (id, first_name, last_name, health_state) VALUES (4, 'Aleksandr', 'Romanov', '1');
