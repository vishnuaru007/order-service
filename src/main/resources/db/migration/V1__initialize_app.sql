-- Description: Initialize application schema

-- Drop tables if they exist
DROP TABLE IF EXISTS session;
DROP TABLE IF EXISTS users;


-- Create users table
CREATE TABLE users (
    id SERIAL PRIMARY KEY,
    first_name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) UNIQUE NOT NULL,
    password VARCHAR(255) NOT NULL,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    status VARCHAR(10) DEFAULT 'active'
);


-- Insert initial user
INSERT INTO users
    (first_name, last_name, email, password, created_at, updated_at)
VALUES
    ('vishnu', 'aru', 'vishnuaru007@gmail.com', '$2a$10$l/FGxnOwfVI/oolOO6HKweSNzOrbU6phps6ldWePlQHn075YWi8wm', now(), now());


-- Create session table
CREATE TABLE session (
    id SERIAL PRIMARY KEY,
    jti VARCHAR(255),
    session_id VARCHAR(255),
    expires_at TIMESTAMP,
    created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP ,
    user_id BIGINT,
    CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES users(id)
);
