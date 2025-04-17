package test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import dao.ICarLeaseRepositoryImpl;
import entity.Car;
import exception.CarExistsException;
import exception.CarNotFoundException;

public class CarCreationTest {
	
	private ICarLeaseRepositoryImpl repo;

    @Before
    public void setUp() {
        repo = new ICarLeaseRepositoryImpl();
    }

    @Test
    public void testCarCreatedSuccessfully() throws CarExistsException {
        Car car = new Car(2010, "Nissan", "Altima", 2021, 50.0, "available", 5, 2.0);
        repo.addCar(car);
        Car retrieved = null;
        try {
            retrieved = repo.findCarById(2010);
        } catch (CarNotFoundException e) {
            fail("Car not found after adding.");
        }
        assertNotNull(retrieved);
        assertEquals("Nissan", retrieved.getMake());
    }
    
}
