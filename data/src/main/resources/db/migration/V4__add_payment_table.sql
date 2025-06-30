CREATE TABLE payments (
    id UUID NOT NULL PRIMARY KEY DEFAULT gen_random_uuid(),
    lesson_id UUID REFERENCES lessons (id) ON DELETE SET NULL,
    amount DECIMAL(10,2) NOT NULL,
    payment_date TIMESTAMP NOT NULL,
    status VARCHAR(10) NOT NULL,
    document BYTEA NOT NULL
);