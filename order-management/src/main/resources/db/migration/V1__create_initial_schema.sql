
CREATE TABLE orders(
  order_id bigint(20) NOT NULL,
  customer_id bigint(20) NOT NULL,
  payment_channel varchar(100),
  is_cod boolean,
  order_status varchar(100),
  order_created_on Date,
  total_amount bigint(20),
  shipping_address varchar(100),
  order_Line_Id bigint(20),
  PRIMARY KEY (order_id)
   ); 


CREATE TABLE order_line_it (
  order_Line_Id bigint(20) NOT NULL,
  sku_Id bigint(20) NOT NULL,
  order_id bigint(20),
  item_qty int,
  PRIMARY KEY (order_Line_Id),
  FOREIGN KEY (order_id) REFERENCES orders(order_id)
  );