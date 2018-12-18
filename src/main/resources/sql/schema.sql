CREATE TABLE IF NOT EXISTS user (
  id       INT AUTO_INCREMENT PRIMARY KEY,

  username VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL

);
CREATE TABLE IF NOT EXISTS employee
(
  id               INT AUTO_INCREMENT PRIMARY KEY,

  first_name       VARCHAR(45) NOT NUll,
  last_name        VARCHAR(45) NOT NUll,
  middle_name      VARCHAR(45) NOT NUll,
  position         VARCHAR(45) NOT NUll,
  phone            VARCHAR(11) NOT NUll,
  doc_name         VARCHAR(45) NOT NUll,
  doc_code         int         NOT NULL,
  doc_date         date        NOT NULL,

  citizenship_name VARCHAR(45) NOT NULL,
  citizenship_code INT         NOT NULL,
  identified       TINYINT(1)  NOT NULL,

  office_id        INT         NOT NULL,
  countries_id     INT         NULL,
  document_id      INT         NULL,

  CONSTRAINT user_office_id_fk
  FOREIGN KEY (office_id) REFERENCES office (id),
  CONSTRAINT employee_countries_id_fk
  FOREIGN KEY (countries_id) REFERENCES countries (id),
  CONSTRAINT employee_document_id_fk
  FOREIGN KEY (document_id) REFERENCES document (id)
);
CREATE TABLE IF NOT EXISTS organization
(
  id        INT AUTO_INCREMENT PRIMARY KEY,
  name      VARCHAR(45) NOT NULL,
  full_name VARCHAR(45) NOT NULL,
  inn       INT         NOT NULL,
  kpp       INT         NOT NULL,
  address   VARCHAR(45) NOT NULL,
  phone     INT         NULL,
  active    TINYINT(1)  NOT NULL
);
CREATE TABLE IF NOT EXISTS office
(
  id              INT AUTO_INCREMENT PRIMARY KEY,
  name            VARCHAR(45) NOT NULL,
  address         VARCHAR(45) NOT NULL,
  phone           INT         NOT NULL,
  active          INT         NOT NULL,
  organization_id INT         NOT NULL,
  CONSTRAINT office_organization_id_fk
  FOREIGN KEY (organization_id) REFERENCES organization (id)
);
CREATE TABLE IF NOT EXISTS document
(
  id   INT PRIMARY KEY AUTO_INCREMENT,
  code INT         NOT NULL,
  name VARCHAR(45) NOT NULL
);
CREATE TABLE IF NOT EXISTS countries (
  id   INT PRIMARY KEY AUTO_INCREMENT,
  code INT         NOT NULL,
  name VARCHAR(45) NOT NULL
);
CREATE INDEX UX_Office_Name             ON office (name);
CREATE INDEX UX_Office_Phone            ON office (phone);
CREATE INDEX IX_Office_Active           ON office (active);
create index office_organization__fk    ON office (organization_id);
CREATE INDEX UX_Organization_Full_Name  ON organization (full_name);
CREATE INDEX UX_Organization_Inn        ON organization (inn);
CREATE INDEX UX_Organization_Kpp        ON organization (kpp);
CREATE INDEX IX_Organization_Active     ON organization (active);
CREATE INDEX employee_countries_id_fk   ON employee (countries_id);
CREATE INDEX employee_document_id_fk    ON employee (document_id);
CREATE INDEX user_office_id_fk          ON employee (office_id);
