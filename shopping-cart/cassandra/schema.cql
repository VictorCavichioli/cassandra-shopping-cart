CREATE KEYSPACE shoppingcart WITH replication = {'class':'SimpleStrategy', 'replication_factor': 3};

CREATE TYPE product_offering_type (
  id UUID,
  name TEXT,
  price DOUBLE,
  image BLOB
);

CREATE TYPE address_type (
  id UUID,
  street TEXT,
  num INT,
  zip_code TEXT,
  state TEXT,
  country TEXT
);

CREATE TYPE customer_type (
  cus_id UUID,
  use_id UUID,
  cus_name TEXT,
)

CREATE TYPE order_type (
  ord_id UUID,
  ord_products SET<FROZEN<product_offering_type>>,
  ord_address FROZEN<address_type>,
  ord_final_price DOUBLE,
)

CREATE TABLE user_by_email (
  use_id UUID,
  use_email TEXT,
  use_pass TEXT,
  use_authority TEXT,
  PRIMARY KEY ((use_authority), use_email, use_id)
);

CREATE TABLE customer_by_user (
  cus_id UUID,
  use_id UUID,
  cus_name TEXT,
  cus_document_number TEXT,
  cus_type TEXT,
  cus_credit_score TEXT,
  cus_addresses SET<FROZEN<address_type>>,
  cus_orders SET<FROZEN<order_type>>,
  PRIMARY KEY ((cus_type), use_id, cus_id)
);

CREATE TABLE address_by_customer (
  cus_id UUID,
  add_cus_id UUID,
  add_cus_address TEXT,
  PRIMARY KEY ((cus_id) add_id)
)

CREATE TABLE address_by_id (
  add_id UUID,
  add_street TEXT,
  add_number INT,
  add_zip_code TEXT,
  add_state TEXT,
  add_country TEXT,
  PRIMARY KEY ((add_state), add_id)
  WITH CLUSTERING ORDER BY(add_zip_code)
)

CREATE TABLE product_offering (
  pro_id UUID,
  pro_name TEXT,
  pro_price FLOAT,
  pro_image BLOB,
  pro_category TEXT,
  PRIMARY KEY ((pro_category), pro_id)
  WITH CLUSTERING ORDER BY(pro_name)
)

CREATE TABLE product_offering_by_id (
  pro_id UUID,
  pro_name TEXT,
  pro_price FLOAT,
  pro_discount FLOAT,
  pro_validate DATE,
  pro_description TEXT
  pro_image SET<BLOB>,
  pro_category TEXT,
  PRIMARY KEY ((pro_category), pro_id)
  WITH CLUSTERING ORDER BY(pro_validate)
);

CREATE TABLE order_by_user (
  use_id UUID,
  ord_id UUID,
  ord_instant_date DATE,
  ord_products SET<FROZEN<product_offering_type>>,
  ord_quantity INT,
  ord_cus FROZEN<customer_type>,
  ord_address FROZEN<address_type>,
  ord_final_price DOUBLE,
  PRIMARY KEY ((use_id), ord_id)
);