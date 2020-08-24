DROP TABLE IF EXISTS `order_item`;
CREATE TABLE `order_item` (
  `product_code` bigint NOT NULL AUTO_INCREMENT,
  `product_name` varchar(255) DEFAULT NULL,
  `quantity` int NOT NULL,
  PRIMARY KEY (`product_code`)
);

DROP TABLE IF EXISTS `order_deatils`;
CREATE TABLE `order_deatils` (
  `order_id` bigint NOT NULL AUTO_INCREMENT,
  `customer_name` varchar(255) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `shipping_address` varchar(255) DEFAULT NULL,
  `total` int DEFAULT NULL,
  PRIMARY KEY (`order_id`)
);