-- Todo App Database Schema
-- Run this script to create the database manually (optional — Spring Boot can create it via createDatabaseIfNotExist)

CREATE DATABASE IF NOT EXISTS todo_db
    CHARACTER SET utf8mb4
    COLLATE utf8mb4_unicode_ci;

USE todo_db;

CREATE TABLE IF NOT EXISTS users (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    full_name   VARCHAR(100)  NOT NULL,
    username    VARCHAR(50)   NOT NULL UNIQUE,
    email       VARCHAR(255)  NOT NULL UNIQUE,
    password    VARCHAR(255)  NOT NULL,
    created_at  DATETIME      NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;

CREATE TABLE IF NOT EXISTS tasks (
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_id     BIGINT        NOT NULL,
    title       VARCHAR(200)  NOT NULL,
    description TEXT,
    due_date    DATE,
    due_time    TIME,
    priority    VARCHAR(20)   NOT NULL DEFAULT 'MEDIUM',
    status      VARCHAR(20)   NOT NULL DEFAULT 'PENDING',
    category    VARCHAR(100),
    remarks     VARCHAR(500),
    created_at  DATETIME      NOT NULL,
    updated_at  DATETIME      NOT NULL,
    CONSTRAINT fk_tasks_user FOREIGN KEY (user_id) REFERENCES users(id) ON DELETE CASCADE,
    INDEX idx_tasks_user (user_id),
    INDEX idx_tasks_due_date (due_date),
    INDEX idx_tasks_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
