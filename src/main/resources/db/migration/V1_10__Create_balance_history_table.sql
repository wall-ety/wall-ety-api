CREATE TABLE IF NOT EXISTS "balence_history"(
    "id" VARCHAR(255) PRIMARY KEY,
    "amount" DECIMAL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "account" VARCHAR(255) REFERENCES "account"("id") NOT NULL
);