CREATE TABLE IF NOT EXISTS "transfer" (
    "id" VARCHAR(255) PRIMARY KEY,
    "amount" DECIMAL NOT NULL,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "effective_date" TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
    "reason" TEXT NOT NULL,
    "status" "transfer_status" NOT NULL,
    "label" VARCHAR(255),
    "account1" VARCHAR(255) REFERENCES "account"("id") NOT NULL,
    "account2" VARCHAR(255) REFERENCES "account"("id") NOT NULL
);