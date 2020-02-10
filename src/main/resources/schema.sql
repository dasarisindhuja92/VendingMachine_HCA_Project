CREATE TABLE Product(
  Product_ID BIGINT AUTO_INCREMENT PRIMARY KEY,
  NAME VARCHAR(16) NOT NULL UNIQUE,
);

CREATE TABLE inventory
(
   id BIGINT AUTO_INCREMENT PRIMARY KEY,
   product_ID BIGINT UNIQUE,
   quantity INT,
   price DECIMAL(18,2)
);

CREATE TABLE Order_history
(
    Oder_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    product_ID BIGINT,
    quantity BIGINT,
    price decimal(18,2),
    order_date DATE
);


ALTER TABLE inventory ADD FOREIGN KEY (product_ID) REFERENCES Product(Product_ID);
ALTER TABLE Order_history ADD FOREIGN KEY (product_ID) REFERENCES Product(Product_ID);
CREATE INDEX IDX_order_DATE_ ON Order_history(order_date);
