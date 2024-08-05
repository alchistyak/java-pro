CREATE TABLE public.flw_users (
                              id bigserial NOT NULL,
                              username varchar(255) NULL,
                              CONSTRAINT users_pk PRIMARY KEY (id),
                              CONSTRAINT users_un UNIQUE (username)
);

CREATE TABLE public.flw_products (
                                 id bigserial NOT NULL,
                                 account varchar(255) NULL,
                                 balance numeric(38, 2) NULL,
                                 "type" varchar(255) NULL,
                                 userid bigserial NOT NULL,
                                 CONSTRAINT products_pk PRIMARY KEY (id)
);

ALTER TABLE public.flw_products ADD CONSTRAINT flw_users_fk FOREIGN KEY (userid) REFERENCES public.flw_users(id);

INSERT INTO public.flw_users (username) VALUES ('User1'), ('User2'), ('User3');

INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000101', 99.99, 'Счет', 1);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000102', 199.99, 'Карта', 1);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000103', 299.99, 'Счет', 1);

INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000201', 201, 'Счет', 2);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000202', 301, 'Карта', 2);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000203', 401, 'Счет', 2);

INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000301', 1000.99, 'Счет', 3);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000302', 2000.99, 'Карта', 3);
INSERT INTO public.flw_products (account, balance, type, userid) VALUES ('40702810800000000303', 3000.99, 'Счет', 3);