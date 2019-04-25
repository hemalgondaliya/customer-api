Brand
INSERT INTO public.brand (id, category, name) VALUES (1, 'Refrigerator', 'Toshiba');
INSERT INTO public.brand (id, category, name) VALUES (2, 'Tv', 'Toshiba');
INSERT INTO public.brand (id, category, name) VALUES (3, 'HT', 'Sony');
INSERT INTO public.brand (id, category, name) VALUES (4, 'Tv', 'Sony');
INSERT INTO public.brand (id, category, name) VALUES (5, 'Refrigerator', 'Samsung');
INSERT INTO public.brand (id, category, name) VALUES (6, 'Microwave', 'Samsung');

INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (1, false, 'SN-65SRN', 3);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (2, false, 'SN-65SRN1', 4);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (3, false, 'TS-65SRN', 1);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (4, false, 'TS-65SRN1', 2);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (5, false, 'SM-65SRN', 5);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (6, false, 'SM-65SRN1', 6);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (7, false, 'SN-65SRN2', 3);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (8, false, 'TS-65SRN2', 1);
INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (9, false, 'SM-65SRN2', 6);

INSERT INTO public.product_model (id, deprecated, name, brand_id) VALUES (10, true, 'SN-65SRN3', 3);


INSERT INTO public.delivery_person(id, address, name, phone_no, tempo_no) VALUES (1, 'xxx', 'Bapu', 9999999, 'GJ5-5446');
INSERT INTO public.delivery_person(id, address, name, phone_no, tempo_no) VALUES (2, 'xxx', 'Bhagat', 9999999, 'GJ5-5446');
INSERT INTO public.delivery_person(id, address, name, phone_no, tempo_no) VALUES (3, 'xxx', 'Self', 9999999, 'GJ5-5446');


#User Login
INSERT INTO public.user_login(id, name, password)VALUES (1, 'Hemal', '$2a$10$VqgLfQkxQXfv4HgwZwUXg.pyglLD2aZvCiQXhyadOm8Mc8LeASnPm');


#Deletion
DROP TABLE IF EXISTS public.brand CASCADE;
DROP TABLE IF EXISTS public.customer CASCADE;
DROP TABLE IF EXISTS public.product CASCADE;
DROP TABLE IF EXISTS public.product_model CASCADE;
DROP TABLE IF EXISTS public.delivery_person CASCADE;
DROP TABLE IF EXISTS public.payment CASCADE;
DROP TABLE IF EXISTS public.cheque_payment CASCADE;







