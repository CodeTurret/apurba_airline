-- Insert initial routes
INSERT INTO route (origin, destination) VALUES ('NYC', 'LAX');
INSERT INTO route (origin, destination) VALUES ('SFO', 'ORD');
INSERT INTO route (origin, destination) VALUES ('MIA', 'ATL');

-- Insert initial flights
INSERT INTO flight (route_id, is_domestic) VALUES (1, true);
INSERT INTO flight (route_id, is_domestic) VALUES (2, true);
INSERT INTO flight (route_id, is_domestic) VALUES (3, false);

-- Insert initial tickets
INSERT INTO ticket (price, sale_date, flight_id) VALUES (100.0, '2024-07-30', 1);
INSERT INTO ticket (price, sale_date, flight_id) VALUES (150.0, '2024-07-30', 1);
INSERT INTO ticket (price, sale_date, flight_id) VALUES (200.0, '2024-07-30', 2);
INSERT INTO ticket (price, sale_date, flight_id) VALUES (120.0, '2024-07-29', 2);
INSERT INTO ticket (price, sale_date, flight_id) VALUES (180.0, '2024-07-28', 3);

-- Insert initial foods
INSERT INTO food (price, sale_date) VALUES (50.0, '2024-07-30');
INSERT INTO food (price, sale_date) VALUES (30.0, '2024-07-30');
INSERT INTO food (price, sale_date) VALUES (20.0, '2024-07-29');
INSERT INTO food (price, sale_date) VALUES (25.0, '2024-07-28');
