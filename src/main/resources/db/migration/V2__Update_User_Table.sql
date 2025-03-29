-- Create a column updated_at for users table
-- Create a sequence for user id
-- Add updated_at column to users table
ALTER TABLE users ADD COLUMN updated_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP;

-- Create a sequence for user id
CREATE SEQUENCE IF NOT EXISTS users_id_seq
    START WITH 1
    INCREMENT BY 1
    NO MINVALUE
    NO MAXVALUE
    CACHE 1;

-- Set the sequence as the default for users.id if not already set
ALTER TABLE users ALTER COLUMN id SET DEFAULT nextval('users_id_seq'::regclass);