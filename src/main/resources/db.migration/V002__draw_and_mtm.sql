 CREATE EXTENSION IF NOT EXISTS "pgcrypto";

CREATE TABLE draw (
  id UUID PRIMARY KEY,
  title_draw VARCHAR(40) NOT NULL,
  description VARCHAR(255) NOT NULL,
  image_preview VARCHAR(50),
  create_ts TIMESTAMP WITHOUT TIME ZONE NOT NULL DEFAULT NOW(),
  draw_ts TIMESTAMP WITHOUT TIME ZONE NOT NULL
);

CREATE TABLE client_draw (
  id UUID PRIMARY KEY DEFAULT gen_random_uuid(),
  client_id UUID NOT NULL REFERENCES client(id),
  draw_id UUID NOT NULL REFERENCES draw(id)
);