-- inserting Authors
INSERT INTO author(author_id, first_name, last_name) VALUES(1, 'Agatha', 'Christie');
INSERT INTO author(author_id, first_name, last_name) VALUES(2, 'Stephen', 'King');
INSERT INTO author(author_id, first_name, last_name) VALUES(3, 'JRR', 'Tolkien');
INSERT INTO author(author_id, first_name, last_name) VALUES(4, 'JK', 'Rowling');
INSERT INTO author(author_id, first_name, last_name) VALUES(5, 'Leo', 'Tolstoy');
INSERT INTO author(author_id, first_name, last_name) VALUES(6, 'Franz', 'Kafka');
INSERT INTO author(author_id, first_name, last_name) VALUES(7, 'Alexander', 'Pushkin');
INSERT INTO author(author_id, first_name, last_name) VALUES(8, 'William', ' Shakespeare');
INSERT INTO author(author_id, first_name, last_name) VALUES(9, 'Dan', 'Brown');

-- inserting Books
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(1, 33, false, 'Harry Potter and the Philosopher''s Stone', 29.99);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(2, 151, false, 'Murder on the Orient Express', 19.98); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(3, 5, false, 'The murder of Roger Ackroyd', 20.98); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(4, 7, false, 'Harry Potter and the Chamber of Secrets', 15.50);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(5, 85, false, 'Harry Potter and the Prisoner of Azkaban', 14.99);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(6, 15, false, 'The Da Vinci Code', 29.98);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(7, 140, false, 'Inferno', 19.50); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(8, 85, false, 'The Lord of the Rings', 23.50); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(9, 110, false, 'The Hobbit or There and Back Again', 19.00);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(10, 43, false, 'Anna Karenina', 17.18); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(11, 11, false, 'War and Peace', 16.52); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(12, 23, false, 'The Death of Ivan Ilych', 26.33);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(13, 54, false, 'Carrie', 19.17); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(14, 68, false, 'The Trial', 13.46);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(15, 80, false, 'IT', 10.74);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(16, 123, false, 'Boris Godunov', 23.82);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(17, 140, false, 'Hamlet', 22.61);


-- inserting Categories
INSERT INTO category(category_id, name, is_deleted) VALUES(1, 'Motivation', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(2, 'Lyric', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(3, 'Tragedy', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(4, 'Comedy', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(5, 'Horror', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(6, 'Romance', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(7, 'Drama', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(8, 'Poetry', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(9, 'Crime', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(10, 'Action', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(11, 'Fantasy', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(12, 'Philosphy', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(13, 'History', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(14, 'Cooking', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(15, 'Psyhology', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(16, 'Mithology', false);


-- connecting author and book
INSERT INTO author_book(author_id, book_id) VALUES(1, 2);
INSERT INTO author_book(author_id, book_id) VALUES(1, 3);
INSERT INTO author_book(author_id, book_id) VALUES(4, 1);
INSERT INTO author_book(author_id, book_id) VALUES(4, 5);
INSERT INTO author_book(author_id, book_id) VALUES(4, 4);
INSERT INTO author_book(author_id, book_id) VALUES(8, 17);
INSERT INTO author_book(author_id, book_id) VALUES(7, 16);
INSERT INTO author_book(author_id, book_id) VALUES(2, 15);
INSERT INTO author_book(author_id, book_id) VALUES(2, 13);
INSERT INTO author_book(author_id, book_id) VALUES(6, 14);
INSERT INTO author_book(author_id, book_id) VALUES(5, 12);
INSERT INTO author_book(author_id, book_id) VALUES(5, 11);
INSERT INTO author_book(author_id, book_id) VALUES(5, 10);
INSERT INTO author_book(author_id, book_id) VALUES(3, 9);
INSERT INTO author_book(author_id, book_id) VALUES(3, 8);
INSERT INTO author_book(author_id, book_id) VALUES(8, 7);
INSERT INTO author_book(author_id, book_id) VALUES(8, 6);


-- connecting category and book
INSERT INTO category_book(category_id, book_id) VALUES(2, 1);
INSERT INTO category_book(category_id, book_id) VALUES(1, 4);
INSERT INTO category_book(category_id, book_id) VALUES(4, 4);
INSERT INTO category_book(category_id, book_id) VALUES(3, 5);
INSERT INTO category_book(category_id, book_id) VALUES(9, 2);
INSERT INTO category_book(category_id, book_id) VALUES(9, 3);
INSERT INTO category_book(category_id, book_id) VALUES(2, 7);
INSERT INTO category_book(category_id, book_id) VALUES(10, 6);
INSERT INTO category_book(category_id, book_id) VALUES(9, 8);
INSERT INTO category_book(category_id, book_id) VALUES(14, 9);
INSERT INTO category_book(category_id, book_id) VALUES(5, 10);
INSERT INTO category_book(category_id, book_id) VALUES(8, 11);
INSERT INTO category_book(category_id, book_id) VALUES(11, 12);
INSERT INTO category_book(category_id, book_id) VALUES(5, 13);
INSERT INTO category_book(category_id, book_id) VALUES(9, 14);
INSERT INTO category_book(category_id, book_id) VALUES(5, 15);
INSERT INTO category_book(category_id, book_id) VALUES(5, 16);
INSERT INTO category_book(category_id, book_id) VALUES(7, 17);


-- inserting roles
INSERT INTO roles VALUES (1,'USER'),(2,'ADMIN');

-- inserting users
INSERT INTO users 
VALUES (1,'test','test','test','test@test.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',1),
(2,'admin','admin','admin','admin@admin.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',2);

INSERT INTO orders(order_id, total, order_date, user_id) VALUES (1,29.99,'2019-08-22 09:32:12',1),(2,29.99,'2019-08-22 09:45:25',1),(3,29.99,'2019-08-22 09:52:26',1),(4,29.99,'2019-08-22 09:54:33',1),(5,19.98,'2019-08-22 09:54:36',1),(6,29.99,'2019-08-22 09:58:52',1),(7,29.99,'2019-08-22 10:14:11',1),(8,411.87,'2019-08-22 10:26:57',1),(9,504.08000000000004,'2019-08-22 10:27:22',1);
INSERT INTO order_item(order_item_id, amount, book_id, order_id) VALUES (1,1,1,1),(2,1,1,2),(3,1,1,3),(4,1,1,4),(5,1,2,5),(6,1,1,6),(7,1,1,7),(8,5,7,8),(9,4,9,8),(10,1,8,8),(11,4,2,8),(12,1,5,8),(13,4,1,8),(14,4,10,9),(15,7,1,9),(16,6,8,9),(17,2,14,9),(18,3,13,9);

-- ALTERING sequences so there is no collision with IDs when performing NEW INSERT statements
alter sequence author_author_id_seq restart with 10;
alter sequence book_book_id_seq restart with 18;
alter sequence category_category_id_seq restart with 17;
alter sequence order_item_order_item_id_seq restart with 19;
alter sequence orders_order_id_seq restart with 10;
alter sequence roles_role_id_seq restart with 3;
alter sequence users_user_id_seq restart with 3;