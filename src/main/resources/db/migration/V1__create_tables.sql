-- Таблица пользователей
CREATE TABLE users (
                       id SERIAL PRIMARY KEY,
                       username VARCHAR(50) UNIQUE NOT NULL,
                       password VARCHAR(100) NOT NULL,
                       email VARCHAR(100) UNIQUE NOT NULL,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Таблица задач
CREATE TABLE tasks (
                       id SERIAL PRIMARY KEY,
                       user_id INTEGER REFERENCES users(id) ON DELETE CASCADE,
                       title VARCHAR(200) NOT NULL,
                       description TEXT,
                       completed BOOLEAN DEFAULT false,
                       created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP
);

-- Тестовые данные (опционально)
INSERT INTO users (username, password, email) VALUES
                                                  ('admin', 'admin123', 'admin@example.com'),
                                                  ('user1', 'password1', 'user1@example.com');