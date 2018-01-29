CREATE TABLE index_page (
  id           SERIAL PRIMARY KEY,
  content      TEXT,
  last_updated TIMESTAMP WITH TIME ZONE NOT NULL
)