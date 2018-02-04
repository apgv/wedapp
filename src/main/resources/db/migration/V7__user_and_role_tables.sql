CREATE TABLE "user" (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(40)              NOT NULL,
  email        VARCHAR(40)              NOT NULL,
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
);

CREATE TABLE role (
  id          SERIAL PRIMARY KEY,
  name        VARCHAR(20)  NOT NULL,
  description VARCHAR(500) NOT NULL
);

CREATE TABLE user_role (
  id           SERIAL,
  user_id      INTEGER REFERENCES "user" (id),
  role_id      INTEGER REFERENCES role (id),
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL,
  PRIMARY KEY (id, user_id, role_id)
);