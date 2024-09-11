INSERT INTO finance.category (id, name) VALUES (1, 'Food') ON CONFLICT (id) DO NOTHING;
INSERT INTO finance.category (id, name) VALUES (2, 'Car') ON CONFLICT (id) DO NOTHING;
INSERT INTO finance.category (id, name) VALUES (3, 'House') ON CONFLICT (id) DO NOTHING;
INSERT INTO finance.category (id, name) VALUES (4, 'Loan') ON CONFLICT (id) DO NOTHING;
INSERT INTO finance.category (id, name) VALUES (5, 'Other') ON CONFLICT (id) DO NOTHING;