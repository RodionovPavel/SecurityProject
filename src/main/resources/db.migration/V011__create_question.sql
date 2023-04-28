CREATE TABLE question12 (
  id UUID PRIMARY KEY,
  weight INTEGER NOT NULL,
  title_question VARCHAR(100) NOT NULL,
  question VARCHAR(4000) NOT NULL,
  answer VARCHAR(2000) NOT NULL,
  if_right_answer VARCHAR(2000) NOT NULL,
  if_wrong_answer VARCHAR(2000) NOT NULL
);