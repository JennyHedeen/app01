DROP TABLE IF EXISTS feedback;
DROP TABLE IF EXISTS appointments;
DROP TABLE IF EXISTS seances;
DROP TABLE IF EXISTS users;

CREATE TABLE users
(
  u_id      INTEGER AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR (255) NOT NULL,
  email     VARCHAR (255) UNIQUE  NOT NULL ,
  password  VARCHAR (255) NOT NULL,
  role      ENUM('CLIENT', 'MASTER', 'ADMIN')
);

CREATE TABLE seances
(
  s_id        INTEGER AUTO_INCREMENT PRIMARY KEY,
  start_time  TIME NOT NULL,
  end_time    TIME NOT NULL
);

CREATE TABLE appointments
(
  a_id        INTEGER AUTO_INCREMENT PRIMARY KEY,
  master_id   INTEGER,
  client_id   INTEGER,
  date        DATE    NOT NULL,
  seance_id   INTEGER,
  CONSTRAINT client_unique UNIQUE (client_id, date, seance_id),
  CONSTRAINT master_unique UNIQUE (master_id, date, seance_id),
  FOREIGN KEY (master_id) REFERENCES users (u_id) ON DELETE CASCADE,
  FOREIGN KEY (client_id) REFERENCES users (u_id) ON DELETE CASCADE,
  FOREIGN KEY (seance_id) REFERENCES seances(s_id)
);

CREATE TABLE feedback
(
  app_id      INTEGER UNIQUE NOT NULL,
  status      VARCHAR(20),
  description VARCHAR (255),
  FOREIGN KEY (app_id) REFERENCES appointments (a_id) ON DELETE CASCADE
);

INSERT INTO users (name, email, password, role) VALUES
  ('Ромашова Ольга', 'ro@gmail.com', '8c6976e5b5410415bde908bd4dee15dfb167a9c873fc4bb8a81f6f2ab448a918', 'ADMIN'),
  ('Тищенко Екатерина', 'te@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'MASTER'),
  ('Потапова Алеся', 'pa@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'CLIENT'),
  ('Смирнова Ольга', 'so@gmail.com', '5e884898da28047151d0e56f8dc6292773603d0d6aabbdd62a11ef721d1542d8', 'CLIENT'),
  ('Буланов Тимур', 'bt@gmail.com', 'fc613b4dfd6736a7bd268c8a0e74ed0d1c04a959f59dd74ef2874983fd443fc9', 'CLIENT');

INSERT INTO seances (start_time, end_time) VALUES
  ('8:00:00', '8:55:00'),
  ('9:00:00', '9:55:00'),
  ('10:00:00', '10:55:00'),
  ('11:00:00', '11:55:00'),
  ('12:00:00', '12:55:00'),
  ('13:00:00', '13:55:00'),
  ('14:00:00', '14:55:00'),
  ('15:00:00', '15:55:00');

INSERT INTO appointments (master_id, client_id, date, seance_id) VALUES
  (1, 2, '2018-05-22', 1),
  (1, 3, '2018-05-22', 3),
  (1, 4, '2018-05-22', 4),
  (1, 2, '2018-05-23', 2),
  (1, 4, '2018-05-23', 4),
  (1, 3, '2019-05-23', 1);