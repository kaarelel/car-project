CREATE TABLE car_brand (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL
);

CREATE TABLE car_model (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    car_brand_id INTEGER REFERENCES car_brand(id)
);

CREATE TABLE car_variant (
    id SERIAL PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    car_model_id INTEGER REFERENCES car_model(id)
);
