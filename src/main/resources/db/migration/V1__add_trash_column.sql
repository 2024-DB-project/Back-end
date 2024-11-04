-- Add is_deleted column if it does not exist
SET @column_exists = (SELECT COUNT(*)
                      FROM information_schema.COLUMNS
                      WHERE TABLE_NAME = 'EMPLOYEE'
                      AND COLUMN_NAME = 'trash');

IF @column_exists = 0 THEN
    ALTER TABLE EMPLOYEE ADD COLUMN trash BOOLEAN DEFAULT FALSE;
END IF;
