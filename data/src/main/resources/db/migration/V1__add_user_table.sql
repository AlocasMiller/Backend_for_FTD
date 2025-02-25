CREATE TABLE IF NOT EXISTS users (
  id          UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
  name        VARCHAR(30)  NOT NULL,
  login       VARCHAR(30)  NOT NULL,
  password    VARCHAR(255) NOT NULL,
  role        VARCHAR(20)  NOT NULL,

  CHECK (role in ('STUDENT', 'TEACHER', 'DEAN', 'ADMIN'))
);

CREATE TABLE IF NOT EXISTS tokens (
   user_id     UUID            NOT NULL,
   token       VARCHAR(255)    NOT NULL,
   expire_at   TIMESTAMP       NOT NULL,

   FOREIGN KEY (user_id) REFERENCES users (id)
);