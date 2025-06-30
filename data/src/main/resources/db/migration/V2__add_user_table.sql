CREATE TABLE users (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    telegram_id BIGINT UNIQUE NOT NULL,
    name VARCHAR(255) NOT NULL,
    phone VARCHAR(20),
    rate INTEGER NOT NULL,

    study_plan UUID REFERENCES study_plans (id)
);