create table folder
(
    idfolder   bigint default nextval('folder_id_seq'::regclass) not null
        constraint idfolder
            primary key,
    namefolder varchar(45)                                       not null,
    iduserfk   bigint
        constraint iduserfk
            references users
            on delete cascade
);

alter table folder
    owner to postgres;

INSERT INTO public.folder (idfolder, namefolder, iduserfk) VALUES (3, 'numbers', 2);
INSERT INTO public.folder (idfolder, namefolder, iduserfk) VALUES (2, 'food', 1);
INSERT INTO public.folder (idfolder, namefolder, iduserfk) VALUES (1, 'animals', 1);
