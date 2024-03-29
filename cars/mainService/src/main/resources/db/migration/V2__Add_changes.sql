ALTER TABLE IF EXISTS MODEL
    ADD COLUMN IF NOT EXISTS HEIGHT INT;

CREATE TABLE IF NOT EXISTS ENGINE
(
    ENGINE_ID   BIGINT PRIMARY KEY,
    ENGINE_NAME VARCHAR(50),
    VOLUME      INT,
    CYLINDERS   INT,
    HEIGHT      INT,
    MODEL       BIGINT,
    CONSTRAINT FK_MODEL
        FOREIGN KEY (MODEL)
            REFERENCES MODEL (MODEL_ID) ON DELETE CASCADE
);
