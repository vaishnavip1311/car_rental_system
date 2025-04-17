package dao;

import java.time.LocalDate;
import java.util.List;

import entity.Car;
import entity.Customer;
import entity.Lease;
import exception.CarExistsException;
import exception.CarNotFoundException;
import exception.CustomerExistsException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;

public interface ICarLeaseRepository {
	
	void addCar(Car car) throws CarExistsException;
    void removeCar(int carID) throws CarNotFoundException;
    List<Car> listAvailableCars();
    List<Car> listRentedCars();
    Car findCarById(int carID) throws CarNotFoundException;

    void addCustomer(Customer customer) throws CustomerExistsException;
    void removeCustomer(int customerID) throws CustomerNotFoundException;
    List<Customer> listCustomers();
    Customer findCustomerById(int customerID) throws CustomerNotFoundException;

    Lease createLease(int customerID, int carID, LocalDate startDate, LocalDate endDate, String type) throws CustomerNotFoundException,CarNotFoundException;
    Lease returnCar(int leaseID) throws LeaseNotFoundException;
    List<Lease> listActiveLeases();
    List<Lease> listLeaseHistory();

    void recordPayment(Lease lease, double amount) throws LeaseNotFoundException;
    
    static ICarLeaseRepository getDaoInstance() {
    	ICarLeaseRepository dao=new ICarLeaseRepositoryImpl();
		return dao;
	}

}
