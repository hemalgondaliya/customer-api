
create schema gayatri authorization postgres;

-- Table: gayatri.user_login

-- DROP TABLE gayatri.user_login;

CREATE TABLE gayatri.user_login
(
    id bigint NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    password character varying(500) COLLATE pg_catalog."default",
    CONSTRAINT user_login_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.user_login
    OWNER to postgres;


-- Table: gayatri.brand

-- DROP TABLE gayatri.brand;

CREATE TABLE gayatri.brand
(
    id bigint NOT NULL,
    category character varying(50) COLLATE pg_catalog."default",
    name character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT brand_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.brand
    OWNER to postgres;


-- Table: gayatri.product_model

-- DROP TABLE gayatri.product_model;

CREATE TABLE gayatri.product_model
(
    id bigint NOT NULL,
    deprecated boolean NOT NULL,
    name character varying(50) COLLATE pg_catalog."default",
    brand_id bigint NOT NULL,
    CONSTRAINT product_model_pkey PRIMARY KEY (id),
    CONSTRAINT fks8tfcg6bk9ojrfkj8t4y2wp5y FOREIGN KEY (brand_id)
        REFERENCES gayatri.brand (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.product_model
    OWNER to postgres;


-- Table: gayatri.cheque_payment

-- DROP TABLE gayatri.cheque_payment;

CREATE TABLE gayatri.cheque_payment
(
    id bigint NOT NULL,
    bank_name character varying(50) COLLATE pg_catalog."default",
    cheque_number integer NOT NULL,
    CONSTRAINT cheque_payment_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.cheque_payment
    OWNER to postgres;


-- Table: gayatri.delivery_person

-- DROP TABLE gayatri.delivery_person;

CREATE TABLE gayatri.delivery_person
(
    id bigint NOT NULL,
    address character varying(300) COLLATE pg_catalog."default",
    name character varying(50) COLLATE pg_catalog."default",
    phone_no bigint NOT NULL,
    tempo_no character varying(50) COLLATE pg_catalog."default",
    CONSTRAINT delivery_person_pkey PRIMARY KEY (id)
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.delivery_person
    OWNER to postgres;


-- Table: gayatri.customer

-- DROP TABLE gayatri.customer;

CREATE TABLE gayatri.customer
(
    id bigint NOT NULL,
    address character varying(300) COLLATE pg_catalog."default",
    bill_no integer NOT NULL,
    date date NOT NULL,
    email character varying(50) COLLATE pg_catalog."default",
    first_name character varying(50) COLLATE pg_catalog."default",
    last_name character varying(50) COLLATE pg_catalog."default",
    phone_no bigint NOT NULL,
    second_phone_no bigint,
    reference_name character varying(50) COLLATE pg_catalog."default",
    delivery_person_id bigint NOT NULL,
    deprecated boolean NOT NULL,
    CONSTRAINT customer_pkey PRIMARY KEY (id),
    CONSTRAINT fk_delivery_person_id FOREIGN KEY (delivery_person_id)
        REFERENCES gayatri.delivery_person (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.customer
    OWNER to postgres;

-- Table: gayatri.payment

-- DROP TABLE gayatri.payment;

CREATE TABLE gayatri.payment
(
    id bigint NOT NULL,
    amount integer NOT NULL,
    date date NOT NULL,
    payment_method character varying(20) COLLATE pg_catalog."default",
    receiver character varying(50) COLLATE pg_catalog."default",
    cheque_payment_id bigint,
    customer_id bigint NOT NULL,
    CONSTRAINT payment_pkey PRIMARY KEY (id),
    CONSTRAINT fk2ib9ca13fydt7w2jodm4uuf6m FOREIGN KEY (cheque_payment_id)
        REFERENCES gayatri.cheque_payment (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkby2skjf3ov608yb6nm16b49lg FOREIGN KEY (customer_id)
        REFERENCES gayatri.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.payment
    OWNER to postgres;



-- Table: gayatri.product

-- DROP TABLE gayatri.product;

CREATE TABLE gayatri.purchase
(
    id bigint NOT NULL,
    price integer NOT NULL,
    qty   integer NOT NULL,
    ser_no character varying(1000) COLLATE pg_catalog."default",
    product_model_id bigint NOT NULL,
    customer_id bigint,
    CONSTRAINT purchase_pkey PRIMARY KEY (id),
    CONSTRAINT fk3ac4n7gvn3ej9hr6h8d8pkex7 FOREIGN KEY (product_model_id)
        REFERENCES gayatri.product_model (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION,
    CONSTRAINT fkj80n6400wnfqrt86qimf9k6ys FOREIGN KEY (customer_id)
        REFERENCES gayatri.customer (id) MATCH SIMPLE
        ON UPDATE NO ACTION
        ON DELETE NO ACTION
)
WITH (
    OIDS = FALSE
)
TABLESPACE pg_default;

ALTER TABLE gayatri.purchase
    OWNER to postgres;



-- Sequences


-- SEQUENCE: gayatri.brand_id_number_sequence

-- DROP SEQUENCE gayatri.brand_id_number_sequence;

CREATE SEQUENCE gayatri.brand_id_number_sequence;

ALTER SEQUENCE gayatri.brand_id_number_sequence
    OWNER TO postgres;


-- SEQUENCE: gayatri.cheque_id_number_sequence

-- DROP SEQUENCE gayatri.cheque_id_number_sequence;

CREATE SEQUENCE gayatri.cheque_id_number_sequence;

ALTER SEQUENCE gayatri.cheque_id_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.customer_id_number_sequence

-- DROP SEQUENCE gayatri.customer_id_number_sequence;

CREATE SEQUENCE gayatri.customer_id_number_sequence;

ALTER SEQUENCE gayatri.customer_id_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.delivery_number_sequence

-- DROP SEQUENCE gayatri.delivery_number_sequence;

CREATE SEQUENCE gayatri.delivery_number_sequence;

ALTER SEQUENCE gayatri.delivery_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.payment_id_number_sequence

-- DROP SEQUENCE gayatri.payment_id_number_sequence;

CREATE SEQUENCE gayatri.payment_id_number_sequence;

ALTER SEQUENCE gayatri.payment_id_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.product_number_sequence

-- DROP SEQUENCE gayatri.product_number_sequence;

CREATE SEQUENCE gayatri.product_number_sequence;

ALTER SEQUENCE gayatri.product_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.purchase_number_sequence

-- DROP SEQUENCE gayatri.purchase_number_sequence;

CREATE SEQUENCE gayatri.purchase_number_sequence;

ALTER SEQUENCE gayatri.purchase_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.product_model_number_sequence

-- DROP SEQUENCE gayatri.product_model_number_sequence;

CREATE SEQUENCE gayatri.product_model_number_sequence;

ALTER SEQUENCE gayatri.product_model_number_sequence
    OWNER TO postgres;

-- SEQUENCE: gayatri.user_id_number_sequence

-- DROP SEQUENCE gayatri.user_id_number_sequence;

CREATE SEQUENCE gayatri.user_id_number_sequence;

ALTER SEQUENCE gayatri.user_id_number_sequence
    OWNER TO postgres;





