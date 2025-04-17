package util;

public class ProjectConstants {
	
	public static final String DELIMETER = ",";
	public static final String DB_FILE_NAME = "src/db.properties";
	public static final String DB_DRIVER = "driver";
	public static final String DB_URL = "dburl";
	public static final String CANNOT_OPEN_CONNECTION = "Connection cannot be opened";
	
	//exceptions
		public static final String CAR_NOT_FOUND = "Car not found with Id ";
		public static final String CUSTOMER_NOT_FOUND = "Customer not found with ID ";
		public static final String LEASE_NOT_FOUND = "Lease not found with ID ";
		public static final String CAR_EXISTS = "Car Already Exists with ID ";
		public static final String CUSTOMER_EXISTS = "Customer Already Exists with Id ";
		
	//dbcolumns
	public static final String VEHICLE_ID = "vehicleID";
	public static final String MAKE = "make";
	public static final String MODEL = "model";
	public static final String YEAR = "year";
	public static final String DAILY_RATE ="dailyRate";
	public static final String STATUS = "status";
	public static final String PASSENGER_CAPACITY = "passengerCapacity";
	public static final String ENGINE_CAPACITY = "engineCapacity";
	public static final String CUSTOMER_ID = "customerID";
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String EMAIL = "email";
	public static final String PHONE_NUMBER = "phoneNumber";
	public static final String LEASE_ID = "leaseID";
	public static final String START_DATE = "startDate";
	public static final String END_DATE = "endDate";
	public static final String TYPE = "type";
	
	
	
	// client side
	public static final String PROMPT_DEPT_NAME = "Enter the department name:";
	public static final String PROMPT_DEPT_ID = "Enter the department Id:";
	public static final String PROMPT_EMP_ID = "Enter the employee Id:";
	public static final String PROMPT_EMP_NAME = "Enter the employee name:";
	public static final String PROMPT_EMP_SAL = "Enter the employee salary:";
	public static final String PROMPT_EMP_DOJ = "Enter the employee doj(dd-mm-yyyy):";
	public static final String CAR_ADDED = "Car added successfully with ID: ";
	public static final String CAR_REMOVED = "Car removed successfully.";
	public static final String CUSTOMER_ADDED = "Customer added successfully with ID: ";
	public static final String CUSTOMER_REMOVED = "Customer removed successfully.";
	
	
	public static final int FIRST_INDEX = 1;
	public static final int SECOND_INDEX = 2;
	public static final int THIRD_INDEX = 3;
	public static final int FOURTH_INDEX = 4;
	public static final int FIFTH_INDEX = 5;
	public static final int SIXTH_INDEX = 6;
	public static final int SEVENTH_INDEX = 7;
	public static final int EIGHTH_INDEX = 8;
	
	
	
	// queries
	public static final String CHECK_CAR_QRY = "SELECT COUNT(*) FROM vehicle WHERE vehicleID = ?";
	public static final String ADD_CAR_QRY = "INSERT INTO vehicle (vehicleID, make, model, year, dailyRate, status, passengerCapacity, engineCapacity) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
	public static final String INSERT_PAYMENT_QRY = "INSERT INTO payment (leaseID, amount, paymentDate) VALUES (?, ?, ?)";
	public static final String CHECK_LEASE_QRY = "SELECT * FROM lease WHERE leaseId = ?";
	public static final String UPDATE_CAR_AVAILABLE_QRY = "UPDATE vehicle SET status = 'available' WHERE vehicleID = ?";
	public static final String UPDATE_CAR_NOT_AVAILABLE_QRY = "UPDATE vehicle SET status = 'notAvailable' WHERE vehicleID = ?";
	public static final String INSERT_LEASE_QRY = "INSERT INTO lease (vehicleID, customerID, startDate, endDate, type) VALUES (?, ?, ?, ?, ?)";
	public static final String VIEW_ALL_CUSTOMER_QRY = "SELECT * FROM customer";
	public static final String VIEW_CUSTOMER_BY_ID_QRY = "SELECT * FROM customer WHERE customerID = ?";
	public static final String REMOVE_CUSTOMER_QRY = "DELETE FROM customer WHERE customerID = ?";
	public static final String ADD_CUSTOMER_QRY = "INSERT INTO customer (customerID, firstName, lastName, email, phoneNumber) VALUES (?, ?, ?, ?, ?)";
	public static final String CHECK_CUSTOMER_QRY = "SELECT customerID FROM customer WHERE customerID = ?";
	public static final String VIEW_CAR_BY_ID_QRY = "SELECT * FROM vehicle WHERE vehicleID = ?";
	public static final String VIEW_RENTED_CAR_QRY = "SELECT * FROM vehicle WHERE status = 'notAvailable'";
	public static final String VIEW_AVAILABLE_CAR_QRY = "SELECT * FROM vehicle WHERE status = 'available'";
	public static final String REMOVE_CAR_QRY = "DELETE FROM vehicle WHERE vehicleID = ?";
	
	public static final String VIEW_ACTIVE_LEASE_QRY = 
		    "SELECT l.leaseID, l.startDate, l.endDate, l.type, " +
		    "v.vehicleID, v.make, v.model, v.year, v.dailyRate, v.status, v.passengerCapacity, v.engineCapacity, " +
		    "c.customerID, c.firstName, c.lastName, c.email, c.phoneNumber " +
		    "FROM Lease l " +
		    "JOIN Vehicle v ON l.vehicleID = v.vehicleID " +
		    "JOIN Customer c ON l.customerID = c.customerID " +
		    "WHERE v.status = 'notAvailable'";
	
	public static final String VIEW_ALL_LEASE_QRY = 
		    "SELECT l.leaseID, l.startDate, l.endDate, l.type, " +
		    "v.vehicleID, v.make, v.model, v.year, v.dailyRate, v.status, v.passengerCapacity, v.engineCapacity, " +
		    "c.customerID, c.firstName, c.lastName, c.email, c.phoneNumber " +
		    "FROM Lease l " +
		    "JOIN Vehicle v ON l.vehicleID = v.vehicleID " +
		    "JOIN Customer c ON l.customerID = c.customerID";
	
	public static final String VIEW_LEASE_BY_ID_QRY = 
		    "SELECT l.leaseID, l.startDate, l.endDate, l.type, " +
		    "v.vehicleID, v.make, v.model, v.year, v.dailyRate, v.status, v.passengerCapacity, v.engineCapacity, " +
		    "c.customerID, c.firstName, c.lastName, c.email, c.phoneNumber " +
		    "FROM Lease l " +
		    "JOIN Vehicle v ON l.vehicleID = v.vehicleID " +
		    "JOIN Customer c ON l.customerID = c.customerID " +
		    "WHERE l.leaseID = ?";

	public static final String WELCOME_MESSAGE = "Welcome to CAR RENTAL SYSTEM !!";
    public static final String CONTINUE_PROMPT = "Press 'y' to continue: ";
    public static final String EXIT_MESSAGE = "Exiting Car Rental System. Goodbye!";
    public static final String INVALID_CHOICE = "Invalid choice. Try again.";
    public static final String ENTER_CHOICE = "Enter your choice: ";
    public static final String ERROR_PROMPT = "Error:";

    // Menu messages
    public static final String MENU_HEADER = "======= CAR RENTAL SYSTEM MENU =======";
    public static final String MENU_OPTION_1 = "1. Add Car";
    public static final String MENU_OPTION_2 = "2. Remove Car";
    public static final String MENU_OPTION_3 = "3. List Available Cars";
    public static final String MENU_OPTION_4 = "4. List Rented Cars";
    public static final String MENU_OPTION_5 = "5. Find Car by ID";
    public static final String MENU_OPTION_6 = "6. Add Customer";
    public static final String MENU_OPTION_7 = "7. Remove Customer";
    public static final String MENU_OPTION_8 = "8. List All Customers";
    public static final String MENU_OPTION_9 = "9. Find Customer by ID";
    public static final String MENU_OPTION_10 = "10. Create Lease";
    public static final String MENU_OPTION_11 = "11. Return Car";
    public static final String MENU_OPTION_12 = "12. List Active Leases";
    public static final String MENU_OPTION_13 = "13. List Lease History";
    public static final String MENU_OPTION_14 = "14. Record Payment";
    public static final String MENU_OPTION_15 = "15. Exit";
    public static final String MENU_FOOTER = "======================================";
    public static final String DISPLAY_SEPARATOR = "-------------------------------------------------";

    // Common messages
    public static final String NO_AVAILABLE_CARS = "No available cars.";
    public static final String NO_RENTED_CARS = "No rented cars currently.";
    public static final String NO_CUSTOMERS = "No customers found.";
    public static final String NO_ACTIVE_LEASES = "No active leases.";
    public static final String NO_LEASE_HISTORY = "No lease history.";
    public static final String INVALID_DATE_FORMAT = "Invalid date format.";
    public static final String DISPLAY_AVAILABLE_CARS = "\n=== Available Cars ===";
    public static final String DISPLAY_RENTED_CARS = "\n=== Rented Cars ===";
    public static final String DISPLAY_CAR_WITH_ID = "\n=== Car Details With Id ";
    public static final String DISPLAY_ALL_CUSTOMERS = "\n=== All Customers ===";
    public static final String DISPLAY_CUSTOMER_WITH_ID = "=== Customer Details With Id ";
    public static final String DISPLAY_ACTIVE_LEASES = "\n=== Active Leases ===";
    public static final String DISPLAY_LEASE_HISTORY = "\n=== Lease History ===";
    public static final String DISPLAY_VEHICLE_ID = "Vehicle ID         : ";
    public static final String DISPLAY_MAKE = "Make               : ";
    public static final String DISPLAY_MODEL = "Model              : ";
    public static final String DISPLAY_YEAR = "Year               : ";
    public static final String DISPLAY_DAILY_RATE = "Daily Rate         : Rs.";
    public static final String DISPLAY_STATUS = "Status             : ";
    public static final String DISPLAY_PASSENGER_CAPACITY = "Passenger Capacity : ";
    public static final String DISPLAY_ENGINE_CAPACITY = "Engine Capacity    : ";
    public static final String DISPLAY_CUSTOMER_ID = "Customer ID  : ";
    public static final String DISPLAY_FIRST_NAME = "First Name   : ";
    public static final String DISPLAY_LAST_NAME = "Last Name    : ";
    public static final String DISPLAY_EMAIL = "Email        : ";
    public static final String DISPLAY_PHONE_NUMBER = "Phone Number : ";
    public static final String DISPLAY_LEASE_ID = "Lease ID     : ";
    public static final String DISPLAY_START_DATE = "Start Date   : ";
    public static final String DISPLAY_END_DATE = "End Date     : ";
    public static final String DISPLAY_LEASE_TYPE = "Lease Type   : ";
    public static final String PAYMENT_PROMPT = "Payment of Rs.";
    public static final String RECORD_PROMPT = " recorded for lease ID: ";
    

    // Success messages
    public static final String CAR_LEASE_SUCCESS = "Car leased successfully.";
    public static final String CAR_RETURN_SUCCESS = "Car returned successfully.";
    public static final String PAYMENT_SUCCESS = "Payment recorded successfully.";

    // Prompts
    public static final String ENTER_CAR_ID = "Enter car ID: ";
    public static final String ENTER_CUSTOMER_ID = "Enter customer ID: ";
    public static final String ENTER_LEASE_ID = "Enter lease ID: ";
    public static final String ENTER_AMOUNT = "Enter amount: ";
    public static final String ENTER_VEHICLE_ID = "Enter Vehicle ID: ";
    public static final String ENTER_MAKE = "Enter Make(Brand):";
    public static final String ENTER_MODEL = "Enter Model: ";
    public static final String ENTER_YEAR = "Enter Year: ";
    public static final String ENTER_DAILY_RATE = "Enter Daily Rate: ";
    public static final String ENTER_STATUS = "Enter Status (available/notAvailable): ";
    public static final String ENTER_PASSENGER_CAPACITY = "Enter Passenger Capacity: ";
    public static final String ENTER_ENGINE_CAPACITY = "Enter Engine Capacity (in CC): ";
    public static final String ENTER_FIRST_NAME = "Enter First Name: ";
    public static final String ENTER_LAST_NAME = "Enter Last Name: ";
    public static final String ENTER_EMAIL = "Enter Email: ";
    public static final String ENTER_PHONE = "Enter Phone Number: ";
    public static final String ENTER_LEASE_START_DATE = "Enter Lease Start Date (yyyy-MM-dd): ";
    public static final String ENTER_LEASE_END_DATE = "Enter Lease End Date (yyyy-MM-dd): ";
    public static final String ENTER_LEASE_TYPE = "Enter Lease Type (DailyLease / MonthlyLease): ";
    public static final String PAYMENT_CONFIRM = "Payment of Rs.%s recorded for lease ID: %d";

}
