CREATE TABLE accommodation (
  id           SERIAL PRIMARY KEY,
  name         VARCHAR(40)              NOT NULL,
  address      VARCHAR(40)              NOT NULL,
  phone        VARCHAR(15),
  home_page    VARCHAR(255),
  info         TEXT,
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
)