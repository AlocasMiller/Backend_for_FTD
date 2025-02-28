CREATE TABLE IF NOT EXISTS groups (
    id              UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    number          INTEGER NOT NULL
);

CREATE TABLE IF NOT EXISTS group_students (
    student_id         UUID NOT NULL REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    group_id        UUID NOT NULL REFERENCES groups (id) ON UPDATE CASCADE ON DELETE CASCADE,

    PRIMARY KEY (student_id, group_id)
);