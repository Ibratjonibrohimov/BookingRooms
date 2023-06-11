CREATE TABLE rooms (
                         "id" bigint generated always as identity PRIMARY KEY,
                         "name" varchar(30),
                         "type_id" int,
                         "capacity" int
);

CREATE TABLE room_types (
                              "id" int generated always as IDENTITY PRIMARY KEY,
                              "name" varchar(30)
);

CREATE TABLE bookings (
                            "id" bigint generated always as identity PRIMARY KEY,
                            "resident_id" bigint,
                            "start_at" timestamp,
                            "end_at" timestamp,
                            "room_id" bigint
);

CREATE TABLE residents (
                            "id" bigint generated always as IDENTITY PRIMARY KEY,
                            "name" varchar(30)
);

ALTER TABLE rooms ADD FOREIGN KEY ("type_id") REFERENCES room_types ("id");

ALTER TABLE bookings ADD FOREIGN KEY ("room_id") REFERENCES rooms ("id");

ALTER TABLE bookings ADD FOREIGN KEY ("resident_id") REFERENCES residents ("id");
