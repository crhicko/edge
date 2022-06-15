\connect postgres


CREATE TABLE events (
        id      SERIAL UNIQUE NOT NULL PRIMARY KEY,
        sport   varchar NOT NULL,
        match_title varchar NOT NULL,
        data_event varchar NOT NULL
);

CREATE TABLE executions (
	id	SERIAL UNIQUE NOT NULL PRIMARY KEY,
	symbol	varchar NOT NULL,
	market	varchar NOT NULL,
	price	DOUBLE PRECISION NOT NULL,
	quantity	DOUBLE PRECISION NOT NULL,
	execution_epoch	TIMESTAMP NOT NULL,
	state_symbol	varchar NOT NULL
);
