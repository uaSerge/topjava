DELETE FROM user_roles;
DELETE FROM users;
DELETE FROM meals;
ALTER SEQUENCE global_seq RESTART WITH 100000;

INSERT INTO users (name, email, password) VALUES
  ('User', 'user@yandex.ru', 'password'),
  ('Admin', 'admin@gmail.com', 'admin');

INSERT INTO user_roles (role, user_id) VALUES
  ('ROLE_USER', 100000),
  ('ROLE_ADMIN', 100001);

INSERT INTO meals (dateTime, description, calories) VALUES
  ('2015-06-04 22:00:00', 'Завтрак', 500),
  ('2015-06-04 22:00:00', 'Ужин', 600),
('2015-07-04 22:00:00', 'Завтрак', 500),
('2015-07-04 22:00:00', 'Ужин', 600);


