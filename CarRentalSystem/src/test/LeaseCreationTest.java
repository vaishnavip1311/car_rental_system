package test;

import static org.junit.Assert.*;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;

import dao.ICarLeaseRepositoryImpl;
import entity.Car;
import entity.Customer;
import entity.Lease;

public class LeaseCreationTest {
	
	private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() {
        repo = new ICarLeaseRepositoryImpl();
    }

    @Test
    public void testLeaseCreatedSuccessfully() throws Exception {
        Customer customer = new Customer(10, "Karan", "Mehta", "karan@example.com", "987768541");
        repo.addCustomer(customer);

        Car car = new Car(11, "Hyundai", "Creta", 2023, 40.0, "available", 5, 1.5);
        repo.addCar(car);

        Lease lease = repo.createLease(3010, 3011, LocalDate.now(), LocalDate.now(), "monthlyLease");

        assertNotNull(lease);
        assertEquals("monthlyLease", lease.getType());
    }

}
