use ismgroup58;

/*
drop table product; 
drop table user;
*/ 

CREATE TABLE user (
	name VARCHAR(50) NOT NULL,
	email VARCHAR(100) NOT NULL UNIQUE,
    ssn VARCHAR(9) UNIQUE,
    username VARCHAR(20) NOT NULL PRIMARY KEY,
    password VARCHAR(255) NOT NULL,
    joined date,
    image VARCHAR(100),
    phone VARCHAR(10),
	city VARCHAR(100),
	address VARCHAR(100),
	zip INT(5)
);
CREATE TABLE product (
    productID INT AUTO_INCREMENT PRIMARY KEY,
    productName VARCHAR(100),
    image VARCHAR(100),
    category VARCHAR(100),
    description VARCHAR(1000),
    price DOUBLE,
    stock INT,
    supplier VARCHAR(20), -- supplier of the product
	FOREIGN KEY (supplier) REFERENCES user(username) ON DELETE CASCADE
);
