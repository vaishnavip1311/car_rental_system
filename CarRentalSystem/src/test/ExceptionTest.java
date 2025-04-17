package test;

import org.junit.Before;
import org.junit.Test;

import dao.ICarLeaseRepositoryImpl;
import entity.Car;
import entity.Customer;
import exception.CarExistsException;
import exception.CarNotFoundException;
import exception.CustomerExistsException;
import exception.CustomerNotFoundException;
import exception.LeaseNotFoundException;

public class ExceptionTest {
	
	private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() {
        repo = new ICarLeaseRepositoryImpl();
    }

    @Test(expected = CustomerNotFoundException.class)
    public void testCustomerNotFoundException() throws CustomerNotFoundException {
        repo.findCustomerById(9999); 
    }

    @Test(expected = CarNotFoundException.class)
    public void testCarNotFoundException() throws CarNotFoundException {
        repo.findCarById(8888); 
    }

    @Test(expected = LeaseNotFoundException.class)
    public void testLeaseNotFoundException() throws LeaseNotFoundException {
        repo.returnCar(7777); 
    }
    
    @Test(expected = CarExistsException.class)
    public void testCarAlreadyExistsException() throws Exception {
        Car car = new Car(5001, "Kia", "Seltos", 2022, 42.5, "available", 5, 1.6);
        repo.addCar(car);
    }
    
    @Test(expected = CustomerExistsException.class)
    public void testCustomerAlreadyExistsException() throws Exception {
        Customer customer = new Customer(5002, "Riya", "Kapoor", "riya@example.com", "9876543210");
        repo.addCustomer(customer); 
    }

}
