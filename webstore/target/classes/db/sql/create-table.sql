DROP TABLE CART_ITEM IF EXISTS;

DROP TABLE CART IF EXISTS;

DROP TABLE PRODUCTS IF EXISTS;

DROP TABLE ORDERS IF EXISTS;
DROP TABLE CUSTOMERS IF EXISTS;
DROP TABLE SHIPPING_DETAIL IF EXISTS;
DROP TABLE ADDRESS IF EXISTS;

CREATE TABLE PRODUCTS (
  ID VARCHAR(25) PRIMARY KEY,
  NAME VARCHAR(50),
  DESCRIPTION  VARCHAR(250),
  UNIT_PRICE DECIMAL,
  MANUFACTURER VARCHAR(50),
  CATEGORY VARCHAR(50),
  CONDITION VARCHAR(50),
  UNITS_IN_STOCK BIGINT,
  UNITS_IN_ORDER BIGINT,
  DISCONTINUED BOOLEAN
);


CREATE TABLE CART (
   ID VARCHAR(50) PRIMARY KEY
);

CREATE TABLE CART_ITEM (
   ID VARCHAR(75),
   PRODUCT_ID VARCHAR(25) FOREIGN KEY REFERENCES PRODUCTS(ID), 
   CART_ID VARCHAR(50) FOREIGN KEY REFERENCES CART(ID),
   QUANTITY BIGINT,
   CONSTRAINT CART_ITEM_PK PRIMARY KEY (ID,CART_ID) 
);


CREATE TABLE ADDRESS (
ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 101, INCREMENT BY 1) PRIMARY KEY,
DOOR_NO VARCHAR(25),
STREET_NAME VARCHAR(25),
AREA_NAME VARCHAR(25),
STATE VARCHAR(25),
COUNTRY VARCHAR(25),
ZIP VARCHAR(25),

);

CREATE TABLE CUSTOMERS (
CUSTOMER_ID BIGINT GENERATED BY DEFAULT AS IDENTITY(START WITH 201, INCREMENT BY 1) PRIMARY KEY,
NAME VARCHAR(25),
PHONE_NUMBER VARCHAR(25),
BILLING_ADDRESS_ID INTEGER FOREIGN KEY REFERENCES ADDRESS(ID),
);

CREATE TABLE SHIPPING_DETAIL (
ID INTEGER IDENTITY PRIMARY KEY,
NAME VARCHAR(25),
SHIPPING_DATE VARCHAR(25),
SHIPPING_ADDRESS_ID INTEGER FOREIGN KEY REFERENCES ADDRESS(ID),
);

CREATE TABLE ORDERS (
ID INTEGER GENERATED BY DEFAULT AS IDENTITY(START WITH 1000, INCREMENT BY 1) PRIMARY KEY,
CART_ID VARCHAR(50) ,
CUSTOMER_ID BIGINT FOREIGN KEY REFERENCES CUSTOMERS(CUSTOMER_ID),
SHIPPING_DETAIL_ID INTEGER FOREIGN KEY REFERENCES SHIPPING_DETAIL(ID),
);