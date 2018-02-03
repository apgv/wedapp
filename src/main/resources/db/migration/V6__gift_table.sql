CREATE TABLE gift (
  id           SERIAL PRIMARY KEY,
  checkable    BOOLEAN                  NOT NULL,
  checked      BOOLEAN                  NOT NULL,
  title        VARCHAR(80)              NOT NULL,
  description  VARCHAR(445),
  link_url     VARCHAR(255),
  link_text    VARCHAR(80),
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
)