DROP DATABASE IF EXISTS transit_management;

CREATE DATABASE transit_management;

USE transit_management;

CREATE TABLE user (
    id INT PRIMARY KEY AUTO_INCREMENT,
    user_name VARCHAR(50),
    email VARCHAR(100),
    password VARCHAR(255),
    role_type TINYINT(2)
);

CREATE TABLE route (
    id INT PRIMARY KEY AUTO_INCREMENT,
    route_name VARCHAR(100),
    expected_total_miles DECIMAL(10,2)
);

CREATE TABLE vehicle (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_type VARCHAR(50),
    fuel_type VARCHAR(50),
    fuel_consumption_rate DECIMAL(10,2),
    max_passengers INT,
    current_assigned_route_id INT,
    real_total_miles DECIMAL(10,2),
    real_total_consumption DECIMAL(10,2),
    maintain_gap_miles DECIMAL(10,2),
    miles_from_last_maintenance DECIMAL(10,2),
    FOREIGN KEY (current_assigned_route_id) REFERENCES route(id)
);

CREATE TABLE station (
    id INT PRIMARY KEY AUTO_INCREMENT,
    station_name VARCHAR(100)
);

CREATE TABLE route_station (
    id INT PRIMARY KEY AUTO_INCREMENT,
    route_id INT,
    station_id INT,
    station_order INT,
    FOREIGN KEY (route_id) REFERENCES route(id),
    FOREIGN KEY (station_id) REFERENCES station(id)
);

CREATE TABLE departure_schedule (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    route_id INT,
    expected_depart_time DATETIME,
    expected_arrival_time DATETIME,
    status ENUM('scheduled', 'out of service', 'break'),
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id),
    FOREIGN KEY (route_id) REFERENCES route(id)
);

CREATE TABLE gps_track (
    id INT PRIMARY KEY AUTO_INCREMENT,
    departure_schedule_id INT,
    station_id INT,
    arrival_time DATETIME,
    depart_time DATETIME,
    real_miles DECIMAL(10,2),
    real_consumption DECIMAL(10,2),
    FOREIGN KEY (departure_schedule_id) REFERENCES departure_schedule(id),
    FOREIGN KEY (station_id) REFERENCES station(id)
);

CREATE TABLE maintain_record (
    id INT PRIMARY KEY AUTO_INCREMENT,
    vehicle_id INT,
    maintain_time DATETIME,
    FOREIGN KEY (vehicle_id) REFERENCES vehicle(id)
);
