DROP TABLE vet_specialties IF EXISTS;
DROP TABLE vet IF EXISTS;
DROP TABLE visits IF EXISTS;
DROP TABLE pet IF EXISTS;
DROP TABLE owner IF EXISTS;


CREATE TABLE vet (
  id         LONG IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR(30)
);
CREATE INDEX vet_last_name ON vet (last_name);

CREATE TABLE vet_specialties (
  vet_id       LONG NOT NULL,
  specialties VARCHAR(80)
);
ALTER TABLE vet_specialties ADD CONSTRAINT fk_vet_specialties_vets FOREIGN KEY (vet_id) REFERENCES vet (id);

CREATE TABLE owner (
  id         LONG IDENTITY PRIMARY KEY,
  first_name VARCHAR(30),
  last_name  VARCHAR_IGNORECASE(30),
  address    VARCHAR(255),
  city       VARCHAR(80),
  email      VARCHAR(80),
  telephone  VARCHAR(20)
);
CREATE INDEX owners_last_name ON owner (last_name);

CREATE TABLE pet (
  id         LONG IDENTITY PRIMARY KEY,
  name       VARCHAR(30),
  birth_date DATE,
  type       VARCHAR(30),
  owner_id   LONG NOT NULL
);
ALTER TABLE pet ADD CONSTRAINT fk_pets_owners FOREIGN KEY (owner_id) REFERENCES owner (id);
CREATE INDEX pets_name ON pet (name);

CREATE TABLE visit (
  id          LONG IDENTITY PRIMARY KEY,
  vet_id      LONG NOT NULL,
  pet_id      LONG NOT NULL,
  start_date  DATETIME NOT NULL,
  end_date  DATETIME NOT NULL,
  description VARCHAR(255),
  CONSTRAINT chk_date CHECK(end_date >= start_date)
);
ALTER TABLE visit ADD CONSTRAINT fk_visits_pets FOREIGN KEY (pet_id) REFERENCES pet (id);
ALTER TABLE visit ADD CONSTRAINT fk_visits_vet FOREIGN KEY (vet_id) REFERENCES vet (id);


CREATE INDEX visits_pet_id ON visit (pet_id);
