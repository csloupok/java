CREATE TABLE IF NOT EXISTS BRAND
(
    BRAND_ID   BIGINT PRIMARY KEY NOT NULL,
    BRAND_NAME VARCHAR(50)        NOT NULL,
    FOUND_DATE DATE               NOT NULL
);

CREATE TABLE IF NOT EXISTS MODEL
(
    MODEL_ID   BIGINT PRIMARY KEY NOT NULL,
    MODEL_NAME VARCHAR(50)        NOT NULL,
    LENGTH     INT                NOT NULL,
    WIDTH      INT                NOT NULL,
    TYPE       TEXT               NOT NULL CHECK (
                TYPE = 'HATCHBACK'
            OR TYPE = 'SEDAN'
            OR TYPE = 'COUPE'
            OR TYPE = 'PICKUP TRUCK'
            OR TYPE = 'ROADSTER'
            OR TYPE = 'STATION WAGON'
        ),
    BRAND      BIGINT             NOT NULL,
    CONSTRAINT FK_BRAND
        FOREIGN KEY (BRAND)
            REFERENCES BRAND (BRAND_ID) ON DELETE CASCADE
);

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
