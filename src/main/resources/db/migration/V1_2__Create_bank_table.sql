CREATE TABLE IF NOT EXISTS "bank"(
    "id" VARCHAR(255) PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "authorize_credits" BOOLEAN,
    "first_week_loan" FLOAT,
    "subsequent_loan" FLOAT
);