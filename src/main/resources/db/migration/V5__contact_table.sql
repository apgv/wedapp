CREATE TABLE contact (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(40)              NOT NULL,
  phone        VARCHAR(15),
  email        VARCHAR(40),
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
)