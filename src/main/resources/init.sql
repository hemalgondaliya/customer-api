Brand
INSERT INTO gayatri.brand (id, category, name) VALUES (1, 'Refrigerator', 'Samsung');
INSERT INTO gayatri.brand (id, category, name) VALUES (2, 'Tv', 'Samsung');
INSERT INTO gayatri.brand (id, category, name) VALUES (3, 'WM', 'Samsung');
INSERT INTO gayatri.brand (id, category, name) VALUES (4, 'Mobile', 'Samsung');
INSERT INTO gayatri.brand (id, category, name) VALUES (5, 'Oven', 'Samsung');
INSERT INTO gayatri.brand (id, category, name) VALUES (6, 'Refrigerator', 'Lg');
INSERT INTO gayatri.brand (id, category, name) VALUES (7, 'WM', 'Lg');
INSERT INTO gayatri.brand (id, category, name) VALUES (8, 'Refrigerator', 'Siemens');
INSERT INTO gayatri.brand (id, category, name) VALUES (9, 'Dishwasher', 'Siemens');
INSERT INTO gayatri.brand (id, category, name) VALUES (10, 'Washerdryer', 'Siemens');
INSERT INTO gayatri.brand (id, category, name) VALUES (11, 'WM', 'Siemens');
INSERT INTO gayatri.brand (id, category, name) VALUES (12, 'Refrigerator', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (13, 'Tv', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (14, 'WM', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (15, 'Oven', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (16, 'Dishwasher', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (17, 'Washerdryer', 'Bosch');
INSERT INTO gayatri.brand (id, category, name) VALUES (18, 'Sony', 'Tv');
INSERT INTO gayatri.brand (id, category, name) VALUES (19, 'Sony', 'HT');
INSERT INTO gayatri.brand (id, category, name) VALUES (20, 'Refrigerator', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (21, 'Tv', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (22, 'WM', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (23, 'Oven', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (24, 'AC', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (25, 'Mixer', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (26, 'HT', 'Panasonic');
INSERT INTO gayatri.brand (id, category, name) VALUES (27, 'Refrigerator', 'Hitachi');
INSERT INTO gayatri.brand (id, category, name) VALUES (28, 'AC', 'Hitachi');
INSERT INTO gayatri.brand (id, category, name) VALUES (29, 'Vacuum', 'Hitachi');
INSERT INTO gayatri.brand (id, category, name) VALUES (30, 'Refrigerator', 'Mitashi');
INSERT INTO gayatri.brand (id, category, name) VALUES (31, 'Refrigerator', 'Haire');
INSERT INTO gayatri.brand (id, category, name) VALUES (32, 'Aatamaker', 'Natraj');
INSERT INTO gayatri.brand (id, category, name) VALUES (33, 'Stabilizer', 'Sonix');
INSERT INTO gayatri.brand (id, category, name) VALUES (34, 'Stabilizer', 'Capri');


INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (1, false, 'SN-65SRN', 3);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (2, false, 'SN-65SRN1', 4);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (3, false, 'TS-65SRN', 1);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (4, false, 'TS-65SRN1', 2);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (5, false, 'SM-65SRN', 5);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (6, false, 'SM-65SRN1', 6);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (7, false, 'SN-65SRN2', 3);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (8, false, 'TS-65SRN2', 1);
INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (9, false, 'SM-65SRN2', 6);

INSERT INTO gayatri.product_model (id, deprecated, name, brand_id) VALUES (10, true, 'SN-65SRN3', 3);


INSERT INTO gayatri.delivery_person(id, address, name, phone_no, tempo_no) VALUES (1, 'xxx', 'Bapu', 9999999, 'GJ5-5446');
INSERT INTO gayatri.delivery_person(id, address, name, phone_no, tempo_no) VALUES (2, 'xxx', 'Bhagat', 9999999, 'GJ5-5446');
INSERT INTO gayatri.delivery_person(id, address, name, phone_no, tempo_no) VALUES (3, 'xxx', 'Self', 9999999, 'GJ5-5446');


#User Login
INSERT INTO gayatri.user_login(id, name, password)VALUES (1, 'Hemal', '$2a$10$VqgLfQkxQXfv4HgwZwUXg.pyglLD2aZvCiQXhyadOm8Mc8LeASnPm');


#Deletion
DROP TABLE IF EXISTS gayatri.brand CASCADE;
DROP TABLE IF EXISTS gayatri.customer CASCADE;
DROP TABLE IF EXISTS gayatri.product CASCADE;
DROP TABLE IF EXISTS gayatri.product_model CASCADE;
DROP TABLE IF EXISTS gayatri.delivery_person CASCADE;
DROP TABLE IF EXISTS gayatri.payment CASCADE;
DROP TABLE IF EXISTS gayatri.cheque_payment CASCADE;







