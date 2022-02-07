-- User

CREATE TABLE jhi_user
(
  id                 BIGINT      NOT NULL PRIMARY KEY AUTO_INCREMENT,
  login              VARCHAR(50) NOT NULL,
  password_hash      VARCHAR(60) NOT NULL,
  first_name         VARCHAR(50),
  last_name          VARCHAR(50),
  email              VARCHAR(191),
  image_url          VARCHAR(256),
  activated          BOOLEAN     NOT NULL,
  lang_key           VARCHAR(10),
  activation_key     VARCHAR(20),
  reset_key          VARCHAR(20),
  reset_date         TIMESTAMP,
  created_by         VARCHAR(50) NOT NULL,
  created_date       TIMESTAMP   NOT NULL,
  last_modified_by   VARCHAR(50) NOT NULL,
  last_modified_date TIMESTAMP   NOT NULL,
  UNIQUE (login),
  UNIQUE (email)
);

insert into jhi_user(login, password_hash, first_name, last_name, email, activated, lang_key, created_by,
                     created_date, last_modified_by, last_modified_date)
values ('admin', '$2a$10$gSAhZrxMllrbgj/kkK9UceBPpChGWJA7SYIb1Mqo.n5aNLq1/oRrC', 'Administrator', 'Administrator',
        'admin@localhost', true, 'en', 'system', current_timestamp, 'system', current_timestamp);

insert into jhi_user(login, password_hash, first_name, last_name, email, activated, lang_key, created_by,
                     created_date, last_modified_by, last_modified_date)
values ('user', '$2a$10$VEjxo0jq2YG9Rbk2HmX9S.k1uZBGYUHdUcid3g/vfiEl7lwWgOH/K', 'User', 'User', 'user@localhost',
        true, 'en', 'system', current_timestamp, 'system', current_timestamp);

-- jhi_authority

CREATE TABLE jhi_authority
(
  name VARCHAR(50) NOT NULL PRIMARY KEY
);

insert into jhi_authority(name) values ('ROLE_ADMIN');
insert into jhi_authority(name) values ('ROLE_USER');

-- jhi_user_authority

CREATE TABLE jhi_user_authority
(
  user_id        BIGINT      NOT NULL,
  authority_name VARCHAR(50) NOT NULL
);

ALTER TABLE jhi_user_authority
  ADD CONSTRAINT pk_user_authority PRIMARY KEY (user_id, authority_name);

ALTER TABLE jhi_user_authority
  ADD CONSTRAINT fk_user_auth_user_id FOREIGN KEY (user_id) REFERENCES jhi_user (id);
ALTER TABLE jhi_user_authority
  ADD CONSTRAINT fk_user_auth_auth_name FOREIGN KEY (authority_name) REFERENCES jhi_authority (name);

insert into jhi_user_authority(user_id, authority_name)
values (1, 'ROLE_ADMIN');
insert into jhi_user_authority(user_id, authority_name)
values (1, 'ROLE_USER');
insert into jhi_user_authority(user_id, authority_name)
values (2, 'ROLE_USER');

