CREATE TABLE IF NOT EXISTS "account" (
    "id" VARCHAR(255) PRIMARY KEY,
    "ref" VARCHAR(255) NOT NULL,
    "name" VARCHAR(255),
    "authorize_credits" BOOLEAN,
    "created_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    "updated_at" TIMESTAMP DEFAULT CURRENT_TIMESTAMP, 
    "client" VARCHAR(255) REFERENCES "client"("id") NOT NULL,
    "bank" VARCHAR(255) REFERENCES "bank"("id") NOT NULL
);