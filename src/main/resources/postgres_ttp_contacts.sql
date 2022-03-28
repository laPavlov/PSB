create table contacts
(
    id          integer default nextval('ttp.contacts_d_seq'::regclass) not null
        constraint contacts_pk
            primary key,
    first_user  integer                                                 not null
        constraint contacts_users_id_fk_2
            references users,
    second_user integer                                                 not null
        constraint contacts_users_id_fk
            references users
);

alter table contacts
    owner to postgres;

create unique index contacts_d_uindex
    on contacts (id);

INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (3, 1, 3);
INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (4, 2, 3);
INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (6, 1, 4);
INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (7, 3, 4);
INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (2, 2, 1);
INSERT INTO ttp.contacts (id, first_user, second_user) VALUES (8, 2, 4);
