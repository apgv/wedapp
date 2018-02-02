CREATE TABLE guest
(
  id           SERIAL PRIMARY KEY,
  full_name    VARCHAR(54)              NOT NULL,
  attending    BOOLEAN                  NOT NULL,
  note         VARCHAR(445),
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
);

