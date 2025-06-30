CREATE TABLE study_plans (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    name VARCHAR(40),
    link VARCHAR(255)
);