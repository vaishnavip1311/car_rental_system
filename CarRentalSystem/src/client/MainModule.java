package client;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.Scanner;

import dao.ICarLeaseRepository;
import dao.ICarLeaseRepositoryImpl;
import entity.Car;
import entity.Customer;
import entity.Lease;
import exception.CarExistsException;
import exception.CarNotFoundException;
import exception.CustomerExistsException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;
import util.ProjectConstants;

public class MainModule {
	
	private static final Scanner scanner = new Scanner(System.in);
    private static final ICarLeaseRepository repo = new ICarLeaseRepositoryImpl();

    public static void main(String[] args) {
        boolean exit = false;
        System.out.println(ProjectConstants.WELCOME_MESSAGE);
        while (!exit) {
        	System.out.print(ProjectConstants.CONTINUE_PROMPT);
            String input = scanner.nextLine();
            if (!input.equalsIgnoreCase("y")) {
                System.out.println(ProjectConstants.EXIT_MESSAGE);
                break;
            }
            printMenu();
            System.out.print(ProjectConstants.ENTER_CHOICE);
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> handleAddCar();
                case 2 -> handleRemoveCar();
                case 3 -> handleListAvailableCars();
                case 4 -> handleListRentedCars();
                case 5 -> handleFindCarById();
                case 6 -> handleAddCustomer();
                case 7 -> handleRemoveCustomer();
                case 8 -> handleListCustomers();
                case 9 -> handleFindCustomerById();
                case 10 -> handleCreateLease();
                case 11 -> handleReturnCar();
                case 12 -> handleListActiveLeases();
                case 13 -> handleListLeaseHistory();
                case 14 -> handleRecordPayment();
                case 15 -> {
                    System.out.println(ProjectConstants.EXIT_MESSAGE);
                    exit = true;
                }
                default -> System.out.println(ProjectConstants.INVALID_CHOICE);
            }
        }
    }

    private static void printMenu() {
        System.out.println(ProjectConstants.MENU_HEADER);
        System.out.println(ProjectConstants.MENU_OPTION_1);
        System.out.println(ProjectConstants.MENU_OPTION_2);
        System.out.println(ProjectConstants.MENU_OPTION_3);
        System.out.println(ProjectConstants.MENU_OPTION_4);
        System.out.println(ProjectConstants.MENU_OPTION_5);
        System.out.println(ProjectConstants.MENU_OPTION_6);
        System.out.println(ProjectConstants.MENU_OPTION_7);
        System.out.println(ProjectConstants.MENU_OPTION_8);
        System.out.println(ProjectConstants.MENU_OPTION_9);
        System.out.println(ProjectConstants.MENU_OPTION_10);
        System.out.println(ProjectConstants.MENU_OPTION_11);
        System.out.println(ProjectConstants.MENU_OPTION_12);
        System.out.println(ProjectConstants.MENU_OPTION_13);
        System.out.println(ProjectConstants.MENU_OPTION_14);
        System.out.println(ProjectConstants.MENU_OPTION_15);
        System.out.println(ProjectConstants.MENU_FOOTER);
    }

    // ---------------------- Car Methods ----------------------

    private static void handleAddCar() {
    	try {
        System.out.print(ProjectConstants.ENTER_CAR_ID);
        int vehicleId = scanner.nextInt();
        scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_MAKE);
        String make = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_MODEL);
        String model = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_YEAR);
        int year = scanner.nextInt();

        System.out.print(ProjectConstants.ENTER_DAILY_RATE);
        double dailyRate = scanner.nextDouble();

        scanner.nextLine(); 
        System.out.print(ProjectConstants.ENTER_STATUS);
        String status = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_PASSENGER_CAPACITY);
        int passengerCapacity = scanner.nextInt();

        System.out.print(ProjectConstants.ENTER_ENGINE_CAPACITY);
        double engineCapacity = scanner.nextDouble();
        scanner.nextLine();

        Car car = new Car(vehicleId, make, model, year, dailyRate, status, passengerCapacity, engineCapacity);
        repo.addCar(car);
        System.out.println(ProjectConstants.CAR_ADDED + car.getVehicleID());
        
    	} catch (CarExistsException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void handleRemoveCar() {
        System.out.print(ProjectConstants.ENTER_CAR_ID);
        int carId = scanner.nextInt();
        scanner.nextLine();
        try {
            repo.removeCar(carId);
        } catch (CarNotFoundException e) {
            System.out.println(ProjectConstants.CAR_NOT_FOUND + carId);
        }
    }

    private static void handleListAvailableCars() {
        List<Car> cars = repo.listAvailableCars();
        if (cars.isEmpty()) {
            System.out.println(ProjectConstants.NO_AVAILABLE_CARS);
        } else {
            System.out.println(ProjectConstants.DISPLAY_AVAILABLE_CARS);
            for (Car car : cars) {
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
                System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
                System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
                System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
                System.out.println(ProjectConstants.DISPLAY_YEAR + car.getYear());
                System.out.println(ProjectConstants.DISPLAY_DAILY_RATE + car.getDailyRate());
                System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
                System.out.println(ProjectConstants.DISPLAY_PASSENGER_CAPACITY + car.getPassengerCapacity());
                System.out.println(ProjectConstants.DISPLAY_ENGINE_CAPACITY + car.getEngineCapacity() + " cc");
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            }
        }
    }

    private static void handleListRentedCars() {
        List<Car> rented = repo.listRentedCars();
        if (rented.isEmpty()) {
            System.out.println(ProjectConstants.NO_RENTED_CARS);
        } else {
            System.out.println(ProjectConstants.DISPLAY_RENTED_CARS);
            for (Car car : rented) {
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
                System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
                System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
                System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
                System.out.println(ProjectConstants.DISPLAY_YEAR + car.getYear());
                System.out.println(ProjectConstants.DISPLAY_DAILY_RATE + car.getDailyRate());
                System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
                System.out.println(ProjectConstants.DISPLAY_PASSENGER_CAPACITY + car.getPassengerCapacity());
                System.out.println(ProjectConstants.DISPLAY_ENGINE_CAPACITY + car.getEngineCapacity() + " cc");
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            }
        }
    }

    private static void handleFindCarById() {
        System.out.print(ProjectConstants.ENTER_CAR_ID);
        int carId = scanner.nextInt();
        scanner.nextLine();
        try {
            Car car = repo.findCarById(carId);
            System.out.println(ProjectConstants.DISPLAY_CAR_WITH_ID + carId);
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
            System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
            System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
            System.out.println(ProjectConstants.DISPLAY_YEAR + car.getYear());
            System.out.println(ProjectConstants.DISPLAY_DAILY_RATE + car.getDailyRate());
            System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
            System.out.println(ProjectConstants.DISPLAY_PASSENGER_CAPACITY + car.getPassengerCapacity());
            System.out.println(ProjectConstants.DISPLAY_ENGINE_CAPACITY + car.getEngineCapacity() + " cc");
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
        } catch (CarNotFoundException e) {
            System.out.println(ProjectConstants.CAR_NOT_FOUND + carId);
        }
    }

    // ---------------------- Customer Methods ----------------------

    private static void handleAddCustomer() {
    	try {
        System.out.print(ProjectConstants.ENTER_CUSTOMER_ID);
        int customerId = scanner.nextInt();
        scanner.nextLine(); 

        System.out.print(ProjectConstants.ENTER_FIRST_NAME);
        String firstName = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_LAST_NAME);
        String lastName = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_EMAIL);
        String email = scanner.nextLine();

        System.out.print(ProjectConstants.ENTER_PHONE);
        String phoneNumber = scanner.nextLine();

        Customer customer = new Customer(customerId, firstName, lastName, email, phoneNumber);
        repo.addCustomer(customer);
        
    	} catch (CustomerExistsException e) {
            System.out.println(ProjectConstants.CUSTOMER_EXISTS);
        }
    }

    private static void handleRemoveCustomer() {
        System.out.print(ProjectConstants.ENTER_CUSTOMER_ID);
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            repo.removeCustomer(id);
        } catch (CustomerNotFoundException e) {
            System.out.println(ProjectConstants.CUSTOMER_NOT_FOUND + id);
        }
    }

    private static void handleListCustomers() {
        List<Customer> customers = repo.listCustomers();
        if (customers.isEmpty()) {
            System.out.println(ProjectConstants.NO_CUSTOMERS);
        } else {
            System.out.println(ProjectConstants.DISPLAY_ALL_CUSTOMERS);
            for (Customer customer : customers) {
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
                System.out.println(ProjectConstants.DISPLAY_CUSTOMER_ID + customer.getCustomerID());
                System.out.println(ProjectConstants.DISPLAY_FIRST_NAME + customer.getFirstName());
                System.out.println(ProjectConstants.DISPLAY_LAST_NAME + customer.getLastName());
                System.out.println(ProjectConstants.DISPLAY_EMAIL + customer.getEmail());
                System.out.println(ProjectConstants.DISPLAY_PHONE_NUMBER + customer.getPhoneNumber());
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            }
        }
    }

    private static void handleFindCustomerById() {
        System.out.print(ProjectConstants.ENTER_CUSTOMER_ID);
        int id = scanner.nextInt();
        scanner.nextLine();
        try {
            Customer customer = repo.findCustomerById(id);
            System.out.println(ProjectConstants.DISPLAY_CUSTOMER_WITH_ID + id);
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            System.out.println(ProjectConstants.DISPLAY_CUSTOMER_ID + customer.getCustomerID());
            System.out.println(ProjectConstants.DISPLAY_FIRST_NAME + customer.getFirstName());
            System.out.println(ProjectConstants.DISPLAY_LAST_NAME + customer.getLastName());
            System.out.println(ProjectConstants.DISPLAY_EMAIL + customer.getEmail());
            System.out.println(ProjectConstants.DISPLAY_PHONE_NUMBER + customer.getPhoneNumber());
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
        } catch (CustomerNotFoundException e) {
            System.out.println(ProjectConstants.CUSTOMER_NOT_FOUND + id);
        }
    }

    // ---------------------- Lease Methods ----------------------

    private static void handleCreateLease() {
        try {
        	System.out.print(ProjectConstants.ENTER_CAR_ID);
            int carId = scanner.nextInt();
            System.out.print(ProjectConstants.ENTER_CUSTOMER_ID);
            int customerId = scanner.nextInt();
            scanner.nextLine(); 

            System.out.print(ProjectConstants.ENTER_LEASE_START_DATE);
            String start = scanner.nextLine();
            System.out.print(ProjectConstants.ENTER_LEASE_END_DATE);
            String end = scanner.nextLine();

            System.out.print(ProjectConstants.ENTER_LEASE_TYPE);
            String type = scanner.nextLine();
            
            LocalDate startDate = LocalDate.parse(start);
            LocalDate endDate = LocalDate.parse(end);

            repo.createLease(customerId, carId, startDate, endDate, type);
            System.out.println(ProjectConstants.CAR_LEASE_SUCCESS);
        } catch (CustomerNotFoundException | CarNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (DateTimeParseException e) {
            System.out.println(ProjectConstants.INVALID_DATE_FORMAT);
        }
    }

    private static void handleReturnCar() {
        System.out.print(ProjectConstants.ENTER_LEASE_ID);
        int leaseId = scanner.nextInt();
        scanner.nextLine();
        try {
        	Lease lease = repo.returnCar(leaseId);
            System.out.println(ProjectConstants.CAR_RETURN_SUCCESS);
            System.out.println("Returned Lease Details:");
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            System.out.println(ProjectConstants.DISPLAY_LEASE_ID + lease.getLeaseID());

            Car car = lease.getCar();
            System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
            System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
            System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
            System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
            Customer customer = lease.getCustomer();
            System.out.println(ProjectConstants.DISPLAY_CUSTOMER_ID + customer.getCustomerID());
            System.out.println(ProjectConstants.DISPLAY_FIRST_NAME + customer.getFirstName());
            System.out.println(ProjectConstants.DISPLAY_LAST_NAME + customer.getLastName());
            
            System.out.println(ProjectConstants.DISPLAY_START_DATE + lease.getStartDate());
            System.out.println(ProjectConstants.DISPLAY_END_DATE + lease.getEndDate());
            System.out.println(ProjectConstants.DISPLAY_LEASE_TYPE + lease.getType());
            System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
        } catch (LeaseNotFoundException e) {
            System.out.println(ProjectConstants.LEASE_NOT_FOUND + leaseId);
        }
    }

    private static void handleListActiveLeases() {
        List<Lease> leases = repo.listActiveLeases();
        if (leases.isEmpty()) {
            System.out.println(ProjectConstants.NO_ACTIVE_LEASES);
        } else {
            System.out.println(ProjectConstants.DISPLAY_ACTIVE_LEASES);
            for (Lease lease : leases) {
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
                System.out.println(ProjectConstants.DISPLAY_LEASE_ID + lease.getLeaseID());
                Car car = lease.getCar();
                System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
                System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
                System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
                System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
                Customer customer = lease.getCustomer();
                System.out.println(ProjectConstants.DISPLAY_CUSTOMER_ID + customer.getCustomerID());
                System.out.println(ProjectConstants.DISPLAY_FIRST_NAME + customer.getFirstName());
                System.out.println(ProjectConstants.DISPLAY_LAST_NAME + customer.getLastName());
                
                System.out.println(ProjectConstants.DISPLAY_START_DATE + lease.getStartDate());
                System.out.println(ProjectConstants.DISPLAY_END_DATE + lease.getEndDate());
                System.out.println(ProjectConstants.DISPLAY_LEASE_TYPE + lease.getType());
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            }
        }
    }

    private static void handleListLeaseHistory() {
        List<Lease> history = repo.listLeaseHistory();
        if (history.isEmpty()) {
            System.out.println(ProjectConstants.NO_LEASE_HISTORY);
        } else {
            System.out.println(ProjectConstants.DISPLAY_LEASE_HISTORY);
            for (Lease lease : history) {
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
                System.out.println(ProjectConstants.DISPLAY_LEASE_ID + lease.getLeaseID());
                Car car = lease.getCar();
                System.out.println(ProjectConstants.DISPLAY_VEHICLE_ID + car.getVehicleID());
                System.out.println(ProjectConstants.DISPLAY_MAKE + car.getMake());
                System.out.println(ProjectConstants.DISPLAY_MODEL + car.getModel());
                System.out.println(ProjectConstants.DISPLAY_STATUS + car.getStatus());
                Customer customer = lease.getCustomer();
                System.out.println(ProjectConstants.DISPLAY_CUSTOMER_ID + customer.getCustomerID());
                System.out.println(ProjectConstants.DISPLAY_FIRST_NAME + customer.getFirstName());
                System.out.println(ProjectConstants.DISPLAY_LAST_NAME + customer.getLastName());
                
                System.out.println(ProjectConstants.DISPLAY_START_DATE + lease.getStartDate());
                System.out.println(ProjectConstants.DISPLAY_END_DATE + lease.getEndDate());
                System.out.println(ProjectConstants.DISPLAY_LEASE_TYPE + lease.getType());
                System.out.println(ProjectConstants.DISPLAY_SEPARATOR);
            }
        }
    }

    // ---------------------- Payment Method ----------------------

    private static void handleRecordPayment() {
        System.out.print(ProjectConstants.ENTER_LEASE_ID);
        int leaseId = scanner.nextInt();
        System.out.print(ProjectConstants.ENTER_AMOUNT);
        double amount = scanner.nextDouble();
        scanner.nextLine();
        try {
            Lease lease = new Lease();
            lease.setLeaseID(leaseId);

            repo.recordPayment(lease, amount);

            System.out.println(ProjectConstants.PAYMENT_PROMPT + amount + ProjectConstants.RECORD_PROMPT + leaseId);
        } catch (LeaseNotFoundException e) {
            System.out.println(ProjectConstants.LEASE_NOT_FOUND + leaseId);
        } catch (Exception e) {
            System.out.println(ProjectConstants.ERROR_PROMPT + e.getMessage());
        }
   
    }

}
