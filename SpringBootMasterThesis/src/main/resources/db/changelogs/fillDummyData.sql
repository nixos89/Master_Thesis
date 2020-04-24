INSERT INTO author(author_id, first_name, last_name) VALUES(1, 'Agatha', 'Christie');
INSERT INTO author(author_id, first_name, last_name) VALUES(2, 'Stephen', 'King');
INSERT INTO author(author_id, first_name, last_name) VALUES(3, 'JRR', 'Tolkien');
INSERT INTO author(author_id, first_name, last_name) VALUES(4, 'JK', 'Rowling');

INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(1, 33, false, 'Harry Potter and the Philosopher''s Stone', 29.99);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(2, 151, false, 'Murder on the Orient Express', 19.98); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(3, 5, false, 'The murder of Roger Ackroyd', 20.98); 
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(4, 7, false, 'Harry Potter and the Chamber of Secrets', 15.50);
INSERT INTO book(book_id, amount, is_deleted, title, price) VALUES(5, 85, false, 'Harry Potter and the Prisoner of Azkaban', 14.99);

-- connecting author and book
INSERT INTO book_author(author_id, book_id) VALUES(1, 2);
INSERT INTO book_author(author_id, book_id) VALUES(1, 3);
INSERT INTO book_author(author_id, book_id) VALUES(4, 1);
INSERT INTO book_author(author_id, book_id) VALUES(4, 5);
INSERT INTO book_author(author_id, book_id) VALUES(4, 4);

-- inserting categories
INSERT INTO category(category_id, name, is_deleted) VALUES(1, 'Motivation', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(2, 'Lyric', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(3, 'Tragedy', false);
INSERT INTO category(category_id, name, is_deleted) VALUES(4, 'Comedy', false);

-- connecting category and book
INSERT INTO book_category(category_id, book_id) VALUES(2, 1);
INSERT INTO book_category(category_id, book_id) VALUES(1, 4);
INSERT INTO book_category(category_id, book_id) VALUES(4, 4);
INSERT INTO book_category(category_id, book_id) VALUES(3, 5);

-- inserting roles
INSERT INTO roles VALUES (1,'USER'),(2,'ADMIN');

-- inserting users
INSERT INTO users 
VALUES (1,'test','test','test','test@test.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',1),
(2,'admin','admin','admin','admin@admin.com','{bcrypt}$2a$10$livl.GRKgXNnUxCg6z3bhu0Xj.KeGnINWRhqYFvgaVnFkEBKUMXB.',2);