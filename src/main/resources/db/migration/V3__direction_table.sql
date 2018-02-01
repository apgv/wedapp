CREATE TABLE direction (
  id           SERIAL PRIMARY KEY,
  title        VARCHAR(40)              NOT NULL,
  content      TEXT                     NOT NULL,
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
)