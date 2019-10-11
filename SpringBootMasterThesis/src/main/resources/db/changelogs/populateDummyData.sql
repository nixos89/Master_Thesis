-- inserting authors
INSERT INTO author(author_id, first_name, last_name) VALUES(1, "Agatha", "Christie");
INSERT INTO author(author_id, first_name, last_name) VALUES(2, "Stephen", "King");
INSERT INTO author(author_id, first_name, last_name) VALUES(3, "JRR", "Tolkien");
INSERT INTO author(author_id, first_name, last_name) VALUES(4, "JK", "Rowling");

-- Inserting books
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(1, 33, 0, "Harry Potter and the Philosopher's Stone", 29.99);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(2, 151, 0, "Murder on the Orient Express", 19.98); 
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(3, 5, 0, "The murder of Roger Ackroyd", 20.98); 
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(4, 7, 0, "Harry Potter and the Chamber of Secrets", 15.50);
INSERT INTO book(book_id, amount, is_deleted, name, price) VALUES(5, 85, 0, "Harry Potter and the Prisoner of Azkaban", 14.99);

-- connecting author and book
INSERT INTO book_author(author_id, book_id) VALUES(1, 2);
INSERT INTO book_author(author_id, book_id) VALUES(1, 3);
INSERT INTO book_author(author_id, book_id) VALUES(4, 1);
INSERT INTO book_author(author_id, book_id) VALUES(4, 5);
INSERT INTO book_author(author_id, book_id) VALUES(4, 4);

-- inserting categories
INSERT INTO category(category_id, name, is_deleted) VALUES(1, "Motivation", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(2, "Lyric", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(3, "Tragedy", 0);
INSERT INTO category(category_id, name, is_deleted) VALUES(4, "Comedy", 0);

-- connecting category and book
INSERT INTO book_category(category_id, book_id) VALUES(2, 1);
INSERT INTO book_category(category_id, book_id) VALUES(1, 4);
INSERT INTO book_category(category_id, book_id) VALUES(4, 4);
INSERT INTO book_category(category_id, book_id) VALUES(3, 5);

-- inserting roles
INSERT INTO `role` VALUES (1,'USER'),(2,'ADMIN');

-- inserting users
INSERT INTO `user` 
VALUES (1,'test','test','test','test@test.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',1),
(2,'admin','admin','admin','admin@admin.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',2);

-- inserting orders
INSERT INTO orders(order_id, total, order_date, user_id) 
VALUES (1,29.99,'2019-08-22 09:32:12',1),(2,29.99,'2019-08-22 09:45:25',1),
(3,29.99,'2019-08-22 09:52:26',1),(4,29.99,'2019-08-22 09:54:33',1),
(5,19.98,'2019-08-22 09:54:36',1),(6,29.99,'2019-08-22 09:58:52',1),
(7,29.99,'2019-08-22 10:14:11',1),(8,411.87,'2019-08-22 10:26:57',1),
(9,504.08,'2019-08-22 10:27:22',1);

