CREATE TABLE IF NOT EXISTS "transfer" (
    "amount" DECIMAL NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "effective_date" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "reason" TEXT NOT NULL,
    "status" VARCHAR(255) NOT NULL,
    "label" VARCHAR(255),
    "account1" VARCHAR(255) REFERENCES "account"("id") NOT NULL,
    "account2" VARCHAR(255) REFERENCES "account"("id") NOT NULL
);