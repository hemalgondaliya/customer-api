
create schema gayatri authorization postgres;

-- Table: gayatri.user_login

-- DROP TABLE gayatri.user_login;

CREATE TABLE gayatri.user_login
(
    id bigint NOT NULL,
    name character varying(50) ,
    password character varying(500),
    CONSTRAINT user_login_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.user_login
    OWNER to postgres;


-- Table: gayatri.brand

-- DROP TABLE gayatri.brand;

CREATE TABLE gayatri.brand
(
    id bigint NOT NULL,
    category character varying(50),
    name character varying(50) ,
    CONSTRAINT brand_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.brand
    OWNER to postgres;


-- Table: gayatri.product_model

-- DROP TABLE gayatri.product_model;

CREATE TABLE gayatri.product_model
(
    id bigint NOT NULL,
    deprecated boolean NOT NULL,
    name character varying(50),
    brand_id bigint NOT NULL,
    CONSTRAINT product_model_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.product_model ADD CONSTRAINT fk_brand_id FOREIGN KEY (brand_id)
        REFERENCES gayatri.brand (id);

ALTER TABLE gayatri.product_model
    OWNER to postgres;


-- Table: gayatri.cheque_payment

-- DROP TABLE gayatri.cheque_payment;

CREATE TABLE gayatri.cheque_payment
(
    id bigint NOT NULL,
    bank_name character varying(50),
    cheque_number integer NOT NULL,
    CONSTRAINT cheque_payment_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.cheque_payment
    OWNER to postgres;


-- Table: gayatri.delivery_person

-- DROP TABLE gayatri.delivery_person;

CREATE TABLE gayatri.delivery_person
(
    id bigint NOT NULL,
    address character varying(50),
    name character varying(50),
    phone_no bigint NOT NULL,
    tempo_no character varying(50),
    CONSTRAINT delivery_person_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.delivery_person
    OWNER to postgres;


-- Table: gayatri.customer

-- DROP TABLE gayatri.customer;

CREATE TABLE gayatri.customer
(
    id bigint NOT NULL,
    address character varying(50),
    bill_no integer NOT NULL,
    date date NOT NULL,
    email character varying(50),
    first_name character varying(50),
    last_name character varying(50),
    phone_no bigint NOT NULL,
    reference_name character varying(50),
    delivery_person_id bigint NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id)
);
ALTER TABLE gayatri.customer ADD CONSTRAINT uk_bill_no_phone_no UNIQUE (bill_no, phone_no);
ALTER TABLE gayatri.customer ADD CONSTRAINT fk_delivery_person_id FOREIGN KEY (delivery_person_id)
        REFERENCES gayatri.delivery_person (id);

ALTER TABLE gayatri.customer
    OWNER to postgres;


-- Table: gayatri.payment

-- DROP TABLE gayatri.payment;

CREATE TABLE gayatri.payment
(
    id bigint NOT NULL,
    amount integer NOT NULL,
    date date NOT NULL,
    payment_method character varying(20),
    receiver character varying(50),
    cheque_payment_id bigint,
    customer_id bigint NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.payment ADD CONSTRAINT fk_cheque_payment_id FOREIGN KEY (cheque_payment_id)
        REFERENCES gayatri.cheque_payment (id);

ALTER TABLE gayatri.payment ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id)
        REFERENCES gayatri.customer (id);

ALTER TABLE gayatri.payment
    OWNER to postgres;



-- Table: gayatri.product

-- DROP TABLE gayatri.product;

CREATE TABLE gayatri.product
(
    id bigint NOT NULL,
    price integer NOT NULL,
    ser_no character varying(50),
    product_model_id bigint NOT NULL,
    customer_id bigint,
    CONSTRAINT product_pkey PRIMARY KEY (id)
);

ALTER TABLE gayatri.product ADD CONSTRAINT fk_product_model_id FOREIGN KEY (product_model_id)
        REFERENCES gayatri.product_model (id);

ALTER TABLE gayatri.product ADD CONSTRAINT fk_customer_id FOREIGN KEY (customer_id)
        REFERENCES gayatri.customer (id);

ALTER TABLE gayatri.product
    OWNER to postgres;
