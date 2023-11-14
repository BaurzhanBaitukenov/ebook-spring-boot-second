-- Table: public.users

-- DROP TABLE IF EXISTS public.users;

CREATE TABLE IF NOT EXISTS public.users
(
    id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
    email character varying(255) COLLATE pg_catalog."default",
    password character varying(255) COLLATE pg_catalog."default",
    username character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT users_pkey PRIMARY KEY (id),
    CONSTRAINT users_email UNIQUE (email)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.users
    OWNER to postgres;


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

ALTER TABLE IF EXISTS public.user_roles
    OWNER to postgres;


-- Table: public.roles

-- DROP TABLE IF EXISTS public.roles;

CREATE TABLE IF NOT EXISTS public.roles
(
    id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
    name character varying(255) COLLATE pg_catalog."default",
    CONSTRAINT roles_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.roles
    OWNER to postgres;


-- Table: public.books

-- DROP TABLE IF EXISTS public.books;

CREATE TABLE IF NOT EXISTS public.books
(
    id bigint NOT NULL DEFAULT nextval('books_id_seq'::regclass),
    author character varying(255) COLLATE pg_catalog."default",
    content character varying(5000) COLLATE pg_catalog."default",
    price integer,
    created_on timestamp(6) without time zone,
    photo_url character varying(255) COLLATE pg_catalog."default",
    title character varying(255) COLLATE pg_catalog."default",
    updated_on timestamp(6) without time zone,
    demo_version character varying(50000) COLLATE pg_catalog."default",
    CONSTRAINT books_pkey PRIMARY KEY (id)
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.books
    OWNER to postgres;


-- Table: public.shopping_cart

-- DROP TABLE IF EXISTS public.shopping_cart;

CREATE TABLE IF NOT EXISTS public.shopping_cart
(
    id bigint NOT NULL DEFAULT nextval('shopping_cart_id_seq'::regclass),
    user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
    CONSTRAINT shopping_cart_pk PRIMARY KEY (id),
    CONSTRAINT shopping_cart_users_fk FOREIGN KEY (user_email)
    REFERENCES public.users (email) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.shopping_cart
    OWNER to postgres;



-- Table: public.book_to_shopping_cart

-- DROP TABLE IF EXISTS public.book_to_shopping_cart;

CREATE TABLE IF NOT EXISTS public.book_to_shopping_cart
(
    id bigint NOT NULL DEFAULT nextval('book_to_shopping_cart_id_seq'::regclass),
    shopping_cart_id bigint NOT NULL,
    book_id bigint NOT NULL,
    amount bigint NOT NULL,
    CONSTRAINT book_to_shopping_cart_pk PRIMARY KEY (id),
    CONSTRAINT book_to_shopping_cart_book_fk FOREIGN KEY (book_id)
    REFERENCES public.books (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION,
    CONSTRAINT book_to_shopping_cart_shopping_cart_fk FOREIGN KEY (shopping_cart_id)
    REFERENCES public.shopping_cart (id) MATCH SIMPLE
    ON UPDATE NO ACTION
    ON DELETE NO ACTION
    )

    TABLESPACE pg_default;

ALTER TABLE IF EXISTS public.book_to_shopping_cart
    OWNER to postgres;



-- Table for comments
CREATE TABLE IF NOT EXISTS public.comments (
                                               id SERIAL PRIMARY KEY,
                                               content VARCHAR(500) NOT NULL,
    created_at TIMESTAMP WITHOUT TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    user_email VARCHAR(255) NOT NULL,
    likes INTEGER DEFAULT 0
    );


CREATE TABLE comment_likes (
                               comment_id BIGINT,
                               user_id BIGINT,
                               PRIMARY KEY (comment_id, user_id),
                               FOREIGN KEY (comment_id) REFERENCES comments(id),
                               FOREIGN KEY (user_id) REFERENCES users(id)
);









-- Create the sequence
-- CREATE SEQUENCE IF NOT EXISTS comments_id_seq
--     START WITH 1
--     INCREMENT BY 1
--     NO MINVALUE
--     NO MAXVALUE
--     CACHE 1;
--
-- -- Create the comments table
-- CREATE TABLE IF NOT EXISTS public.comments
-- (
--     id bigint NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
--     book_id bigint NOT NULL,
--     user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     content character varying(500) COLLATE pg_catalog."default" NOT NULL,
--     created_at timestamp(6) without time zone,
--     likes integer,
--     CONSTRAINT comments_pk PRIMARY KEY (id),
--     CONSTRAINT books_comments_fk FOREIGN KEY (book_id)
--     REFERENCES public.books (id) MATCH SIMPLE
--                             ON UPDATE NO ACTION
--                             ON DELETE NO ACTION
--     );
--
-- -- Set the table owner
-- ALTER TABLE IF EXISTS public.comments
--     OWNER to postgres;
--
-- -- Create the comment_likes table
-- CREATE TABLE IF NOT EXISTS comment_likes (
--                                              id SERIAL PRIMARY KEY,
--                                              comment_id BIGINT REFERENCES public.comments(id),
--     user_id BIGINT REFERENCES public.users(id)
--     );
--
-- -- Add a foreign key constraint with CASCADE on delete
-- ALTER TABLE public.comment_likes
--     ADD CONSTRAINT comment_likes_comments_fk
--         FOREIGN KEY (comment_id)
--             REFERENCES public.comments (id)
--             ON UPDATE NO ACTION
--             ON DELETE CASCADE;






-- -- Table: public.users
--
-- -- DROP TABLE IF EXISTS public.users;
--
-- CREATE TABLE IF NOT EXISTS public.users
-- (
--     id bigint NOT NULL DEFAULT nextval('users_id_seq'::regclass),
--     email character varying(255) COLLATE pg_catalog."default",
--     password character varying(255) COLLATE pg_catalog."default",
--     username character varying(255) COLLATE pg_catalog."default",
--     CONSTRAINT users_pkey PRIMARY KEY (id),
--     CONSTRAINT users_email UNIQUE (email)
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.users
--     OWNER to postgres;
--
--
-- -- Table: public.user_roles
--
-- -- DROP TABLE IF EXISTS public.user_roles;
--
-- CREATE TABLE IF NOT EXISTS public.user_roles
-- (
--     user_id bigint NOT NULL,
--     role_id bigint NOT NULL,
--     CONSTRAINT fkh8ciramu9cc9q3qcqiv4ue8a6 FOREIGN KEY (role_id)
--     REFERENCES public.roles (id) MATCH SIMPLE
--     ON UPDATE NO ACTION
--     ON DELETE NO ACTION,
--     CONSTRAINT fkhfh9dx7w3ubf1co1vdev94g3f FOREIGN KEY (user_id)
--     REFERENCES public.users (id) MATCH SIMPLE
--     ON UPDATE NO ACTION
--     ON DELETE NO ACTION
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.user_roles
--     OWNER to postgres;
--
--
-- -- Table: public.roles
--
-- -- DROP TABLE IF EXISTS public.roles;
--
-- CREATE TABLE IF NOT EXISTS public.roles
-- (
--     id bigint NOT NULL DEFAULT nextval('roles_id_seq'::regclass),
--     name character varying(255) COLLATE pg_catalog."default",
--     CONSTRAINT roles_pkey PRIMARY KEY (id)
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.roles
--     OWNER to postgres;
--
--
-- -- Table: public.books
--
-- -- DROP TABLE IF EXISTS public.books;
--
-- CREATE TABLE IF NOT EXISTS public.books
-- (
--     id bigint NOT NULL DEFAULT nextval('books_id_seq'::regclass),
--     author character varying(255) COLLATE pg_catalog."default",
--     content character varying(5000) COLLATE pg_catalog."default",
--     price integer,
--     created_on timestamp(6) without time zone,
--     photo_url character varying(255) COLLATE pg_catalog."default",
--     title character varying(255) COLLATE pg_catalog."default",
--     updated_on timestamp(6) without time zone,
--     demo_version character varying(50000) COLLATE pg_catalog."default",
--     CONSTRAINT books_pkey PRIMARY KEY (id)
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.books
--     OWNER to postgres;
--
--
-- -- Table: public.shopping_cart
--
-- -- DROP TABLE IF EXISTS public.shopping_cart;
--
-- CREATE TABLE IF NOT EXISTS public.shopping_cart
-- (
--     id bigint NOT NULL DEFAULT nextval('shopping_cart_id_seq'::regclass),
--     user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     CONSTRAINT shopping_cart_pk PRIMARY KEY (id),
--     CONSTRAINT shopping_cart_users_fk FOREIGN KEY (user_email)
--     REFERENCES public.users (email) MATCH SIMPLE
--     ON UPDATE NO ACTION
--     ON DELETE NO ACTION
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.shopping_cart
--     OWNER to postgres;
--
--
--
-- -- Table: public.book_to_shopping_cart
--
-- -- DROP TABLE IF EXISTS public.book_to_shopping_cart;
--
-- CREATE TABLE IF NOT EXISTS public.book_to_shopping_cart
-- (
--     id bigint NOT NULL DEFAULT nextval('book_to_shopping_cart_id_seq'::regclass),
--     shopping_cart_id bigint NOT NULL,
--     book_id bigint NOT NULL,
--     amount bigint NOT NULL,
--     CONSTRAINT book_to_shopping_cart_pk PRIMARY KEY (id),
--     CONSTRAINT book_to_shopping_cart_book_fk FOREIGN KEY (book_id)
--     REFERENCES public.books (id) MATCH SIMPLE
--     ON UPDATE NO ACTION
--     ON DELETE NO ACTION,
--     CONSTRAINT book_to_shopping_cart_shopping_cart_fk FOREIGN KEY (shopping_cart_id)
--     REFERENCES public.shopping_cart (id) MATCH SIMPLE
--     ON UPDATE NO ACTION
--     ON DELETE NO ACTION
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.book_to_shopping_cart
--     OWNER to postgres;
--
--
--
-- -- Table: public.comments
--
-- -- DROP TABLE IF EXISTS public.comments;
--
-- CREATE TABLE IF NOT EXISTS public.comments
-- (
--     id bigint NOT NULL DEFAULT nextval('comments_id_seq'::regclass),
--     book_id bigint NOT NULL,
--     user_email character varying(255) COLLATE pg_catalog."default" NOT NULL,
--     content character varying(500) COLLATE pg_catalog."default" NOT NULL,
--     created_at timestamp(6) without time zone,
--     CONSTRAINT comments_pk PRIMARY KEY (id),
--     CONSTRAINT books_comments_fk FOREIGN KEY (book_id)
--     REFERENCES public.books (id) MATCH SIMPLE
--                             ON UPDATE NO ACTION
--                             ON DELETE NO ACTION
--     )
--
--     TABLESPACE pg_default;
--
-- ALTER TABLE IF EXISTS public.comments
--     OWNER to postgres;