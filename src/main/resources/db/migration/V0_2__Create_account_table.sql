CREATE TABLE IF NOT EXISTS "account" (
    "id" VARCHAR(255) PRIMARY KEY,
    "name" VARCHAR(255) NOT NULL,
    "balance" DECIMAL,
    "negative_account" Bool,
    "client" VARCHAR(255) REFERENCES "client"("id") NOT NULL
);