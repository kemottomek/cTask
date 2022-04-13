CREATE TABLE t_users (
  id BIGINT NOT NULL,
   firstname VARCHAR(255),
   lastname VARCHAR(255),
   email VARCHAR(255),
   CONSTRAINT pk_t_users PRIMARY KEY (id)
);

ALTER TABLE t_users ADD CONSTRAINT uc_t_users_email UNIQUE (email);

CREATE TABLE t_cases (
  id BIGINT NOT NULL,
   title VARCHAR(255),
   description VARCHAR(255),
   severity INTEGER,
   status INTEGER,
   user_id BIGINT NOT NULL,
   CONSTRAINT pk_t_cases PRIMARY KEY (id)
);

ALTER TABLE t_cases ADD CONSTRAINT FK_T_CASES_ON_USER FOREIGN KEY (user_id) REFERENCES t_users (id);

CREATE TABLE t_note (
  id BIGINT NOT NULL,
   case_id BIGINT NOT NULL,
   details VARCHAR(255),
   CONSTRAINT pk_t_note PRIMARY KEY (id)
);

ALTER TABLE t_note ADD CONSTRAINT FK_T_NOTE_ON_CASE FOREIGN KEY (case_id) REFERENCES t_cases (id);

