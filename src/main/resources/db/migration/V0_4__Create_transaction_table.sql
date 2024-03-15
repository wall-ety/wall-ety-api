CREATE TABLE IF NOT EXISTS "transaction" (
    "id" VARCHAR(255) PRIMARY KEY,
    "transaction_datetime" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "reason" VARCHAR(255) NOT NULL,
    "amount" DECIMAL(18,5) NOT NULL,
    "account" VARCHAR(255) REFERENCES "account"("id") NOT NULL,
    "type" VARCHAR(255) REFERENCES "transaction_type"("id") NOT NULL
);