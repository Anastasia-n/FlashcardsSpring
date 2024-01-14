create table vocabulary
(
    idword      bigint default nextval('vocabulary_id_seq'::regclass) not null
        constraint idword
            primary key,
    word        varchar(255)                                          not null,
    translation varchar(255)                                          not null,
    context     varchar(255),
    idfolderfk  bigint
        constraint idfolderfk
            references folder
            on delete cascade
);

alter table vocabulary
    owner to postgres;

INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (1, 'fox', 'лиса', 'to hunt the fox', 1);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (2, 'wolf', 'волк', 'wolf pack', 1);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (3, 'deer', 'олень', 'A young deer is a fawn', 1);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (4, 'tiger', 'тигр', 'He fought like a tiger', 1);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (11, 'number', 'число', 'The number 15 follows 14', 3);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (13, 'two', 'два', 'We have to be there by two', 3);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (14, 'three', 'три', 'They''ve won their last three games', 3);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (15, 'four', 'четыре', 'four books', 3);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (12, 'one', 'один', 'She is one year old', 3);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (5, 'animal', 'животное', 'domestic animal', 1);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (6, 'food', 'еда', 'delicious food', 2);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (7, 'bread', 'хлеб', 'a slice of bread', 2);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (8, 'milk', 'молоко', 'Milk is very rich in calcium', 2);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (9, 'meat', 'мясо', 'Meat shrinks as it cooks', 2);
INSERT INTO public.vocabulary (idword, word, translation, context, idfolderfk) VALUES (10, 'fish', 'рыба', 'Is there any fish on the menu?', 2);
