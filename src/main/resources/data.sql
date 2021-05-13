CREATE TABLE IF NOT EXISTS user (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    role VARCHAR(255) NOT NULL
);

INSERT INTO user (id, name, password, role) VALUES
  (1, 'Gino', 'gino', 'USER'),
  (2, 'Pino', 'pino', 'USER'),
  (3, 'Tino', 'tino', 'ADMIN');