INSERT INTO users (name, email, password)
VALUES ('User', 'user@yandex.ru', '{noop}password'),
       ('Admin', 'admin@gmail.com', '{noop}admin');

INSERT INTO user_roles (role, user_id)
VALUES ('USER', 1),
       ('ADMIN', 2),
       ('USER', 2);

INSERT INTO restaurants (id, name)
VALUES (1, 'RESTAURANT_1'),
       (2, 'RESTAURANT_2'),
       (3, 'RESTAURANT_3');

INSERT INTO meals (id, date_time, description, restaurant_id)
VALUES (1, '2020-01-30 10:00:00', 'bread', 1),
       (2, '2020-01-30 13:00:00', 'milk', 1),
       (3, '2020-01-30 20:00:00', 'juice', 1),
       (4, '2020-01-31 0:00:00', 'salami', 2),
       (5, '2020-01-31 10:00:00', 'nuts', 2),
       (6, '2020-01-31 13:00:00', 'cabbage', 2),
       (7, '2020-01-31 20:00:00', 'chocolate', 3),
       (8, '2020-01-31 14:00:00', 'ice cream', 3);

INSERT INTO voting (id, user_id, restaurant_id, date, time)
VALUES (1, 1, 1, '2022-04-16', '19:44:15'),
       (2, 2, 1, '2022-04-16', '19:44:15' ),
       (3, 2, 2, '2022-04-17', '19:44:15' ),
       (4, 1, 2, '2022-04-17', '19:44:15' ),
       (5, 2, 3, '2022-04-18', '19:44:15' ),
       (6, 1, 2, '2022-04-18', '19:44:15' ),
       (7, 1, 1, '2022-04-19', '19:44:15' ),
       (8, 1, 1, now(), now() );
