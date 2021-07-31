DROP TABLE IF EXISTS currency_exchange;

CREATE TABLE currency_exchange (
  id BIGINT   PRIMARY KEY,
  currency_from VARCHAR(250),
  currency_to VARCHAR(250),
  conversion_multiple DECIMAL,
  environment VARCHAR(250)
);



insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10001,'USD','INR',65,'');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10002,'EUR','INR',75,'');
insert into currency_exchange
(id,currency_from,currency_to,conversion_multiple,environment)
values(10003,'AUD','INR',25,'');