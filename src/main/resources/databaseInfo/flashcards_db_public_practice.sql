create table practice
(
    idpractice        bigint default nextval('practice_id_seq'::regclass) not null
        constraint idpractice
            primary key,
    numberofpractices integer,
    firstpracticedate timestamp,
    lastpracticedate  timestamp,
    repetitionstage   integer,
    idfolderfk        bigint
        constraint uk_33vxg7beuohipmyjigld5rp7f
            unique
        constraint idfolderfk
            references folder
            on delete cascade
);

alter table practice
    owner to postgres;

INSERT INTO public.practice (idpractice, numberofpractices, firstpracticedate, lastpracticedate, repetitionstage, idfolderfk) VALUES (18, 1, '2024-01-10 23:16:26.479000', '2024-01-10 23:16:26.479000', 1, 2);
INSERT INTO public.practice (idpractice, numberofpractices, firstpracticedate, lastpracticedate, repetitionstage, idfolderfk) VALUES (17, 5, '2024-01-10 23:17:30.695000', '2024-01-11 01:04:01.429000', 4, 1);
