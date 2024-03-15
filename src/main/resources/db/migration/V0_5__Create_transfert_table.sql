CREATE TABLE IF NOT EXISTS "transaction" (
    "id" VARCHAR(255) PRIMARY KEY,
    "transfert_datetime" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "reason" VARCHAR(255) NOT NULL,
    "amount" DECIMAL(18,5) NOT NULL,
    "account1" VARCHAR(255) REFERENCES "account"("id") NOT NULL,
    "account2" VARCHAR(255) REFERENCES "account"("id") NOT NULL
);