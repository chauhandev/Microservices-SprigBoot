CREATE TABLE t_inventory (
    id BIGINT PRIMARY KEY IDENTITY(1,1),
    skuCode VARCHAR(255) Default NULL,
    quantity INT Default NULL
);
