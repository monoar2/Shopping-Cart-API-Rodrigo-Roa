
-- CREATE TABLE PRODUCT (id INT NOT NULL,  name VARCHAR(250) NOT NULL,  product_Category VARCHAR(20) NOT NULL,  tax_Category VARCHAR(20) NOT NULL, price DOUBLE);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (1, 'PIZZA','food', 'GNRL', 20.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (2, 'SKIRT','clothes', 'TIERED', 100.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (3, 'NINTENDO SWITCH','electronics', 'LUXURY', 600.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (4, 'PEN','misc', 'GNRL', 15000.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (5, 'ORANGE','food', 'GNRL', 10.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (6, 'TOFU','food', 'GNRL', 10.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (7, 'FISH','food', 'GNRL', 10.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (8, 'TACOS','food', 'GNRL', 10.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (9, 'TORTA','food', 'GNRL', 10.00);
INSERT INTO product (id, name, product_Category, tax_Category, price) VALUES (10,'BEER','food', 'GNRL', 10.00);
-- normally I would put the categories as tables but I want to finish this as fast and usefull as posible so they will be a enum in the configurations folder
