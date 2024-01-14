create table users
(
    iduser   bigint default nextval('users_id_seq'::regclass) not null
        constraint iduser
            primary key,
    login    varchar(255),
    name     varchar(255),
    password varchar(255),
    role     varchar(20)
);

alter table users
    owner to postgres;

INSERT INTO public.users (iduser, login, name, password, role) VALUES (2, 'firstUser', 'userName', '$2a$12$gGBye1x.bYvv.dvHCEM7SufpyJ46pM5ZFUy0C0rIlChjDLOOtitUe', 'USER');
INSERT INTO public.users (iduser, login, name, password, role) VALUES (1, 'admin', 'Анастасия', '$2a$12$XiATO9Z8nam7yZYQvjynuOGZr3E28YvY/q4NkvpxPxgdtqABoqc0W', 'USER');
