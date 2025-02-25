CREATE TABLE IF NOT EXISTS requests (
    id              UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    creator_id      UUID NOT NULL REFERENCES users (id) ON UPDATE CASCADE ON DELETE CASCADE,
    moderator_id    UUID,
    date_start      DATE NOT NULL,
    date_end        DATE,
    comment         VARCHAR(255),
    status          VARCHAR(20) NOT NULL,

    CHECK (status in ('PENDING', 'APPROVED', 'DECLINED'))
);

CREATE TABLE IF NOT EXISTS files (
    request_id      UUID NOT NULL,
    file_url        VARCHAR(255) NOT NULL,
    PRIMARY KEY (request_id, file_url),

    FOREIGN KEY (request_id) REFERENCES requests (id)
);