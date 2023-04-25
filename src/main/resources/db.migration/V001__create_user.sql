CREATE TABLE client (
  id UUID PRIMARY KEY,
  login VARCHAR(40) NOT NULL UNIQUE,
  password VARCHAR(250) NOT NULL,
  full_name VARCHAR(40) NOT NULL,
  email VARCHAR(40) NOT NULL,
  phone VARCHAR(11),
  role VARCHAR(15) NOT NULL,
  create_ts TIMESTAMP NOT NULL
);