CREATE TABLE IF NOT EXISTS "transaction" (
    "id" VARCHAR(255) PRIMARY KEY,
    "type" "transaction_type" NOT NULL,
    "amount" DECIMAL(18,5) NOT NULL,
    "transaction_datetime" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "reason" TEXT,
    "label" VARCHAR(255),
    "account" VARCHAR(255) REFERENCES "account"("id") NOT NULL,
    "category" VARCHAR(255) REFERENCES "category"("id") NOT NULL
);