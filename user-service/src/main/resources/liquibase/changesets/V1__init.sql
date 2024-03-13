CREATE TABLE "users"
(
    "id"        UUID         NOT NULL,
    "firstname" VARCHAR(255) NOT NULL,
    "lastname"  VARCHAR(255) NULL,
    "username"  VARCHAR(255) NOT NULL,
    "email"     VARCHAR(255) NOT NULL,
    "password"  VARCHAR(255) NOT NULL
);
ALTER TABLE
    "users"
    ADD PRIMARY KEY ("id");