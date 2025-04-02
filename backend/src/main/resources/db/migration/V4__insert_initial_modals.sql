INSERT INTO car_model (name, car_brand_id)
VALUES
    ('316', (SELECT id FROM car_brand WHERE code = 'BMW')),
    ('318', (SELECT id FROM car_brand WHERE code = 'BMW')),
    ('Q3', (SELECT id FROM car_brand WHERE code = 'AUDI')),
    ('Q5', (SELECT id FROM car_brand WHERE code = 'AUDI')),
    ('C 180', (SELECT id FROM car_brand WHERE code = 'MERCEDES_BENZ'));
