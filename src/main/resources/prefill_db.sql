
DELETE FROM data.daily_norm WHERE id IS NOT NULL;
SELECT setval('data.daily_norm_id_seq', (SELECT MAX(id) FROM data.daily_norm));
DELETE FROM data."user" WHERE id IS NOT NULL ;
SELECT setval('data.user_id_seq', (SELECT MAX(id) FROM data."user"));

insert into data."user" (id, first_name, middle_name, last_name, email, password, role_id, status)
VALUES (1, 'Ivan', 'Ivanovich', 'Adminov', 'ivan@mail.ru',
        '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 1, 'ACTIVE');

insert into data.user (id, first_name, middle_name, last_name, email, password, role_id, status)
VALUES (2, 'Petr', 'Petrovich', 'Userov', 'Petr@mail.ru',
        '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2, 'ACTIVE');

insert into data.user (id, first_name, middle_name, last_name, email, password, role_id, status)
VALUES (3, 'Sidor', 'Sidorovich', 'Userov', 'sidor@mail.ru',
        '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2, 'ACTIVE');

insert into data.user (id, first_name, middle_name, last_name, email, password, role_id, status)
VALUES (4, 'Sidor', 'Sidorovich', 'Dismissov', 'king-of-universe@mail.ru',
        '$2a$04$Fx/SX9.BAvtPlMyIIqqFx.hLY2Xp8nnhpzvEEVINvVpwIPbA3v/.i', 2, 'DISMISSED');
SELECT setval('data.user_id_seq', (SELECT MAX(id) FROM data."user"));

-- --------------------------
DELETE FROM data.product WHERE id IS NOT NULL;
SELECT setval('data.product_id_seq', (SELECT MAX(id) FROM data.product));
insert into data.product (id, name, measure_unit) VALUES (1, 'Картошка', 'KILOGRAM');
insert into data.product (id, name, measure_unit) VALUES (2, 'Морковка', 'KILOGRAM');
insert into data.product (id, name, measure_unit) VALUES (3, 'Молоко коровье', 'LITER');
insert into data.product (id, name, measure_unit) VALUES (4, 'Сметана козье', 'LITER');
insert into data.product (id, name, measure_unit) VALUES (5, 'Березовый сок', 'LITER');
insert into data.product (id, name, measure_unit) VALUES (6, 'Яйцо куриное', 'PIECE');
insert into data.product (id, name, measure_unit) VALUES (7, 'Тушка бройлера', 'PIECE');
insert into data.product (id, name, measure_unit) VALUES (8, 'Пшеница', 'CUBIC_METER');
insert into data.product (id, name, measure_unit) VALUES (9, 'Овес', 'CUBIC_METER');
SELECT setval('data.product_id_seq', (SELECT MAX(id) FROM data.product));

-- --------------------------
DELETE FROM data.daily_norm WHERE id IS NOT NULL;
SELECT setval('data.daily_norm_id_seq', (SELECT MAX(id) FROM data.daily_norm));

insert into data.daily_norm (id, product_id, user_id, norm) VALUES (1, 1, 1, 200);
insert into data.daily_norm (id, product_id, user_id, norm) VALUES (2, 1, 2, 300);
insert into data.daily_norm (id, product_id, user_id, norm) VALUES (3, 1, 3, 450);
insert into data.daily_norm (id, product_id, user_id, norm) VALUES (4, 3, 1, 20.0);
insert into data.daily_norm (id, product_id, user_id, norm) VALUES (5, 3, 2, 25.5);
insert into data.daily_norm (id, product_id, user_id, norm) VALUES (6, 3, 3, 15.7);

SELECT setval('data.daily_norm_id_seq', (SELECT MAX(id) FROM data.daily_norm));
