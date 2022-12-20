DROP TABLE users IF EXISTS;
CREATE TABLE IF NOT EXISTS USERS1 (id bigserial, score int, name VARCHAR(255), PRIMARY KEY (id));
INSERT INTO USERS1 (name, score) VALUES ('Bob', 80), ('Jack', 80), ('John', 80);

CREATE TABLE IF NOT EXISTS consumers (id bigserial, name VARCHAR(255));
insert into consumers (name) VALUES ('Jon'), ('Robert');

CREATE TABLE IF NOT EXISTS products (id bigserial, title VARCHAR(255), cost int, PRIMARY KEY (id));
INSERT INTO products (title, cost) VALUES ('milk', 35), ('cheese', 50), ('sausage', 60), ('ketchup', 70), ('mayonnaise', 100);

CREATE TABLE IF NOT EXISTS  consumers_products  (product_id bigint, consumer_id bigint, FOREIGN KEY (product_id) REFERENCES products (id), FOREIGN KEY (consumer_id) REFERENCES consumers (id));
INSERT INTO consumers_products  ( consumer_id, product_id) VALUES (1 , 1), (1 , 2), (1 , 3),  (2 , 3),  (2 , 2);