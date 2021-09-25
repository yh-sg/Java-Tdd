CREATE TABLE tbl_customer_contact(
	id INT NOT NULL AUTO_INCREMENT,
	firstName VARCHAR(50),
	lastName VARCHAR(50),
	email VARCHAR(50),
	deliveryAddressLine1 VARCHAR(50),
	deliveryAddressLine2 VARCHAR(50),
	deliveryAddressCity VARCHAR(50),
	deliveryAddressState VARCHAR(50),
	deliveryAddressZipCode VARCHAR(50),
	PRIMARY KEY (id)
);