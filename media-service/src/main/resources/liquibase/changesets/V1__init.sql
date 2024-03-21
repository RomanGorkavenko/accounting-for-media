CREATE TABLE "objects"
(
    "id"          bigserial    NOT NULL,
    "title"       VARCHAR(255) NOT NULL,
    "description" TEXT         NULL
);
ALTER TABLE
    "objects"
    ADD PRIMARY KEY ("id");

CREATE TABLE "categories"
(
    "id"    bigserial    NOT NULL,
    "title" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "categories"
    ADD PRIMARY KEY ("id");
ALTER TABLE
    "categories"
    ADD CONSTRAINT "categories_title_unique" UNIQUE ("title");

CREATE TABLE "status"
(
    "id"    bigserial    NOT NULL,
    "title" VARCHAR(255) NOT NULL,
    "color" VARCHAR(255) NOT NULL
);
ALTER TABLE
    "status"
    ADD PRIMARY KEY ("id");
ALTER TABLE
    "status"
    ADD CONSTRAINT "status_title_unique" UNIQUE ("title");
ALTER TABLE
    "status"
    ADD CONSTRAINT "status_color_unique" UNIQUE ("color");

CREATE TABLE "media"
(
    "id"           bigserial    NOT NULL,
    "title"        VARCHAR(255) NOT NULL,
    "description"  TEXT         NULL,
    "number"       BIGINT       NOT NULL,
    "image_link"   VARCHAR(255) NULL,
    "object_id"    bigserial    NOT NULL,
    "user_id"      bigserial    NOT NULL,
    "start_date"   DATE         NULL,
    "service_life" INTEGER      NULL,
    "end_date"     DATE         NULL,
    "category_id"  bigserial    NOT NULL,
    "status_id"    bigserial    NOT NULL
);
ALTER TABLE
    "media"
    ADD PRIMARY KEY ("id");

ALTER TABLE
    "media"
    ADD CONSTRAINT "media_number_unique" UNIQUE ("number");

ALTER TABLE
    "media"
    ADD CONSTRAINT "media_status_id_foreign" FOREIGN KEY ("status_id") REFERENCES "status" ("id");
ALTER TABLE
    "media"
    ADD CONSTRAINT "media_category_id_foreign" FOREIGN KEY ("category_id") REFERENCES "categories" ("id");
ALTER TABLE
    "media"
    ADD CONSTRAINT "media_object_id_foreign" FOREIGN KEY ("object_id") REFERENCES "objects" ("id");
