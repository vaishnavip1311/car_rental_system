package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import entity.Car;
import entity.Customer;
import entity.Lease;
import exception.CarExistsException;
import exception.CarNotFoundException;
import exception.CustomerExistsException;
import exception.CustomerNotFoundException;
import exception.DbConnectionException;
import exception.LeaseNotFoundException;
import util.DBConnection;
import util.ProjectConstants;

public class ICarLeaseRepositoryImpl implements ICarLeaseRepository{
	
	private static Connection conn = null;

    static {
        try {
            conn = DBConnection.getDbConnection();
        } catch (DbConnectionException e) {
            System.out.println(e.getMessage());
        }
    }

	@Override
	/**
	 * Adds a new car to the system if it doesn't already exist.
	 * 
	 * @param car the Car object to be added
	 * @throws CarExistsException if a car with the given vehicle ID already exists
	 */
	public void addCar(Car car) throws CarExistsException{
		
		try {
			String checkQuery = ProjectConstants.CHECK_CAR_QRY;
			PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
	        checkStmt.setInt(ProjectConstants.FIRST_INDEX, car.getVehicleID());
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next() && rs.getInt(1) > 0) {
	            throw new CarExistsException(ProjectConstants.CAR_EXISTS + car.getVehicleID());
	        }
	        
	        PreparedStatement st = conn.prepareStatement(ProjectConstants.ADD_CAR_QRY);
			st.setInt(ProjectConstants.FIRST_INDEX, car.getVehicleID());
			st.setString(ProjectConstants.SECOND_INDEX, car.getMake());
			st.setString(ProjectConstants.THIRD_INDEX, car.getModel());
			st.setInt(ProjectConstants.FOURTH_INDEX, car.getYear());
			st.setDouble(ProjectConstants.FIFTH_INDEX, car.getDailyRate());
			st.setString(ProjectConstants.SIXTH_INDEX, car.getStatus());
			st.setInt(ProjectConstants.SEVENTH_INDEX, car.getPassengerCapacity());
			st.setDouble(ProjectConstants.EIGHTH_INDEX, car.getEngineCapacity());

			st.executeUpdate();
		
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		
	}
	
	@Override
	/**
	 * Removes a car from the system using its ID.
	 * 
	 * @param carID the ID of the car to be removed
	 * @throws CarNotFoundException if no car with the given ID exists
	 */
	public void removeCar(int carID) throws CarNotFoundException {

	    try {
	        PreparedStatement st = conn.prepareStatement(ProjectConstants.REMOVE_CAR_QRY);
	        st.setInt(ProjectConstants.FIRST_INDEX, carID);
	        
	        int rowsDeleted = st.executeUpdate();
	        if (rowsDeleted == 0) {
	            throw new CarNotFoundException();
	        }
	        System.out.println(ProjectConstants.CAR_REMOVED);
	        
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    } 
	}
	
	@Override
	/**
	 * Retrieves a list of all available (not currently leased) cars.
	 * 
	 * @return List of available Car objects
	 */
	public List<Car> listAvailableCars() {
		List<Car> cars = new ArrayList<>();
		
		try {
			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_AVAILABLE_CAR_QRY);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
	            Car car = new Car(
	                rs.getInt(ProjectConstants.VEHICLE_ID),
	                rs.getString(ProjectConstants.MAKE),
	                rs.getString(ProjectConstants.MODEL),
	                rs.getInt(ProjectConstants.YEAR),
	                rs.getDouble(ProjectConstants.DAILY_RATE),
	                rs.getString(ProjectConstants.STATUS),
	                rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
	                rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
	            );
	            cars.add(car);
	        }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return cars;
	}

	@Override
	/**
	 * Retrieves a list of all cars currently leased.
	 * 
	 * @return List of rented Car objects
	 */
	public List<Car> listRentedCars() {
		List<Car> rentedCars = new ArrayList<>();
		
		try {
			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_RENTED_CAR_QRY);
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
	            Car car = new Car(
	                rs.getInt(ProjectConstants.VEHICLE_ID),
	                rs.getString(ProjectConstants.MAKE),
	                rs.getString(ProjectConstants.MODEL),
	                rs.getInt(ProjectConstants.YEAR),
	                rs.getDouble(ProjectConstants.DAILY_RATE),
	                rs.getString(ProjectConstants.STATUS),
	                rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
	                rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
	            );
	            rentedCars.add(car);
	        }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return rentedCars;
	}

	@Override
	/**
	 * Finds and returns car details by its ID.
	 * 
	 * @param carID the ID of the car
	 * @return Car object with given ID
	 * @throws CarNotFoundException if no car is found with the given ID
	 */
	public Car findCarById(int carID) throws CarNotFoundException {
		
		try {

			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_CAR_BY_ID_QRY);
			st.setInt(ProjectConstants.FIRST_INDEX, carID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
	            return new Car(
	            		rs.getInt(ProjectConstants.VEHICLE_ID),
		                rs.getString(ProjectConstants.MAKE),
		                rs.getString(ProjectConstants.MODEL),
		                rs.getInt(ProjectConstants.YEAR),
		                rs.getDouble(ProjectConstants.DAILY_RATE),
		                rs.getString(ProjectConstants.STATUS),
		                rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
		                rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
	            );
	        } else {
	            throw new CarNotFoundException();
	        }
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;		
	}

	@Override
	/**
	 * Adds a new customer to the system if they don't already exist.
	 * 
	 * @param customer the Customer object to be added
	 * @throws CustomerExistsException if the customer already exists
	 */
	public void addCustomer(Customer customer) throws CustomerExistsException{
		
		try {
			
			PreparedStatement checkStmt = conn.prepareStatement(ProjectConstants.CHECK_CUSTOMER_QRY);
	        checkStmt.setInt(ProjectConstants.FIRST_INDEX, customer.getCustomerID());
	        ResultSet rs = checkStmt.executeQuery();

	        if (rs.next()) {
	            throw new CustomerExistsException();
	        }
	        
			PreparedStatement st = conn.prepareStatement(ProjectConstants.ADD_CUSTOMER_QRY);
			st.setInt(ProjectConstants.FIRST_INDEX, customer.getCustomerID());        
			st.setString(ProjectConstants.SECOND_INDEX, customer.getFirstName());
			st.setString(ProjectConstants.THIRD_INDEX, customer.getLastName());
			st.setString(ProjectConstants.FOURTH_INDEX, customer.getEmail());
			st.setString(ProjectConstants.FIFTH_INDEX, customer.getPhoneNumber());

			st.executeUpdate();
	        System.out.println(ProjectConstants.CUSTOMER_ADDED + customer.getCustomerID());
	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}		
	}

	@Override
	/**
	 * Removes a customer from the system using their ID.
	 * 
	 * @param customerID the ID of the customer to be removed
	 * @throws CustomerNotFoundException if the customer does not exist
	 */
	public void removeCustomer(int customerID) throws CustomerNotFoundException {
		
	    try {
	        
	        PreparedStatement st = conn.prepareStatement(ProjectConstants.REMOVE_CUSTOMER_QRY);
	        st.setInt(ProjectConstants.FIRST_INDEX, customerID);
	        
	        int rowsDeleted = st.executeUpdate();
	        if (rowsDeleted == 0) 
	        	throw new CustomerNotFoundException();
	        
	        System.out.println(ProjectConstants.CUSTOMER_REMOVED);
	        
	    } catch (SQLException e) {
	        System.out.println(e.getMessage());
	        e.printStackTrace();
	    } 
		
	}

	@Override
	/**
	 * Returns a list of all customers in the system.
	 * 
	 * @return List of Customer objects
	 */
	public List<Customer> listCustomers() {
		List<Customer> customerList = new ArrayList<>();
		
		try {
			
			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_ALL_CUSTOMER_QRY);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
	            Customer customer = new Customer();
	            customer.setCustomerID(rs.getInt(ProjectConstants.CUSTOMER_ID));
	            customer.setFirstName(rs.getString(ProjectConstants.FIRST_NAME));
	            customer.setLastName(rs.getString(ProjectConstants.LAST_NAME));
	            customer.setEmail(rs.getString(ProjectConstants.EMAIL));
	            customer.setPhoneNumber(rs.getString(ProjectConstants.PHONE_NUMBER));

	            customerList.add(customer);
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return customerList;
	}

	@Override
	/**
	 * Finds and returns a customer by their ID.
	 * 
	 * @param customerID the ID of the customer
	 * @return Customer object
	 * @throws CustomerNotFoundException if the customer is not found
	 */
	public Customer findCustomerById(int customerID) throws CustomerNotFoundException {
		
		try {

			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_CUSTOMER_BY_ID_QRY);
			st.setInt(ProjectConstants.FIRST_INDEX, customerID);
			ResultSet rs = st.executeQuery();
			if (rs.next()) {
	            return new Customer(
	                rs.getInt(ProjectConstants.CUSTOMER_ID),
	                rs.getString(ProjectConstants.FIRST_NAME),
	                rs.getString(ProjectConstants.LAST_NAME),
	                rs.getString(ProjectConstants.EMAIL),
	                rs.getString(ProjectConstants.PHONE_NUMBER)
	            );
	        } else {
	            throw new CustomerNotFoundException();
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * Creates a lease agreement for a car and customer.
	 * 
	 * @param customerID the ID of the customer
	 * @param carID the ID of the car to be leased
	 * @param startDate lease start date
	 * @param endDate lease end date
	 * @param type the lease type
	 * @return the created Lease object
	 * @throws CustomerNotFoundException if the customer does not exist
	 * @throws CarNotFoundException if the car does not exist
	 */
	public Lease createLease(int customerID, int carID, LocalDate startDate, LocalDate endDate, String type)
			throws CustomerNotFoundException, CarNotFoundException {
		
	    try {
	    Car car = findCarById(carID);                   
		Customer customer = findCustomerById(customerID);
		
	    String insert = ProjectConstants.INSERT_LEASE_QRY;
	    String updateCar = ProjectConstants.UPDATE_CAR_NOT_AVAILABLE_QRY;
		
		try {
			
			PreparedStatement ps = conn.prepareStatement(insert, Statement.RETURN_GENERATED_KEYS);
	        PreparedStatement psCar = conn.prepareStatement(updateCar);
			ps.setInt(ProjectConstants.FIRST_INDEX, carID);
	        ps.setInt(ProjectConstants.SECOND_INDEX, customerID);
	        ps.setDate(ProjectConstants.THIRD_INDEX, java.sql.Date.valueOf(startDate));
	        ps.setDate(ProjectConstants.FOURTH_INDEX, java.sql.Date.valueOf(endDate));
	        ps.setString(ProjectConstants.FIFTH_INDEX, type);

	        ps.executeUpdate();

	        ResultSet keys = ps.getGeneratedKeys();
	        int leaseID = 0;
	        if (keys.next()) {
	            leaseID = keys.getInt(ProjectConstants.FIRST_INDEX);
	        }

	        psCar.setInt(ProjectConstants.FIRST_INDEX, carID);
	        psCar.executeUpdate();

	        return new Lease(leaseID, car, customer, startDate, endDate, type);

		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    }catch (CustomerNotFoundException | CarNotFoundException e) {
	        throw e;
	    }
		return null;
	}

	@Override
	/**
	 * Marks the car as returned and ends the lease associated with the given lease ID.
	 * 
	 * @param leaseID the ID of the lease
	 * @return Lease object associated with the lease ID
	 * @throws LeaseNotFoundException if no lease is found with the given ID
	 */
	public Lease returnCar(int leaseID) throws LeaseNotFoundException {
	    
	    try {
			
	    	    PreparedStatement ps = conn.prepareStatement(ProjectConstants.VIEW_LEASE_BY_ID_QRY);
	            ps.setInt(ProjectConstants.FIRST_INDEX, leaseID);
	            ResultSet rs = ps.executeQuery();

	            if (rs.next()) {
	                Car car = new Car(
	                    rs.getInt(ProjectConstants.VEHICLE_ID),
	                    rs.getString(ProjectConstants.MAKE),
	                    rs.getString(ProjectConstants.MODEL),
	                    rs.getInt(ProjectConstants.YEAR),
	                    rs.getDouble(ProjectConstants.DAILY_RATE),
	                    rs.getString(ProjectConstants.STATUS),
	                    rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
	                    rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
	                );

	                Customer customer = new Customer(
	                    rs.getInt(ProjectConstants.CUSTOMER_ID),
	                    rs.getString(ProjectConstants.FIRST_NAME),
	                    rs.getString(ProjectConstants.LAST_NAME),
	                    rs.getString(ProjectConstants.EMAIL),
	                    rs.getString(ProjectConstants.PHONE_NUMBER)
	                );

	                try (PreparedStatement psUpdate = conn.prepareStatement(ProjectConstants.UPDATE_CAR_AVAILABLE_QRY)) {
	                    psUpdate.setInt(ProjectConstants.FIRST_INDEX, car.getVehicleID());
	                    psUpdate.executeUpdate();
	                }

	                return new Lease(
	                    leaseID,
	                    car,
	                    customer,
	                    rs.getDate(ProjectConstants.START_DATE).toLocalDate(),
	                    rs.getDate(ProjectConstants.END_DATE).toLocalDate(),
	                    rs.getString(ProjectConstants.TYPE)
	                );
	            } else {
	                throw new LeaseNotFoundException();
	            }

	        
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return null;
	}

	@Override
	/**
	 * Returns a list of all active leases.
	 * 
	 * @return List of active Lease objects
	 */
	public List<Lease> listActiveLeases() {
		List<Lease> activeLeases = new ArrayList<>();
		
		try {
			
			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_ACTIVE_LEASE_QRY);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Car car = new Car(
		                rs.getInt(ProjectConstants.VEHICLE_ID),
		                rs.getString(ProjectConstants.MAKE),
		                rs.getString(ProjectConstants.MODEL),
		                rs.getInt(ProjectConstants.YEAR),
		                rs.getDouble(ProjectConstants.DAILY_RATE),
		                rs.getString(ProjectConstants.STATUS),
		                rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
		                rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
		            );

		            Customer customer = new Customer(
		                rs.getInt(ProjectConstants.CUSTOMER_ID),
		                rs.getString(ProjectConstants.FIRST_NAME),
		                rs.getString(ProjectConstants.LAST_NAME),
		                rs.getString(ProjectConstants.EMAIL),
		                rs.getString(ProjectConstants.PHONE_NUMBER)
		            );

		            Lease lease = new Lease(
		                rs.getInt(ProjectConstants.LEASE_ID),
		                car,
		                customer,
		                rs.getDate(ProjectConstants.START_DATE).toLocalDate(),
		                rs.getDate(ProjectConstants.END_DATE).toLocalDate(),
		                rs.getString(ProjectConstants.TYPE)
		            );

		            activeLeases.add(lease);
		        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return activeLeases;
	}

	@Override
	/**
	 * Returns a list of all leases (past and current).
	 * 
	 * @return List of Lease history objects
	 */
	public List<Lease> listLeaseHistory() {
		List<Lease> leaseHistory = new ArrayList<>();
		
		try {
			
			PreparedStatement st= conn.prepareStatement(ProjectConstants.VIEW_ALL_LEASE_QRY);
			ResultSet rs = st.executeQuery();
			
			while (rs.next()) {
				Car car = new Car(
		                rs.getInt(ProjectConstants.VEHICLE_ID),
		                rs.getString(ProjectConstants.MAKE),
		                rs.getString(ProjectConstants.MODEL),
		                rs.getInt(ProjectConstants.YEAR),
		                rs.getDouble(ProjectConstants.DAILY_RATE),
		                rs.getString(ProjectConstants.STATUS),
		                rs.getInt(ProjectConstants.PASSENGER_CAPACITY),
		                rs.getDouble(ProjectConstants.ENGINE_CAPACITY)
		            );

		            Customer customer = new Customer(
		                rs.getInt(ProjectConstants.CUSTOMER_ID),
		                rs.getString(ProjectConstants.FIRST_NAME),
		                rs.getString(ProjectConstants.LAST_NAME),
		                rs.getString(ProjectConstants.EMAIL),
		                rs.getString(ProjectConstants.PHONE_NUMBER)
		            );

		            Lease lease = new Lease(
		                rs.getInt(ProjectConstants.LEASE_ID),
		                car,
		                customer,
		                rs.getDate(ProjectConstants.START_DATE).toLocalDate(),
		                rs.getDate(ProjectConstants.END_DATE).toLocalDate(),
		                rs.getString(ProjectConstants.TYPE)
		            );

		            leaseHistory.add(lease);
	        }
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
		return leaseHistory;
	}

	@Override
	public void recordPayment(Lease lease, double amount) throws LeaseNotFoundException {
        PreparedStatement psCheck = null;
        PreparedStatement psInsert = null;
        ResultSet rs = null;
        try {

        String checkQuery = ProjectConstants.CHECK_LEASE_QRY;
        psCheck = conn.prepareStatement(checkQuery);
        psCheck.setInt(ProjectConstants.FIRST_INDEX, lease.getLeaseID());
        rs = psCheck.executeQuery();

        if (!rs.next()) {
            throw new LeaseNotFoundException();
        }

        String insertQuery = ProjectConstants.INSERT_PAYMENT_QRY;
        psInsert = conn.prepareStatement(insertQuery);
        psInsert.setInt(ProjectConstants.FIRST_INDEX, lease.getLeaseID());
        psInsert.setDouble(ProjectConstants.SECOND_INDEX, amount);
        psInsert.setDate(ProjectConstants.THIRD_INDEX, java.sql.Date.valueOf(LocalDate.now()));

        psInsert.executeUpdate();
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
		
	}

	
        


