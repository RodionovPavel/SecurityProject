CREATE TABLE win_prize (
    id UUID PRIMARY KEY,
    title_draw VARCHAR(40) NOT NULL,
    description TEXT,
    place INTEGER NOT NULL,
    create_ts TIMESTAMP WITHOUT TIME ZONE NOT NULL,
    draw_id UUID REFERENCES draw(id),
    client_id UUID REFERENCES client(id)
);