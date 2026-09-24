-- =========================
-- ORDERS
-- =========================

INSERT INTO orders (id, order_status, price)
VALUES
    (1, 'PENDINGORDER_STATUS', 2499.00),
    (2, 'CONFIRUMEDORDER_STATUS', 5499.00),
    (3, 'ONTHE_WAY', 3299.00),
    (4, 'DELIVEREDORDER_STATUS', 7999.00),
    (5, 'CANCLEDORDER_STATUS', 1499.00);


-- =========================
-- ORDER ITEMS
-- =========================

INSERT INTO order_items (id, product_id, quantity, order_id)
VALUES
    -- Order 1
    (101, 1001, 2, 1),
    (102, 1002, 1, 1),

    -- Order 2
    (103, 1003, 1, 2),
    (104, 1004, 2, 2),

    -- Order 3
    (105, 1005, 1, 3),
    (106, 1006, 1, 3),

    -- Order 4
    (107, 1007, 2, 4),
    (108, 1008, 1, 4),

    -- Order 5
    (109, 1009, 1, 5);