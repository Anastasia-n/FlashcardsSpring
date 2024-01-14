create table progress
(
    idprogress   bigint default nextval('progress_id_seq'::regclass) not null
        constraint idprogress
            primary key,
    correct      integer,
    mistakes     integer,
    idpracticefk bigint
        constraint idpracticefk
            references practice
            on delete cascade
);

alter table progress
    owner to postgres;

INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (70, 4, 1, 17);
INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (71, 5, 0, 17);
INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (72, 5, 0, 17);
INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (73, 5, 0, 18);
INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (74, 5, 0, 17);
INSERT INTO public.progress (idprogress, correct, mistakes, idpracticefk) VALUES (75, 5, 0, 17);
