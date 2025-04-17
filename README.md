# 🚗 Car Rental System

A Java-based Car Rental System that allows users to manage cars, customers, leases, and payments. The system uses **JDBC** for database interaction and follows an object-oriented design with layered architecture.

## 🧠 Features

- Add, view, and manage **Cars** and **Customers**
- Create and retrieve **Leases**
- Handle **Payments**
- Exception handling using custom exceptions
- SQL backend integration with **MySQL**
- Modular and testable code
- Unit testing using **JUnit 4**

## 🛠️ Technologies Used

- Java (JDK 17+)
- JDBC
- MySQL
- JUnit 4
- Eclipse IDE

## 🗃️ Database Schema

### Tables:
- `Vehicle(vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity)`
- `Customer(customerID, firstName, lastName, email, phoneNumber)`
- `Lease(leaseID, vehicleID, customerID, startDate, endDate, type)`
- `Payment(paymentID, leaseID, paymentDate, amount)`

## ✅ How to Run

1. Clone the repo:
   ```bash
   git clone https://github.com/vaishnavip1311/car_rental_system.git
   cd car_rental_system
   
2.Set up the MySQL database using the provided schema.

3.Update DBConnUtil.java with your MySQL credentials.

4.Open the project in Eclipse.

5.Run MainModule.java to interact with the system.

🧪 Unit Testing

JUnit test cases are available for:

•	Car creation

•	Lease creation and retrieval

•	Customer addition

•	Exception scenarios (CarExists, CustomerExists, LeaseNotFound)

👩‍💻 Author

Vaishnavi Sharad Patil

Computer Science & Engineering (2025 Batch)
