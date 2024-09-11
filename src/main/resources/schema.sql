CREATE SCHEMA IF NOT EXISTS finance;

CREATE TABLE IF NOT EXISTS finance.user (
    id SERIAL PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(200) NOT NULL
);

CREATE TABLE IF NOT EXISTS finance.budget (
    id SERIAL PRIMARY KEY,
    amount numeric(10, 2) NOT NULL,
    for_date DATE NOT NULL,
    user_id INT NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES finance.user(id)
        ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS finance.category (
    id SERIAL PRIMARY KEY,
    name VARCHAR(50) NOT NULL
);

CREATE TABLE IF NOT EXISTS finance.expense (
    id SERIAL PRIMARY KEY,
    amount numeric(10, 2) NOT NULL,
    date DATE NOT NULL,
    description VARCHAR(200),
    user_id INT NOT NULL,
    category_id INT NOT NULL,
    CONSTRAINT fk_user
        FOREIGN KEY(user_id)
        REFERENCES finance.user(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_expense
        FOREIGN KEY(category_id)
        REFERENCES finance.category(id)
        ON DELETE CASCADE
);