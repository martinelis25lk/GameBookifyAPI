-- Updating this column because we will be saving the image in the database as base64 for now
ALTER TABLE users ALTER COLUMN profile_picture TYPE TEXT;