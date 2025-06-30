CREATE TABLE lessons (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    start_time TIMESTAMP UNIQUE NOT NULL,
    end_time TIMESTAMP NOT NULL,
    conference_link VARCHAR(255),

    student_id UUID REFERENCES users (id) ON DELETE CASCADE
);