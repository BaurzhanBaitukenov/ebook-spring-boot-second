INSERT INTO public.users(
    id, email, password, username)
VALUES (1, 'admin@gmail.com', '$2a$10$XZ4q1GroBGK9HvM5NfwbzeTy8oouGhsswAPlEcC5gkkrOX8IHVAFe', 'admin');


INSERT INTO public.books(
    id, author, content, created_on, photo_url, title, updated_on)
VALUES (1, 'Baurzhan Baitukenov', 'Hello Wolrd', '2023-05-31 03:08:12.885309', 'https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTz5qo34F55xyCh-Ay6A9O1hjo4t8Sxe_BpIw&usqp=CAU', 'titile', '2023-05-31 03:08:12.885309');


INSERT INTO public.shopping_cart(
    id, user_id)
VALUES (1, 1);


INSERT INTO public.book_to_shopping_cart(
    id, shopping_cart_id, book_id, amount)
VALUES (1, 1, 1, 2);


INSERT INTO public.roles(
    id, name)
VALUES (1, 'ADMIN');


INSERT INTO public.roles(
    id, name)
VALUES (2, 'USER');


INSERT INTO public.user_roles(
    user_id, role_id)
VALUES (1, 1);


INSERT INTO public.shopping_cart(
    id, user_id)
VALUES (1, 1);