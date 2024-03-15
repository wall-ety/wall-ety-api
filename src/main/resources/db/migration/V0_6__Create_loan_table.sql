CREATE TABLE IF NOT EXISTS "loan" (
    "id" VARCHAR(255) PRIMARY KEY,
    "amount" DECIMAL(15, 2),
    "date_Taken_Effect" DATE,
    "percentage" DECIMAL(5, 2),
    "account" VARCHAR(255) REFERENCES "account"("id") NOT NULL
)