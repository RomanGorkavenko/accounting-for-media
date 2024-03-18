CREATE TABLE "users"
(
    "id"        bigserial    NOT NULL,
    "firstname" VARCHAR(255) NULL,
    "lastname"  VARCHAR(255) NULL,
    "username"  VARCHAR(255) NOT NULL,
    "email"     VARCHAR(255) NOT NULL,
    "password"  VARCHAR(255) NOT NULL
);
ALTER TABLE
    "users"
    ADD PRIMARY KEY ("id");
ALTER TABLE
    "users"
    ADD CONSTRAINT "users_username_unique" UNIQUE ("username");
ALTER TABLE
    "users"
    ADD CONSTRAINT "users_email_unique" UNIQUE ("email");


CREATE TABLE "roles"
(
    "id"    bigserial    NOT NULL,
    "title" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "roles"
    ADD PRIMARY KEY ("id");
ALTER TABLE
    "roles"
    ADD CONSTRAINT "roles_title_unique" UNIQUE ("title");

CREATE TABLE "user_role"
(
    "id"      bigserial NOT NULL,
    "user_id" bigserial NOT NULL,
    "role_id" bigserial NOT NULL
);
ALTER TABLE
    "user_role"
    ADD PRIMARY KEY ("id");