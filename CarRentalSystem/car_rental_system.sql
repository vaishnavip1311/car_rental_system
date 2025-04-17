create database car_rental_system;

use car_rental_system;

-- vehicle table
CREATE TABLE Vehicle (
    vehicleID INT PRIMARY KEY AUTO_INCREMENT,
    make VARCHAR(50) NOT NULL,
    model VARCHAR(50) NOT NULL,
    year INT NOT NULL,
    dailyRate DECIMAL(10, 2) NOT NULL,
    status ENUM('available', 'notAvailable') NOT NULL,
    passengerCapacity INT NOT NULL,
    engineCapacity DECIMAL(5, 2) NOT NULL
);

-- customer table
CREATE TABLE Customer (
    customerID INT PRIMARY KEY AUTO_INCREMENT,
    firstName VARCHAR(50) NOT NULL,
    lastName VARCHAR(50) NOT NULL,
    email VARCHAR(100) UNIQUE NOT NULL,
    phoneNumber VARCHAR(15) NOT NULL
);

-- lease table
CREATE TABLE Lease (
    leaseID INT PRIMARY KEY AUTO_INCREMENT,
    vehicleID INT NOT NULL,
    customerID INT NOT NULL,
    startDate DATE NOT NULL,
    endDate DATE NOT NULL,
    type ENUM('DailyLease', 'MonthlyLease') NOT NULL,
    FOREIGN KEY (vehicleID) REFERENCES Vehicle(vehicleID),
    FOREIGN KEY (customerID) REFERENCES Customer(customerID)
);

--  table
CREATE TABLE Payment (
    paymentID INT PRIMARY KEY AUTO_INCREMENT,
    leaseID INT NOT NULL,
    paymentDate DATE NOT NULL,
    amount DECIMAL(10, 2) NOT NULL,
    FOREIGN KEY (leaseID) REFERENCES Lease(leaseID)
);


INSERT INTO Vehicle (make, model, year, dailyRate, status, passengerCapacity, engineCapacity) 
VALUES 
('Maruti Suzuki', 'Swift', 2022, 1500.00, 'available', 5, 1.2),
('Tata', 'Nexon', 2023, 2000.00, 'available', 5, 1.5);

INSERT INTO Customer (firstName, lastName, email, phoneNumber) 
VALUES 
('Rohit', 'Sharma', 'rohit.sharma@example.com', '9876543210'),
('Anjali', 'Patel', 'anjali.patel@example.com', '9123456789');

INSERT INTO Lease (vehicleID, customerID, startDate, endDate, type) 
VALUES 
(1, 1, '2025-04-01', '2025-04-05', 'DailyLease'),
(2, 2, '2025-04-03', '2025-05-03', 'MonthlyLease');

INSERT INTO Payment (leaseID, paymentDate, amount) 
VALUES 
(1, '2025-04-01', 6000.00),
(2, '2025-04-03', 20000.00);

select * from Vehicle;
select * from Customer;
select * from Lease;
select * from Payment;