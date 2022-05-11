INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurant (id, name)
VALUES (1, 'RESTAURANT_1'),
       (2, 'RESTAURANT_2'),
       (3, 'RESTAURANT_3');

INSERT INTO dish (id, date, description, restaurant_id)
VALUES (1, '2020-01-30', 'bread', 1),
       (2, '2020-01-30', 'milk', 1),
       (3, '2020-01-30', 'juice', 1),
       (4, '2020-01-31', 'salami', 2),
       (5, '2020-05-31', 'nuts', 2),
       (6, '2020-05-31', 'cabbage', 2),
       (7, now(), 'chocolate', 3),
       (8, now(), 'ice cream', 3);

INSERT INTO vote (id, user_id, restaurant_id, date, time)
VALUES (1, 1, 1, '2022-04-16', '19:44:15'),
       (2, 2, 1, '2022-04-16', '19:44:15' ),
       (3, 2, 2, '2022-04-17', '19:44:15' ),
       (4, 1, 2, '2022-04-17', '19:44:15' ),
       (5, 2, 3, '2022-04-18', '19:44:15' ),
       (6, 1, 2, '2022-04-18', '19:44:15' ),
       (7, 2, 1, now(), now() ),
       (8, 1, 1, now(), now() );
