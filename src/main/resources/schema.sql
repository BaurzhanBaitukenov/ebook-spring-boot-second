CREATE TABLE IF NOT EXISTS public.users
(
    id BIGSERIAL NOT NULL,
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;


CREATE TABLE IF NOT EXISTS public.roles
(
    id BIGSERIAL NOT NULL,
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;


-- user_roles
-- Table: public.user_roles

-- DROP TABLE IF EXISTS public.user_roles;

CREATE TABLE IF NOT EXISTS public.user_roles
(
    user_id bigint NOT NULL,
    role_id bigint NOT NULL,
    CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id)
    REFERENCES public.roles (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;



-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id BIGSERIAL NOT NULL,
    author character varying(255) COLLATE pg_catalog."default",
    content character varying(5000) COLLATE pg_catalog."default",
    created_on timestamp(6) without time zone,
    photo_url character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp(6) without time zone,
    CONSTRAINT books_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;



-- CREATE TABLE IF NOT EXISTS public.shopping_cart
-- (
--     id BIGSERIAL NOT NULL,
--     user_id BIGINT NOT NULL,
--     CONSTRAINT shopping_cart_pk PRIMARY KEY (id),
--     CONSTRAINT shopping_cart_users_fk FOREIGN KEY (user_id)
--     REFERENCES public.users (id) MATCH SIMPLE
--     );

CREATE TABLE IF NOT EXISTS public.shopping_cart
(
    id BIGSERIAL NOT NULL,
    user_email varchar(255) NOT NULL,
    CONSTRAINT shopping_cart_pk PRIMARY KEY (id),
    CONSTRAINT shopping_cart_users_fk FOREIGN KEY (user_email)
    REFERENCES public.users (email) MATCH SIMPLE
    );


CREATE TABLE IF NOT EXISTS public.book_to_shopping_cart
(
    id BIGSERIAL NOT NULL,
    shopping_cart_id BIGINT NOT NULL,
    book_id BIGINT NOT NULL,
    amount BIGINT NOT NULL,
    CONSTRAINT book_to_shopping_cart_pk PRIMARY KEY (id),

    CONSTRAINT book_to_shopping_cart_shopping_cart_fk FOREIGN KEY (shopping_cart_id)
    REFERENCES public.shopping_cart (id) MATCH SIMPLE,

    CONSTRAINT book_to_shopping_cart_book_fk FOREIGN KEY (book_id)
    REFERENCES public.books (id) MATCH SIMPLE
    );


CREATE TABLE IF NOT EXISTS public.comments
(
    id BIGSERIAL NOT NULL,
    book_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    content varchar(500) NOT NULL,
    created_at timestamp(6) without time zone,

    CONSTRAINT comments_pk PRIMARY KEY (id),

    CONSTRAINT books_comments_fk FOREIGN KEY (book_id)
    REFERENCES public.books (id) MATCH SIMPLE,

    CONSTRAINT users_comments_fk FOREIGN KEY (user_id)
    REFERENCES public.users (id) MATCH SIMPLE
    );

