CREATE TABLE directions
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE tests
(
    id          BIGSERIAL PRIMARY KEY,
    name        TEXT NOT NULL,
    description TEXT NOT NULL
);

CREATE TABLE test_directions
(
    test_id      BIGINT,
    direction_id BIGINT,
    PRIMARY KEY (test_id, direction_id),
    FOREIGN KEY (test_id) REFERENCES tests (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);

CREATE TABLE candidates
(
    id          BIGSERIAL PRIMARY KEY,
    second_name TEXT  NOT NULL,
    first_name  TEXT  NOT NULL,
    patronymic  TEXT  NOT NULL,
    photo       BYTEA NOT NULL,
    description TEXT  NOT NULL,
    cv_file     BYTEA NOT NULL
);

CREATE TABLE candidate_directions
(
    candidate_id BIGINT,
    direction_id BIGINT,
    PRIMARY KEY (candidate_id, direction_id),
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (direction_id) REFERENCES directions (id)
);

CREATE TABLE candidate_tests
(
    id           BIGSERIAL PRIMARY KEY,
    candidate_id BIGINT NOT NULL,
    test_id      BIGINT NOT NULL,
    FOREIGN KEY (candidate_id) REFERENCES candidates (id),
    FOREIGN KEY (test_id) REFERENCES tests (id)
);

CREATE TABLE results
(
    id                BIGSERIAL PRIMARY KEY,
    date              TIMESTAMPTZ NOT NULL,
    mark              INT         NOT NULL,
    candidate_test_id BIGINT      NOT NULL,
    FOREIGN KEY (candidate_test_id) REFERENCES candidate_tests (id)
);