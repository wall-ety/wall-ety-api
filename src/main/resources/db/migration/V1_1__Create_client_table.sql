CREATE TABLE IF NOT EXISTS "client"(
    "id" VARCHAR(255) PRIMARY KEY,
    "last_name" VARCHAR(255) NOT NULL,
    "first_name" VARCHAR(255),
    "birthday" DATE,
    "month_salary" DECIMAL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP 
);