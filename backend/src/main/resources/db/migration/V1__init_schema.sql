CREATE TABLE car_brand (
                           id SERIAL PRIMARY KEY,
                           code VARCHAR(20) NOT NULL UNIQUE,
                           name VARCHAR(20)
);

CREATE TABLE car_model (
                           id SERIAL PRIMARY KEY,
                           name VARCHAR(20) NOT NULL,
                           car_brand_id INTEGER NOT NULL,
                           CONSTRAINT fk_car_brand FOREIGN KEY (car_brand_id) REFERENCES car_brand(id) ON DELETE CASCADE
);

CREATE TABLE client_submission (
                                   id SERIAL PRIMARY KEY,
                                   full_name VARCHAR(50) NOT NULL,
                                   phone_number VARCHAR(20) NOT NULL,
                                   selected_brand VARCHAR(20) NOT NULL,
                                   selected_model VARCHAR(20) NOT NULL,
                                   has_driver_license BOOLEAN NOT NULL
);
