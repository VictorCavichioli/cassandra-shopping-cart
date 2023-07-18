CREATE KEYSPACE mykeyspace WITH replication = {'class':'SimpleStrategy', 'replication_factor': 1};

CREATE TYPE address_type (
  id UUID,
  street TEXT,
  addressNumber INT,
  zipCode TEXT,
  country TEXT
);

CREATE TYPE role_type (
  id UUID,
  role TEXT
)

CREATE TYPE product_offering_type (
  id UUID,
  name TEXT,
  price DOUBLE
);

CREATE TABLE customer (
  cus_id UUID PRIMARY KEY,
  cus_name TEXT,
  cus_document_number TEXT,
  cus_status TEXT,
  cus_credit_score TEXT,
  cus_addresses SET<frozen<address_type>>,
  cus_orders LIST<UUID>
);

CREATE TABLE order (
  ord_id UUID PRIMARY KEY,
  ord_instant_date DATE,
  ord_discount DOUBLE,
  ord_products SET<frozen<product_offering_type>>,
  ord_quantity INT,
  ord_total_price DOUBLE,
  ord_cus_id UUID,
  ord_cus_name TEXT,
  ord_add_street TEXT,
  ord_add_number INT,
  ord_add_zipcode TEXT,
  ord_add_country TEXT
);

CREATE TABLE product_offering (
  pro_id UUID PRIMARY KEY,
  pro_name TEXT,
  pro_price DOUBLE,
  pro_validate DATE
);

CREATE TABLE role (
  rol_id UUID PRIMARY KEY,
  rol_role TEXT,
  UNIQUE (rol_role)
)

CREATE TABLE user (
  use_id UUID PRIMARY KEY,
  cus_id UUID,
  use_email TEXT,
  use_pass TEXT,
  use_authority SET<frozen<role_type>>,
  UNIQUE (cus_id, use_email)
);
