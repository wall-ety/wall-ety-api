DO $$ BEGIN
    IF NOT EXISTS (SELECT 1 FROM pg_type WHERE typname = 'transfer_status') THEN
        CREATE TYPE "transfer_status" AS ENUM ( 'CANCELLED', 'PENDING', 'COMPLETED' );
    END IF;
END $$;
