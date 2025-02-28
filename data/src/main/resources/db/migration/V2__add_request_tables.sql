CREATE TABLE IF NOT EXISTS requests (
    id              UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    creator_id      UUID NOT NULL REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    moderator_id    UUID,
    created_at      TIMESTAMP NOT NULL,
    date_start      DATE NOT NULL,
    date_end        DATE NOT NULL,
    comment         VARCHAR(255),
    status          VARCHAR(20) NOT NULL DEFAULT 'PENDING',
    reason          VARCHAR(20) NOT NULL,
    file_in_dean    BOOLEAN DEFAULT FALSE,

    CHECK (status in ('PENDING', 'APPROVED', 'DECLINED')),
    CHECK (reason in ('FAMILY', 'ILLNESS', 'STUDY_ACTIVITY'))
);

CREATE TABLE IF NOT EXISTS files (
    id              UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    request_id      UUID NOT NULL,
    file_data       BYTEA NOT NULL,

    FOREIGN KEY (request_id) REFERENCES requests (id)
);